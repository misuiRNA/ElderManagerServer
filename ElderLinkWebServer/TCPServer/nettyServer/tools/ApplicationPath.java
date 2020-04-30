package nettyServer.tools;
/**
 * netty应用中应用路径，文件路径管理工具
 * @author root
 *
 */
public class ApplicationPath {
	private final static String ROOTPATH;
	static {
		String Path=Thread.currentThread().getContextClassLoader().getResource("").toString();
		int end=Path.lastIndexOf("ElderLinkWebServer")+"ElderLinkWebServer/".length();//.indexOf("ElderLinkWebServer");
		int start=Path.indexOf("/");
		ROOTPATH=Path.substring(start,end);
	}
	/**
	 * 将相对路径转换为绝对路径
	 * @param dev 相对于应用程序“ElderLinkWebServer”的相对路径，不带“/”
	 * @return
	 */
	public static String getRealPath(String dev) {
		return ROOTPATH+dev;
	}
	
	
	
}
