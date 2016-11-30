package co.edu.usa.adf.JREF.admins;

import co.edu.usa.adf.JREF.logic.JREFAdmin;

public class AdminBootstrap extends JREFAdmin{

	public AdminBootstrap(String frameworkName, String identifier, String nameRulesJson) {
		super(frameworkName, identifier, nameRulesJson);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadDocuments() {
		// TODO Auto-generated method stub
		// TODO this.addDocument(documentName, originalFilePath, finalFilePath, scriptFilePath, type);
		this.addDocument(
				"bootstrap.min.css", 
				"jref/pruebas/page/fr/bootstrap/3.3.7/css/bootstrap.css", 
				"jref/pruebas/page/css/bootstrap.min.css", 
				"css/bootstrap.min.css", 
				'C');
		this.addDocument(
				"bootstrap-theme.min.css", 
				"jref/pruebas/page/fr/bootstrap/3.3.7/css/bootstrap-theme.min.css", 
				"jref/pruebas/page/css/bootstrap-theme.min.css", 
				"css/bootstrap-theme.min.css", 
				'C');
		this.addDocument(
				"bootstrap.min.js", 
				"jref/pruebas/page/fr/bootstrap/3.3.7/js/bootstrap.min.js", 
				"jref/pruebas/page/js/bootstrap.min.js", 
				"js/bootstrap.min.js", 
				'J');
	}

	@Override
	public void loadElements() {
		// TODO Auto-generated method stub
		// TODO this.addElement(orignalName, finalName);
		this.addElement("input-group");
		this.addElement("input-group-lg");
		this.addElement("input-group-addon");
		this.addElement("sizing-addon1");
		this.addElement("form-control");
		this.addElement("sizing-addon2");
		this.addElement("input-group-sm");
		this.addElement("sizing-addon3");
		this.addElement("input-group-btn");
		this.addElement("btn-primary");
		this.addElement("btn-lg");
		this.addElement("btn");
		this.addElement("btn-group");
	}
}
