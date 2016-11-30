package co.edu.usa.adf.JREF.admins;

import co.edu.usa.adf.JREF.logic.JREFAdmin;

public class AdminMaterialize extends JREFAdmin{

	public AdminMaterialize(String frameworkName, String identifier, String nameRulesJson) {
		super(frameworkName, identifier, nameRulesJson);
	}
	
	@Override
	public void loadDocuments() {
		// TODO Auto-generated method stub
		// TODO this.addDocument(documentName, originalFilePath, finalFilePath, scriptFilePath, type);
		this.addDocument(
				"materialize.min.js", 
				"https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.min.js", 
				"jref/pruebas/page/js/materialize.min.js", 
				"js/materialize.min.js", 
				'J');
		this.addDocument(
				"materialize.min.css", 
				"https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.min.css", 
				"jref/pruebas/page/css/materialize.min.css", 
				"css/materialize.min.css", 
				'C');
	}

	@Override
	public void loadElements() {
		// TODO Auto-generated method stub
		// TODO this.addElement(orignalName, finalName);
		this.addElement("row");
		this.addElement("col");
		this.addElement("s12");
		this.addElement("input-field");
		this.addElement("s6");
		this.addElement("inline");
	}
}
