package co.edu.usa.adf.JREF.logic;

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
import java.util.Iterator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class JREFAdmin extends JREFConfig{

	public JREFAdmin(String frameworkName, String identifier, String nameRulesJson) { 
		super(frameworkName, identifier, nameRulesJson); 
	}
	
	public abstract void loadDocuments();
	
	public abstract void loadElements();
	
	public void loadNativeElements(){
		addNativeElement("*");
		addNativeElement("html");
		addNativeElement("ul");
		addNativeElement("li");
		addNativeElement("body");
		addNativeElement("div");
		addNativeElement("input");
		addNativeElement("button");
	}
	
	public void addNativeElement(String name){
		if(name.equals("*")) this.addElement("*", "."+getNewName(""));
		else this.addElement(name, "."+getNewName(name));
	}
	
	public String getNewName(String oldName){
		return (this.identifier+oldName);
	}

	public void addElement(String oldName){
		this.elementsToChange.add(new JREFElement(oldName, getNewName(oldName)));
	}

	public String init(){
		loadDocuments();
		loadNativeElements();
		loadElements();
		return initREF();
	}
	
	private String initREF() {
		String scripts = "";
		for (int i = 0; i < documentsToChange.size(); i++) {
			scripts += renameElementsOfDocument(documentsToChange.get(i));
			if(i!=documentsToChange.size()-1) scripts += "\n";
		}
		return scripts;
	}
	
	private String renameElementsOfDocument(JREFDocument document){
		try {
			document = validateOriginPath(document);
			createFile(document.getFinalFilePath());
			
			try(BufferedReader read = new BufferedReader(new FileReader(document.getOriginalFilePath()));
				BufferedWriter write = new BufferedWriter(new FileWriter(document.getFinalFilePath()))){
				String line = "";
				write.write("/*"); write.newLine();
				write.write("* File Edit by JREF( Java Rename Elements Framework ) v0.0.1 - Author Steven Puerto"); write.newLine();
				write.write("* Github: stevenn2012.github.io"); write.newLine();
				write.write("*/"); write.newLine();
				while((line = read.readLine())!= null){
					if(document.getType() == 'C'){
						for (int i = 0; i < nativeElementsToChange.size(); i++) {
							line = replace(line, nativeElementsToChange.get(i).getOriginalName(), nativeElementsToChange.get(i).getFinalName());
						}
						for (int i = 0; i < elementsToChange.size(); i++){
							line = replace(line, elementsToChange.get(i).getOriginalName(), elementsToChange.get(i).getFinalName());
						}
					}
					write.write(line);
					write.newLine();
				}
				return getTagForHtml(document);
			} catch (Exception e) {
				e.printStackTrace();
				return "Init error: "+e;
			} finally{
				deleteTemporalFiles();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "RenameElementOfDocument error: "+e;
		}
	}
	
	/**
	 * Check local or online route, if it is online download the file
	 */
	private JREFDocument validateOriginPath(JREFDocument document) throws Exception{
		try {
			if(document.getOriginalFilePath().toUpperCase().indexOf("HTTP")==0){
				String name = this.frameworkName;
				if (document.getFinalFilePath().lastIndexOf(".") != -1) 
					name=name+"."+document.getFinalFilePath().substring(document.getFinalFilePath().lastIndexOf(".") + 1);
				File dir = new File(this.temporalFilesFolderRoute);
				if (!dir.exists()) if (!dir.mkdir()) throw new Exception("Error: Could not create destination folder");
				File file = new File(this.temporalFilesFolderRoute + name);
				URLConnection connection = new URL(document.getOriginalFilePath()).openConnection();
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
				document.setOriginalFilePath(temporalFilesFolderRoute+name);
			}
			return document;
		} catch (Exception e) {
			throw new Exception("validateOriginPath Error: "+e);
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
	
	public String replace(String line, String oldName, String newName){
		String finalLine="";
		int index = 0;
		while((index = line.indexOf(oldName))!=-1){
			finalLine += line.substring(0, index);
			char preChar = ' ', postChar= ' ';
			if(index>0)	preChar = line.substring(index-1).charAt(0);
			if((index+oldName.length()) < line.length()) postChar = line.substring(index+oldName.length()).charAt(0);
			try { line = line.substring(index+oldName.length());	
			} catch (Exception e) { line = ""; }
			if(validateCharacter(preChar+"") && validateCharacter(postChar+""))finalLine+=newName;
			else finalLine+=oldName;	
		}
		finalLine += line;
		return finalLine;
	}
	
	public boolean validateCharacter(String character){
		if(this.denyCharacters.indexOf(character.toUpperCase()) ==-1) return true;
		else return false;
	}
	
	private void deleteTemporalFiles(){
		File f = new File(temporalFilesFolderRoute);
		if (f.exists()){
			File[] files = f.listFiles();
			for (int i=0; i < files.length; i++) files[i].delete();
		} 
	}

	/**Json element management**/
	public void readRulesJson() throws IOException{
		createFile(this.rulesFolderPath+this.nameRulesJson);
		JsonElement elementsJson = new JsonParser().parse(new FileReader(this.rulesFolderPath+this.nameRulesJson));
		if(elementsJson.isJsonArray()){
			JsonArray array = elementsJson.getAsJsonArray();
	        Iterator<JsonElement> iter = array.iterator();
	        while (iter.hasNext()) {
	            JsonObject obj = iter.next().getAsJsonObject();
		        java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
		        java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter2 = entradas.iterator();
		        JREFElement element = new JREFElement("", "");
		        while (iter2.hasNext()) {
		            java.util.Map.Entry<String,JsonElement> entrada = iter2.next();
		            if(entrada.getKey().toUpperCase().equals(("originalName").toUpperCase())) 
		            	element.setOriginalName(entrada.getValue().toString().substring(1, entrada.getValue().toString().length()-1));
		            else if(entrada.getKey().toUpperCase().equals(("finalName").toUpperCase())) 
		            	element.setFinalName(entrada.getValue().toString().substring(1, entrada.getValue().toString().length()-1));
		        }
		        addElement(element);
	        }
		}
	}
	
	public void saveRulesOnJson(){
		
	}
	
	/****/
	
	public String getTagForHtml(JREFDocument document){
		try {
			String route = document.getScriptFilePath(); 
			if (route.lastIndexOf(".") != -1) {	
				if(document.getType() == 'J') return "<script type='text/javascript' src='"+route+"'></script>";
				else if(document.getType() == 'C') return "<link rel='stylesheet' type='text/css' href='"+route+"'>"; 
				else return "getTagForHtml error: Invalid extension";
			}else return "getTagForHtml error: Invalid extension";
		} catch (Exception e) {
			return "getTagForHtml error: "+e;
		}
	}
}
