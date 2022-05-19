package com.sgss.service;

import java.util.List;
import com.sgss.model.Folder;

public class FolderRequest {
	private String userId ;
	private Boolean childFolderRequired= false;
	protected FolderRequest() {
		super();
	}
	protected String getUserId() {
		return userId;
	}
	protected Boolean ChildFolderRequired() {
		return childFolderRequired;
	}
	public static FolderRequest newInstance(String userId) {
		 FolderRequest folderRequest=new FolderRequest();
		 folderRequest.userId=userId;
		 return folderRequest;
	}
	public FolderRequest ChildFolderRequired(Boolean childFolderRequired) {
		this.childFolderRequired = childFolderRequired;
		return this;
	}
	public List<Folder> get() {
		FolderService folderService=new FolderService();
		return folderService.processFolder(this);
	}
	

}
