package test;
import nettyServer.tools.IsPointInPolyline;

public class MapTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String[] point="108.773253,34.040461".split(",");
		String area="108.766175,34.043243ra108.776739,34.043363ra108.775948,34.03373ra108.773577,34.034209r"
							+ "a108.773433,34.035346ra108.766462,34.035615ra";
		
//		String[] point="5.000201121,5.0002011".split(",");
//		String area="5.000111,5.00051ra5.000501,5.0005011ra5.000501,5.00011ra5.00010,5.00011ra";
		
    	System.out.println("----------------------------------------------:"+IsPointInPolyline.isPointInPoly(point, area));
	}

}
