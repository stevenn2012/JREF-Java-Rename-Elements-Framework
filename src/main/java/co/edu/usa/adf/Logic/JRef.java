package co.edu.usa.adf.Logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Java Rename Elements Framework (JREF)
 * @author Steven Puerto
 */

public class JRef {
	
	public class Element{
		public String originalName, finalName;
		public Element(String orignalName, String finalName) { this.originalName = orignalName; this.finalName = finalName; }
		@Override public String toString() { return "Element [orignalName=" + originalName + ", finalName=" + finalName + "]"; }
	}
	
	private String frameworkName;
	private String rulesFolderPath;
	private String temporalFilesFolderRoute;
	private String originalFilePath; 
	private String finalFilePath; 
	private String nameRulesJson;
	private ArrayList<Element> elementsToChange = new ArrayList<JRef.Element>();
	
	public JRef(String frameworkName, String originalFilePath, String finalFilePath, String nameRulesJson) {
		this.frameworkName = frameworkName;
		this.finalFilePath = finalFilePath;
		this.nameRulesJson = nameRulesJson;
		this.rulesFolderPath = "ref/rules-json/";
		this.temporalFilesFolderRoute = "ref/temporalFiles/";
		this.originalFilePath = originalFilePath;
	}

	public String getName() {
		return frameworkName;
	}

	public void setName(String name) {
		this.frameworkName = name;
	}

	public String getTemporalRoute() {
		return temporalFilesFolderRoute;
	}

	public void setTemporalRoute(String temporalRoute) {
		this.temporalFilesFolderRoute = temporalRoute;
	}

	public String getRulesPath() {
		return rulesFolderPath;
	}

	public void setRulesPath(String rulesPath) {
		this.rulesFolderPath = rulesPath;
	}

	public String getNameRulesJson() {
		return nameRulesJson;
	}

