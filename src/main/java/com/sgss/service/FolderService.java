package com.sgss.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.requests.GraphServiceClient;
import com.microsoft.graph.requests.MailFolderCollectionPage;
import com.sgss.model.Folder;

import okhttp3.Request;

public class FolderService {
	private final static String CLIENT_ID = "47300f86-73c6-4b95-a81e-b4c15fedc831";
    private final static String TENANT_ID = "b5c66e55-d7b0-405d-b4f8-9a6e27f8d531";
    private final static String SECRET_ID = "YHA8Q~llII13px61YW4LHER8in5LYpXn.92h4dl_";

    //Set the scopes for your ms-graph request
    private final static List<String> SCOPES = Arrays.asList("https://graph.microsoft.com/.default");
	protected static List<Folder> processFolder (FolderRequest request) {
		return getAllFolder(getAuthorization(),request);
	}
	
	private static GraphServiceClient getAuthorization() {
		final ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                .clientId(CLIENT_ID)
                .clientSecret(SECRET_ID)
                .tenantId(TENANT_ID)
                .build();
		
        final TokenCredentialAuthProvider tokenCredAuthProvider = new TokenCredentialAuthProvider(SCOPES, clientSecretCredential);

        // Build a Graph client
        GraphServiceClient<Request> graphClient = GraphServiceClient.builder()
                .authenticationProvider(tokenCredAuthProvider)
                .buildClient();
        
        return graphClient;
		
	}
	private static List<Folder> getAllFolder(GraphServiceClient graphClient,FolderRequest request) {
		MailFolderCollectionPage mailFolders = graphClient.users(request.getUserId()).mailFolders()
				.buildRequest().select("displayName,id,childFolderCount").top(99)
				.get();
		List<Folder> folderlist=new ArrayList<>(); 
		mailFolders.getCurrentPage().forEach(folder->{
			Folder folder1=new Folder();
			folder1.setFolderId(folder.id);
			folder1.setFolderName(folder.displayName);
			folderlist.add(folder1);
			if(request.ChildFolderRequired() && folder.childFolderCount>0) {
				MailFolderCollectionPage mailFolders2=graphClient.users(request.getUserId()).mailFolders(folder.id).childFolders()
				.buildRequest()
				.get();
				mailFolders2.getCurrentPage().forEach(folder2->{
					Folder folder3=new Folder();
					folder3.setFolderId(folder2.id);
					folder3.setFolderName(folder2.displayName);
					folderlist.add(folder3);
				});;
			}	
		});;
		return folderlist;
	}
}
