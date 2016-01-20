package orbitSimulator;

import java.util.ArrayList;

public interface MainFrameListenerElliptical /*extends MainFrameListener*/ {
	
	public void setNewGraphics(Double raan, Double ra, Double rp, Double a, Double e, Double i, Double VatR, 
			Double RforV, Double va, Double vp, Double epsilon, Double TrueAnomaly, Double T, String renderScale, 
			ArrayList<Double> canvasUArray, ArrayList<Double> canvasVArray); // from elliptical 
}
