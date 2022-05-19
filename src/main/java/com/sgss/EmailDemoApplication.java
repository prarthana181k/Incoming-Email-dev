package com.sgss;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.Attachment;
import com.microsoft.graph.models.FileAttachment;
import com.microsoft.graph.models.Message;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.FileAttachmentRequestBuilder;
import com.microsoft.graph.requests.GraphServiceClient;
import com.microsoft.graph.requests.MessageCollectionPage;
import com.sgss.model.Folder;
import com.sgss.service.EmailRequest;
import com.sgss.service.EmailService2;
import com.sgss.service.FolderRequest;
import com.sgss.service.FolderService;

import okhttp3.Request;

public class EmailDemoApplication{

    public static void main(String[] args) throws Exception {
        
//    	EmailRequest email= new EmailRequest();
//    	email.newInstance().userId("bf03eeb7-b951-4a42-8eb4-0c2ca31bbb53").DownaloadPath(null);
//    	//email.conhub("bf03eeb7-b951-4a42-8eb4-0c2ca31bbb53","2022-05-13T05:49:00Z");
//    	System.out.println(OffsetDateTime.parse("2022-05-11T00:00:00Z"));
//    	EmailRequest.newInstance().userId("bf03eeb7-b951-4a42-8eb4-0c2ca31bbb53").responseRequired(true)
//    	.startDate(OffsetDateTime.parse("2022-05-17T00:00:00Z")).destinationFolder("AAMkADUxMmZiNTJlLTE3ODYtNDgwMC05ZjNkLWEyM2EyMjgyNDcxOQAuAAAAAAC81vsJPti-Sb7ofWQg48-sAQAwLSlCs8z6S5ax2LkGh7U7AAAGQVqJAAA=")
//    	.get()
//    	.forEach(mail->{System.out.println(mail.getSubject());System.out.println(mail.getDate());});;
//    	
//    	FolderService.processFolder("bf03eeb7-b951-4a42-8eb4-0c2ca31bbb53").forEach(folder34->{System.out.println(folder34.getFolderName());System.out.println(folder34.getFolderId());});;
    	FolderRequest.newInstance("bf03eeb7-b951-4a42-8eb4-0c2ca31bbb53").ChildFolderRequired(true).get().forEach(folder34->{System.out.println(folder34.getFolderName());System.out.println(folder34.getFolderId());});;
    }

	
}

