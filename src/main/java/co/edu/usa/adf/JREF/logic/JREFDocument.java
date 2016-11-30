package co.edu.usa.adf.JREF.logic;

public class JREFDocument {
	
	private String documentName;
	private String originalFilePath; 
	private String finalFilePath;
	private String scriptFilePath;
	private char type;
	
	public JREFDocument(String documentName, String originalFilePath, String finalFilePath, String scriptFilePath, char type) {
		this.documentName = documentName;
		this.originalFilePath = originalFilePath;
		this.finalFilePath = finalFilePath;
		this.scriptFilePath = scriptFilePath;
		this.type = type;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}


	public String getOriginalFilePath() {
		return originalFilePath;
	}

	public void setOriginalFilePath(String originalFilePath) {
		this.originalFilePath = originalFilePath;
	}

	public String getFinalFilePath() {
		return finalFilePath;
	}

	public void setFinalFilePath(String finalFilePath) {
		this.finalFilePath = finalFilePath;
	}

	public String getScriptFilePath() {
		return scriptFilePath;
	}

	public void setScriptFilePath(String scriptFilePath) {
		this.scriptFilePath = scriptFilePath;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "JREFDocument [originalFilePath=" + originalFilePath + ", finalFilePath=" + finalFilePath
				+ ", scriptFilePath=" + scriptFilePath + ", type=" + type + "]";
	}
}
