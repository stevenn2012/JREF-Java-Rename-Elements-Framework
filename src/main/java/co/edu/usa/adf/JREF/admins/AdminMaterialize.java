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
				"jref/pruebas/page/fr/materialize/js/materialize.min.js", 
				"jref/pruebas/page/js/materialize.min.js", 
				"js/materialize.min.js", 
				'J');
		this.addDocument(
				"materialize.min.css", 
				"jref/pruebas/page/fr/materialize/css/materialize.min.css", 
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
		this.addElement("footer");
		this.addElement("page-footer");
		this.addElement("l6");
		this.addElement("footer-copyright");
		this.addElement("container");
		this.addElement("l4");
		this.addElement("offset-l2");
		this.addElement("btn");
		this.addElement("s4");
		this.addElement("s1");
		this.addElement("s2");
		this.addElement("s5");
		this.addElement("s7");
		this.addElement("s8");
	}
}
