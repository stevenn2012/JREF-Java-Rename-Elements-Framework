package co.edu.usa.adf.JREF.logic;

import java.util.ArrayList;

public class JREFConfig {

	protected String frameworkName;
	protected String identifier;
	protected String rulesFolderPath;
	protected String nameRulesJson;
	protected String temporalFilesFolderRoute;
	protected String denyCharacters;
	protected ArrayList<JREFDocument> documentsToChange;
	protected ArrayList<JREFElement> elementsToChange;
	protected ArrayList<JREFElement> nativeElementsToChange;
	
	public JREFConfig(String frameworkName, String identifier, String nameRulesJson) {
		this.frameworkName = frameworkName;
		this.identifier = identifier;
		this.rulesFolderPath ="jref/rules-json/";
		this.nameRulesJson = nameRulesJson;
		this.temporalFilesFolderRoute = "jref/temporalFiles/";
		this.denyCharacters = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ123456789-/\\";
		this.documentsToChange = new ArrayList<JREFDocument>();
		this.elementsToChange = new ArrayList<JREFElement>();
		this.nativeElementsToChange = new ArrayList<JREFElement>();
	}

	/**Framework name**/
	public String getFrameworkName() {
		return frameworkName;
	}

	public void setFrameworkName(String frameworkName) {
		this.frameworkName = frameworkName;
	}
	
	/**Identifier**/
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**Rules Folder Path**/
	public String getRulesFolderPath() {
		return rulesFolderPath;
	}
	
	public void setRulesFolderPath(String rulesFolderPath) {
		this.rulesFolderPath = rulesFolderPath;
	}
	
	/**Rules Json name**/
	public String getNameRulesJson() {
		return nameRulesJson;
	}

	public void setNameRulesJson(String nameRulesJson) {
		this.nameRulesJson = nameRulesJson;
	}
	
	/**Temporal files route**/
	public String getTemporalFilesFolderRoute() {
		return temporalFilesFolderRoute;
	}

	public void setTemporalFilesFolderRoute(String temporalFilesFolderRoute) {
		this.temporalFilesFolderRoute = temporalFilesFolderRoute;
	}

	/**Deny Characters**/
	public String getDenyCharacters() {
		return denyCharacters;
	}

	public void setDenyCharacters(String denyCharacters) {
		this.denyCharacters = denyCharacters;
	}

	/**Elements to change**/
	public ArrayList<JREFElement> getElementsToChange() {
		return elementsToChange;
	}
	
	public void setElementsToChange(ArrayList<JREFElement> elementsToChange) {
		this.elementsToChange = elementsToChange;
	}
	
	public String getElementsToChangeToString() {
		return elementsToChange.toString();
	}
	
	public void addElement(JREFElement element){
		this.elementsToChange.add(element);
	}
	
	public void addElement(String orignalName, String finalName){
		this.elementsToChange.add(new JREFElement(orignalName, finalName));
	}
	
	public JREFElement getElement(int index){
		return this.elementsToChange.get(index);
	}
	
	public String getElementToString(int index){
		return this.elementsToChange.get(index).toString();
	}
	
	public void setElement(int index, JREFElement element) {
		this.elementsToChange.set(index, element);
	}
	
	public void setElement(int index, String orignalName, String finalName) {
		this.elementsToChange.set(index, new JREFElement(orignalName, finalName));
	}
	
	public void removeElement(int index){
		this.elementsToChange.remove(index);
	}
	
	public int elementsSize(){
		return this.elementsToChange.size();
	}
	
	/**Documents to change**/
	public ArrayList<JREFDocument> getDocumentsToChange() {
		return documentsToChange;
	}

	public void setDocumentsToChange(ArrayList<JREFDocument> documentToChange) {
		this.documentsToChange = documentToChange;
	}
	
	public String getDocumentsToChangeToString(){
		return documentsToChange.toString();
	}
	
	//**
	public void addDocument(JREFDocument document){
		this.documentsToChange.add(document);
	}
	
	public void addDocument(String documentName, String originalFilePath, String finalFilePath, String scriptFilePath, char type){
		this.documentsToChange.add(new JREFDocument(documentName ,originalFilePath, finalFilePath, scriptFilePath, type));
	}
	
	public JREFDocument getDocument(int index){
		return this.documentsToChange.get(index);
	}
	
	public String getDocumentToString(int index){
		return this.documentsToChange.get(index).toString();
	}
	
	public void setDocument(int index, JREFDocument document) {
		this.documentsToChange.set(index, document);
	}
	
	public void setDocument(int index, String documentName, String originalFilePath, String finalFilePath, String scriptFilePath, char type) {
		this.documentsToChange.set(index, new JREFDocument(documentName,originalFilePath, finalFilePath, scriptFilePath, type));
	}
	
	public void removeDocument(int index){
		this.documentsToChange.remove(index);
	}
	
	public int documentSize(){
		return this.documentsToChange.size();
	}

	public ArrayList<JREFElement> getNativeElementsToChange() {
		return nativeElementsToChange;
	}

	public void setNativeElementsToChange(ArrayList<JREFElement> nativeElementsToChange) {
		this.nativeElementsToChange = nativeElementsToChange;
	}

	@Override
	public String toString() {
		return "JREFConfig [frameworkName=" + frameworkName + ", rulesFolderPath=" + rulesFolderPath
				+ ", nameRulesJson=" + nameRulesJson + ", temporalFilesFolderRoute=" + temporalFilesFolderRoute
				+ ", denyCharacters=" + denyCharacters + ", documentsToChange=" + documentsToChange
				+ ", elementsToChange=" + elementsToChange + "]";
	}
}
