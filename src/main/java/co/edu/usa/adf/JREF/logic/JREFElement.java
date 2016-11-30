package co.edu.usa.adf.JREF.logic;

public class JREFElement {

	private String originalName; 
	private String finalName;

	public JREFElement(String originalName, String finalName) {
		this.originalName = originalName;
		this.finalName = finalName;
	}
	
	public String getOriginalName() {
		return originalName;
	}
	
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	
	public String getFinalName() {
		return finalName;
	}
	
	public void setFinalName(String finalName) {
		this.finalName = finalName;
	}

	@Override
	public String toString() {
		return "JREFElement [originalName=" + originalName + ", finalName=" + finalName + "]";
	}
}
