package orbitSimulator;

import java.util.HashMap;
import java.util.Map;

public class SolarSystemDatabase {
	
	public HashMap<String, Double> getSolarSystemObjectInformation(String object)
	{
		Object returnObject = null;
		
		switch(object)
		{
		case "earth":
			returnObject = earth; 
			break;
		case "moon":
			returnObject = moon;
			break;
		case "mars":
			returnObject = mars;
			break;
		}
		if(returnObject == null)
		{
			// error handling
		}
		
		return (HashMap<String, Double>) returnObject;
	}
	
	
	private static final Map<String, Double> earth;
	static
	{
		earth = new HashMap<String, Double>();
		earth.put("radius", (double) 6317);
		earth.put("mu", (double) 398600);
		earth.put("atmosphereRadius", (double) 6517);
		earth.put("SOI", (double) 924000);
	}
	
	private static final Map<String, Double> moon;
	static
	{
		moon = new HashMap<String, Double>();
		moon.put("radius", (double) 1737);
		moon.put("mu", (double) 4902.8);
		moon.put("atmosphereRadius", (double) 0);
		moon.put("SOI", (double) 66100);	
	}
	
	private static final Map<String, Double> mars;
	static
	{
		mars = new HashMap<String, Double>();
		mars.put("radius", (double) 3396);
		mars.put("mu", (double) 42828);
		mars.put("atmosphereRadius", (double) 3550);
		mars.put("SOI", (double) 576000);
	}
	
	

}
