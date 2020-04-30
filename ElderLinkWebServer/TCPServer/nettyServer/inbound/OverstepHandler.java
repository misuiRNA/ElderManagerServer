package nettyServer.inbound;

import java.util.Map;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import linkserver.base.pojo.Elder;
import linkserver.data.handler.ElderJDBC;
import nettyServer.exception.DecoderException;
import nettyServer.exception.DecoderExceptionHandler;
import nettyServer.tools.ConstantMember;
import nettyServer.tools.IsPointInPolyline;
import test.DisplayDetails;
/**
 * 越界管理处理器,在pipline入站的第二项，是否申请越，界坐标点是否越界
 * 入站序列的二、三位置可以互换
 * @author root
 *
 */
public class OverstepHandler extends ChannelInboundHandlerAdapter implements ConstantMember{
	private final DecoderExceptionHandler exceptionHandler=new DecoderExceptionHandler();	//处理特殊异常
	private final ElderJDBC template;
	public OverstepHandler(ElderJDBC elderTemplate) {
		this.template=elderTemplate;
	}
	
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	@SuppressWarnings("unchecked")
		Map<String,Object> datas=(Map<String,Object>)msg;			//获取解码器解码后的数据映射
    	boolean applyforOut=(Boolean)datas.get(ConstantMember.KEY_APPLAYFOROUT);
        if(applyforOut) {					//做有关越界申请管理的相关工作
        	dealWithApplyForOut();
        }
    	Boolean isINAREA=true;				//表示老人是否在安全区域的值，将被持久化到数据库中
    	Elder elder=null;					//老人对象，查找成功后将添加到Map中
        try {
        	elder=getElder((String)datas.get(KEY_ELDERID),(String)datas.get(KEY_HOMEID));  //获取老人对象
            datas.put(KEY_ELDER, elder);					//将老人对象存入Map
            String[] point=(String[])datas.get(KEY_POINT);	//该老人的百度坐标
            isINAREA=isInArea(elder.getElderArea(),point);	//判断point是否在area内
            exceptionHandler.resetWrongTime(); 				//重置错误消息次数
        }catch(DecoderException e) {
        	//要求重发数据
        	exceptionHandler.setException(e, ctx).handle();
        	return;											//直接返回，不执行之后的代码
        }
        System.out.println("是否在安全区域---------->："+isINAREA);
        datas.put(KEY_INAREA, isINAREA);					//向Map中添加isINAREA，以便后续持进行久化操作
        //如果点不再区域内，存储老人位置安全状态
        if(!isINAREA) datas.put(KEY_EMERGENCY_POINTSTATUS, new Boolean(isINAREA));
        //*****测试代码段，检查datas中的所有键值对
        DisplayDetails.listMapperDetails(datas);			
        ctx.fireChannelRead(msg);
    }
	/**
	 * 在数据库查询老人的安全活动范围
	 * @param elderID 老人在机构内的ID
	 * @param homeID 老人所在机构的ID
	 * @return 老人的安全活动范围数据
	 * @throws DecoderException 
	 */
    private Elder getElder(String elderID,String homeID) throws DecoderException {
    	Elder elder=template.getElder(elderID);
    	if(elder==null) throw new DecoderException("no elder's id is "+elderID);
    	return elder;
    }
    /**
     * 判断点是否在区域内，在则返回true，否则返回false
     * @param area 区域（面）数据
     * @param point 点坐标，包含经度和纬度
     * @return 
     */
    private boolean isInArea(String area,String[] point) {
    	//通过调用JS或本地方法，判断point是否在area内
    	return IsPointInPolyline.isPointInPoly(point, area);
    }
    /**
     * 做有关越界申请管理的相关工作
     */
    private void dealWithApplyForOut() {
    	//*****************当客户申请越界时，做出相应操作
    	
    }
    
}