	public void setNameRulesJson(String nameRulesJson) {
		this.nameRulesJson = nameRulesJson;
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

	public String getRulesJson() {
		return nameRulesJson;
	}

	public void setRulesJson(String rulesJson) {
		this.nameRulesJson = rulesJson;
	}

	public ArrayList<Element> getElementsToChange() {
		return elementsToChange;
	}

	public void setElementsToChange(ArrayList<Element> elementsToChange) {
		this.elementsToChange = elementsToChange;
	}

	public String getElementsToChangeToString() {
		return elementsToChange.toString();
	}
	
	public void addElement(Element element){
		this.elementsToChange.add(element);
	}
	
	public void addElement(String orignalName, String finalName){
		this.elementsToChange.add(new Element(orignalName, finalName));
	}
	
	public Element getElement(int index){
		return this.elementsToChange.get(index);
	}
	
	public String getElementToString(int index){
		return this.elementsToChange.get(index).toString();
	}
	
	public void setElement(int index, Element element) {
		this.elementsToChange.set(index, element);
	}
	
	public void setElement(int index, String orignalName, String finalName) {
		this.elementsToChange.set(index, new Element(orignalName, finalName));
	}
	
	public void removeElement(int index){
		this.elementsToChange.remove(index);
	}
	
	public int size(){
		return this.elementsToChange.size();
	}

	@Override
	public String toString() {
		return "JFrame [originalFilePath=" + originalFilePath + ", finalFilePath=" + finalFilePath
				+ ", elementsToChange=" + elementsToChange + "]";
	}
	
	/**
	 * start of framework
	 */
	public String init(){
		try {
			validateOriginPath();
			createFile(finalFilePath);
			try(BufferedReader read = new BufferedReader(new FileReader(originalFilePath));
				BufferedWriter write = new BufferedWriter(new FileWriter(finalFilePath))){
				readRulesJson();
				String line = "";
				write.write("/*"); write.newLine();
				write.write("* File Edit by JREF( Java Rename Elements Framework ) v0.0.1 - Author Steven Puerto"); write.newLine();
				write.write("* Github: stevenn2012.github.io"); write.newLine();
				write.write("*/"); write.newLine();
				while((line = read.readLine())!= null){
					for (int i = 0; i < elementsToChange.size(); i++) {
						line = replace(line, elementsToChange.get(i).originalName, elementsToChange.get(i).finalName);
					}
					write.write(line);
					write.newLine();
				}
				return getTagForHtml();
			} catch (Exception e) {
				return "Init error: "+e;
			} finally{
				deleteTemporalFiles();
			}
		} catch (Exception e) {
			return "Init error: "+e;
		}
	}
	
	private String replace(String line, String oldName, String newName){
		/**cambiar, verificar que sea el correcto  materialize es diferete a materialize-red**/
		line = line.replace(oldName, newName);
		return line;
	}
	
	private void deleteTemporalFiles(){
		File f = new File(temporalFilesFolderRoute);
		if (f.exists()){
			File[] files = f.listFiles();
			for (int i=0; i < files.length; i++){
				files[i].delete();
			}
		} 
	}
	
	private void createFile(String route) throws IOException{
		File archivo = new File(route);
		BufferedWriter bw;
		if(!archivo.exists()) {
			bw = new BufferedWriter(new FileWriter(archivo));
		    bw.write("");
		}
	}
	
	/**
	 * Check local or online route, if it is online download the file
	 */
	private void validateOriginPath() throws Exception{
		try {
			if(originalFilePath.toUpperCase().indexOf("HTTP")==0){
				String name = this.frameworkName;
				if (finalFilePath.lastIndexOf(".") != -1) name=name+"."+finalFilePath.substring(finalFilePath.lastIndexOf(".") + 1);
				File dir = new File(temporalFilesFolderRoute);
				if (!dir.exists()) if (!dir.mkdir()) throw new Exception("Could not create destination folder");
				File file = new File(temporalFilesFolderRoute + name);
				URLConnection connection = new URL(originalFilePath).openConnection();
				connection.connect();	
				InputStream in = connection.getInputStream();
				OutputStream out = new FileOutputStream(file);
					int line = 0;
					while (line != -1) {
						line = in.read();
						if (line != -1) out.write(line);
					}
				out.close();
				in.close();
				originalFilePath = temporalFilesFolderRoute+name;
			}
		} catch (Exception e) {
			throw new Exception("validateOriginPath Error: "+e);
		}
	}
	
	public void readRulesJson() throws IOException{
		createFile(rulesFolderPath+nameRulesJson);
		JsonElement elementsJson = new JsonParser().parse(new FileReader(rulesFolderPath+nameRulesJson));
		if(elementsJson.isJsonArray()){
			JsonArray array = elementsJson.getAsJsonArray();
	        Iterator<JsonElement> iter = array.iterator();
	        while (iter.hasNext()) {
	            JsonObject obj = iter.next().getAsJsonObject();
		        java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
		        java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter2 = entradas.iterator();
		        Element element = new Element("", "");
		        while (iter2.hasNext()) {
		            java.util.Map.Entry<String,JsonElement> entrada = iter2.next();
		            if(entrada.getKey().toUpperCase().equals(("originalName").toUpperCase())) element.originalName = entrada.getValue().toString().substring(1, entrada.getValue().toString().length()-1);
		            else if(entrada.getKey().toUpperCase().equals(("finalName").toUpperCase())) element.finalName = entrada.getValue().toString().substring(1, entrada.getValue().toString().length()-1);
		        }
		        addElement(element);
	        }
		}
	}
	
	public String getTagForHtml(){
		if (finalFilePath.lastIndexOf(".") != -1) {
			String extension = finalFilePath.substring(finalFilePath.lastIndexOf(".") + 1).toUpperCase();
			if(extension.equals("JS")) return "<script type='text/javascript' src='"+finalFilePath+"'></script>";
			else if(extension.equals("CSS")) return "<link rel='stylesheet' type='text/css' href="+finalFilePath+">"; 
		}
		return "getTagForHtml error";
	}
}
