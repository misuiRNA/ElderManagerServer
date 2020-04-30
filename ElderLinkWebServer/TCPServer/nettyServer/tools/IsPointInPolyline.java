package nettyServer.tools;

/***
 * 用于判断点是否在多边形内
 * @author root
 *
 */
public class IsPointInPolyline {
	/**
	 * 解析经纬度信息为坐标
	 * @param lng
	 * @param lat
	 * @return
	 */
	private static Point2D setPoint(String lng,String lat) {
		return newPoint(lng,lat);
	}
	/**
	 * 返回一个新的Point2D对象
	 * @param lngStr 
	 * @param latStr 
	 * @return
	 */
	private static Point2D newPoint(String lngStr,String latStr) {
		double lng=Double.valueOf(lngStr);
		double lat=Double.valueOf(latStr);
		return new Point2D(lng,lat);
	}
	/**
	 * 填充多边形
	 * @param polyStr 多边形String型数据，例如108.958523,34.270461ra108.957014,34.264345ra
	 */
	private static Point2D[] setPolygon(String polyStr) {
		String[] pointsStr=polyStr.split("ra");			//将图形字符解析为点字符的数组
		Point2D[] polygon=new Point2D[pointsStr.length];
		for(int i=0;i<pointsStr.length;i++) {
			String[] point=pointsStr[i].split(",");
			polygon[i]=newPoint(point[0],point[1]); 	//填充多边形为给定数据指定的多边形
		}
		return polygon;
	}
	
	/**
	 * 判断点是否在多边形内，如果在则返回true
	 * @param pointStr
	 * @param polylineStr
	 * @return
	 */
	public static boolean isPointInPoly(String[] pointStr,String polylineStr) {
		Point2D point=setPoint(pointStr[0],pointStr[1]);
		Point2D[] polygon=setPolygon(polylineStr);
		return isPointInPoly(point,polygon);
	}
	/**
	 * 判断点与多边形位置关系
	 * @param point
	 * @param polygon
	 * @return
	 */
	private static boolean isPointInPoly(Point2D point,Point2D[] polygon) {
        int N = polygon.length;
        boolean boundOrVertex = true; //如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
        int intersectCount = 0;//cross points count of x 
        float precision = (float) 2e-10; //浮点类型计算时候与0比较时候的容差
        Point2D p1, p2;//neighbour bound vertices
        Point2D p = point; //测试点
		p1=polygon[0];		//left vertex
		for(int i=1;i<=N;++i) {
			if(p.equals(p1)) {
				return boundOrVertex;
			}
            p2 = polygon[i % N];//right vertex            
            if(p.lat < Math.min(p1.lat, p2.lat) || p.lat > Math.max(p1.lat, p2.lat)){//ray is outside of our interests                
                p1 = p2; 
                continue;//next ray left point
            }
            if(p.lat > Math.min(p1.lat, p2.lat) && p.lat < Math.max(p1.lat, p2.lat)){//ray is crossing over by the algorithm (common part of)
                if(p.lng <= Math.max(p1.lng, p2.lng)){//x is before of ray                    
                    if(p1.lat == p2.lat && p.lng >= Math.min(p1.lng, p2.lng)){//overlies on a horizontal ray
                        return boundOrVertex;
                    }
                    if(p1.lng == p2.lng){//ray is vertical                        
                        if(p1.lng == p.lng){//overlies on a vertical ray
                            return boundOrVertex;
                        }else{//before ray
                            ++intersectCount;
                        } 
                    }else{//cross point on the left side                        
                        double xinters = ((p.lat - p1.lat) * (p2.lng - p1.lng) / (p2.lat - p1.lat) + p1.lng);//cross point of lng                        
                        if(Math.abs(p.lng - xinters) < precision){//overlies on a ray
                            return boundOrVertex;
                        }
                        if(p.lng < xinters){//before ray
                            ++intersectCount;
                        } 
                    }
                }
            }else{//special case when ray is crossing through the vertex                
                if(p.lat == p2.lat && p.lng <= p2.lng){//p crossing over p2                    
                    Point2D p3 = polygon[(i+1) % N]; //next vertex                    
                    if(p.lat >= Math.min(p1.lat, p3.lat) && p.lat <= Math.max(p1.lat, p3.lat)){//p.lat lies between p1.lat & p3.lat
                        ++intersectCount;
                    }else{
                        intersectCount += 2;
                    }
                }
            }            
            p1 = p2;//next ray left point
        }
        if(intersectCount % 2 == 0){//偶数在多边形外
            return false;
        } else { //奇数在多边形内
            return true;
        }  		
	}
	
	
	
}
