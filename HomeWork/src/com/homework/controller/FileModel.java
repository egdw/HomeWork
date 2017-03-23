package com.homework.controller;

import java.io.File;

public class FileModel {
	private String fileName;
	private File file;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "FileModel [fileName=" + fileName + ", file=" + file + "]";
	}

}
