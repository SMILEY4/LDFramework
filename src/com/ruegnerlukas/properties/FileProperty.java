package com.ruegnerlukas.properties;

import java.io.File;

public class FileProperty extends Property {

	private File value;
	
	
	
	
	
	
	public FileProperty(String name) {
		super(name);
	}
	

	public FileProperty(String name, File value) {
		super(name);
		this.value = value;
	}
	
	
	public FileProperty(String name, String path) {
		super(name);
		this.value = new File(path);
	}
	
	
	
	
	
	public void setValue(File value) { 
		this.value = value;
	}
	
	
	public void setValue(String path) { 
		this.value = new File(path);
	}
	
	
	
	public File getValue() {
		return this.value;
	}
	
	
	
}
