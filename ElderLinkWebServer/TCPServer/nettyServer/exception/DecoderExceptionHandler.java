package nettyServer.exception;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
/**
 * 解决DecoderException类型异常的专用类
 * @author root
 *
 */
public class DecoderExceptionHandler {
	
	private final String NOTIFY="SENDAGAIN----";		//重发消息通知
	private DecoderException exception;
	private ChannelHandlerContext ctx;
	private int WRONGMESSAGETIME=0;
	/**
	 * 设置异常实例与ctx，以便对异常情况进行操作,之后需要紧跟hanle()方法，用以处理对应的异常
	 * @param e 
	 * @param ctxt 
	 * @return
	 */
	public DecoderExceptionHandler setException(DecoderException e,ChannelHandlerContext ctxt) {
		this.exception=e;
		ctx=ctxt;
		return this;
	}
	/**
	 * 将异常次数置0
	 */
	public void resetWrongTime() {
		WRONGMESSAGETIME=0;
	}
	/**
	 * 异常处理实际操作方法
	 */
	public void handle() {
		//这里要求远程重发数据，如果同一远程终端累计发送非法数据5次，则断开连接
		WRONGMESSAGETIME++;
		ByteBuf notify=ctx.alloc().buffer(32);
		notify.writeBytes((NOTIFY+WRONGMESSAGETIME).getBytes());
		ctx.writeAndFlush(notify);
		System.out.println("it's "+WRONGMESSAGETIME+" time received wrong message");
//		exception.printStackTrace();/
		System.out.println(exception.getMessage());
		if(WRONGMESSAGETIME>4) ctx.close();
	}
	
}
