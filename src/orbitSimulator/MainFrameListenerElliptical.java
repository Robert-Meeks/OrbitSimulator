package orbitSimulator;

public interface MainFrameListenerElliptical /*extends MainFrameListener*/ {
	
	public void setNewGraphics(Double raan, Double ra, Double rp, Double a, Double e, Double i, Double VatR, 
			Double RforV, Double va, Double vp, Double epsilon, Double TrueAnomaly, Double T, String renderScale); // from elliptical 
}
