package script;

import java.io.FileReader;
import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import nettyServer.tools.ApplicationPath;

public class InvokeBaiduMap {	
	
	public void invoke() throws Exception {
		String jsPath=ApplicationPath.getRealPath("WebContent/WEB-INF/baiduMap/test.js");
		System.out.println("PATH:"+jsPath);
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("JavaScript");
		Bindings bind=engine.createBindings();
		bind.put("factor", 0);
		engine.setBindings(bind, ScriptContext.ENGINE_SCOPE);
		engine.eval(new FileReader(jsPath));
		
		int x=3;
		int y=4;
		if(engine instanceof Invocable) {
			Invocable in =(Invocable)engine;
			Object result=in.invokeFunction("add", x,y);
			System.out.println("the result is:"+result+"   type:"+result.getClass());
		}
				
	}
	
	
	

	
	
}
