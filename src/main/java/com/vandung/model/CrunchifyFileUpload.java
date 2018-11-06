package com.vandung.model;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class CrunchifyFileUpload {
	
	private List<MultipartFile> crunchifyFiles;
	
	public List<MultipartFile> getCrunchifyFiles() {
		return crunchifyFiles;
	}
	public void setCrunchifyFiles(List<MultipartFile> crunchifyFiles) {
		this.crunchifyFiles = crunchifyFiles;
	}
	public List<MultipartFile> getFiles() {
        return crunchifyFiles;
    } 
    public void setFiles(List<MultipartFile> files) {
        this.crunchifyFiles = files;
    }
}
