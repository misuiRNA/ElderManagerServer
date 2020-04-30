package test;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class DisplayDetails {
	
	public static void listMapperDetails(Map<String,Object> map) {
		System.out.println("<---------------数据详情----------------->");
		Set<String> keys=map.keySet();
		for(String key:keys) {
			if(map.get(key) instanceof String) System.out.println(key + ": " + (String)map.get(key));
			else if(map.get(key) instanceof Integer) System.out.println(key + ": " + (Integer)map.get(key));
			else if(map.get(key) instanceof String[]) System.out.println(key + ": " + Arrays.toString((String[])map.get(key)));
			else if(map.get(key) instanceof Boolean) System.out.println(key + ": " + (Boolean)map.get(key));
		}
	}
	
}
