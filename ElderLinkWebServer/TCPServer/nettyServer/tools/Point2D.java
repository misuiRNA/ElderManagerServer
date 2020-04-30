package nettyServer.tools;

public class Point2D {
	public final double lng;		//地理经度
	public final double lat;		//地理纬度
	public Point2D(double lng,double lat){
		this.lng=lng;
		this.lat=lat;
	}
	public boolean equals(Point2D point) {
		if(this.lng==point.lng&&this.lat==point.lat) return true;
		return false;
	}
	
	public String toString() {
		return "lng:"+String.valueOf(lng)+"_lat:"+String.valueOf(lat);
	}
}
