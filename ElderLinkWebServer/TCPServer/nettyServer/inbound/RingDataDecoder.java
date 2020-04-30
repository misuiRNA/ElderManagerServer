package nettyServer.inbound;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import nettyServer.exception.DecoderException;
import nettyServer.exception.DecoderExceptionHandler;
import nettyServer.tools.ConstantMember;
/**
 * 解码器，消息入站后首次解码，将数据按照格式解析，供后续handler使用
 * @author root
 *
 */
public class RingDataDecoder extends ByteToMessageDecoder{
	//以下常量为消息解码为数据的对应数据类型，在对应的解码方法中（如将byte转化为Integer,String等）作为参数
	private final int APPLYFOROUT=2;	//指定返回数为是否带有越界申请
	private final int POWER=3;				//指定返回数据为电量的标志
	private final int HOMEID=4;			//指定返回数据为机构ID的标志
	private final int ELDERID=5;			//指定返回数据为客户ID的标志
	private final int HEARTRATE=6;		//指定返回数据为心率的标志
	private final int BLOODPRESSURE=7;//指定返回数据为血压的标志
	private final int POINT=8;				//指定返回数据为经纬度的标志
	
	/**
	 * 正常数据的最小长度，根据协议，正常数据的长度应当大于等于46byte
	 */
	private final int LENGTH=42;		//数据的长度，长度过小的输入视为非法字符
	private final String IDENTIFILER="LINK";			//合法消息标志头
	private final DecoderExceptionHandler exceptionHandler=new DecoderExceptionHandler();		//处理特殊异常的类
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		// TODO Auto-generated method stub		
		Map<String,Object> datas=new HashMap<String,Object>();
		try {
			identifiler(in);		//判断消息是否合法
			datas.put(ConstantMember.KEY_APPLAYFOROUT, getBoolean(in,APPLYFOROUT));		//申请越界标志
			datas.put(ConstantMember.KEY_POWER, getInteger(in,POWER));					//电量
			datas.put(ConstantMember.KEY_HOMEID, getString(in,HOMEID));				//	机构ID号
			datas.put(ConstantMember.KEY_ELDERID, getString(in,ELDERID)); 				//客户ID号
			datas.put(ConstantMember.KEY_HEARTRATE, getInteger(in,HEARTRATE)); 			//心率值
			datas.put(ConstantMember.KEY_BLOODPRESSURE, getInteger(in,BLOODPRESSURE));		//血压值
			datas.put(ConstantMember.KEY_POINT, getPoint(in,POINT)); 					//经纬度
			exceptionHandler.resetWrongTime();		//将非法消息计数置0
			System.out.println("message has been decoded");
			out.add(datas);							//将数据添加到消息列表，由netty负责发送到pipline链的下一环节
		}catch(DecoderException e) {
			//调用exception处理方法解决该异常情况
			exceptionHandler.setException(e,ctx).handle();
		}finally {
			in.clear();		//必须清除消息，否则会由于netty的解码器设计原则，多次执行Decode()导致出错
		}
	}
	/**
	 * 对消息长度和标识符验证，检验消息是否合法——调用次序：1
	 * @param in 
	 * @throws DecoderException
	 */
	private void identifiler(ByteBuf in) throws DecoderException {
		if (in.readableBytes()<LENGTH) throw new DecoderException("message too short——1");
		StringBuffer identifiler=new StringBuffer();
		for(int i=0;i<4;i++) {
			char c=(char)in.readByte();
			identifiler.append(c);
		}
		if(!IDENTIFILER.equals(identifiler.toString())) throw new DecoderException("wrong identifiler——1");
	}	
	/**
	 * 解析对应消息为boolean类型，调用次序——2
	 * @param in
	 * @param type 指定返回的数据所表示的意义，要求该参数使用本类的静态变量，2表示返回是否带有越界请求
	 * @return
	 * @throws DecoderException
	 */
	private Boolean getBoolean(ByteBuf in,int type) throws DecoderException {
		byte value=in.readByte();
		if(value>1||value<0) throw new DecoderException("Wrong code of applying for out——"+type);
		return value==(byte)1;
	}
	/**
	 * 解析对应消息为字符串，调用次序——4，5
	 * @param in
	 * @param type 指定返回的数据所表示的意义，要求该参数使用本类的静态变量；4表示返回机构ID，5表示返回老人ID
	 * @return
	 */
	private String getString(ByteBuf in,int type) {
		StringBuffer buff=new StringBuffer();
		int length=0;
		if(type==HOMEID) length=10;
		else if(type==ELDERID) length=8;
		for(int i=0;i<length;i++) {
			char c=(char)in.readByte();
			buff.append(c);
		}
		return buff.toString();
	}
	/**
	 * 将对应数据解析为整数，调用次序——3,6,7
	 * @param in
	 * @param type 指定返回的数据所表示的意义，要求该参数使用本类的静态变量，3表示电池电量百分比，
	 * 6表示返回心率，7表示返回血压
	 * @return
	 * @throws DecoderException 该值大于intType视为数据非法
	 */
	private Integer getInteger(ByteBuf in,int type) throws DecoderException {
		byte value=in.readByte();
		int result=value&0xff;
		if(type==HEARTRATE&&result>200) throw new DecoderException("wrong heart rate——"+type);
		else if(type==BLOODPRESSURE&&result>230) throw new DecoderException("wrong heart rate——"+type);
		else if(type==POWER&&result>100) throw new DecoderException("power out of range——"+type);
		return result;
	}
	/**
	 * 经纬度字符串数组，point[0]为经度，point[1]为纬度，调用次序——8
	 * @param in
	 * @return
	 * @throws DecoderException
	 */
	private String[] getPoint(ByteBuf in,int type) throws DecoderException {
		final String[] point=new String[2];
		StringBuffer buffer=new StringBuffer();
		if(in.readByte()!=36) throw new DecoderException("point start position is wrong——"+type);
		for(int i=0;i<2;i++) {
			char c=(char)in.readByte();
			while(isNumber(c)) {
				buffer.append(c);
				c=(char)in.readByte();
			}
			point[i]=buffer.toString();
			buffer.setLength(0);
		}
		in.clear();				//清除消息，防止冗余数据导致的过度操作
		return point;
	}
	/**
	 * 判断当前字符是否为数字0-9或点号（.）,是返回true，如果是美元符号（36号字符）返回false，否则视为非法字符
	 * @param c
	 * @return
	 * @throws DecoderException
	 */
	private boolean isNumber(char c) throws DecoderException {
		if(c>45&&c<58&&c!=47) return true;
		if(c==36) return false;
		throw new DecoderException("no a number——8_1");
	}
}
