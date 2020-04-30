package nettyServer.exception;
/**
 * 异常类，描述RingData的解析过程中出现非法数据时抛出
 * @author root
 *
 */
public class DecoderException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	public DecoderException(String message) {
		super(message);
	}
	
}
