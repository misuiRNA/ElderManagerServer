package linkserver.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import emergencise.Emergency;
import emergencise.EmergencyCache;

@RestController
@RequestMapping(value = "/emergency", produces = "application/json; charset=utf-8")
public class EmergencyController {
	
	@RequestMapping(value = "/poll/{homeId}", method = RequestMethod.GET)
	public Object pollEmergency(@PathVariable("homeId") String homeId) {
		EmergencyCache cache = EmergencyCache.cache();
		Emergency emergency = cache.pollEmergency(homeId);
		if(emergency == null) {
			return new String("null");
		}
		return emergency;
	}
	
	
}
