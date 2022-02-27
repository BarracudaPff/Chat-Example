package com.samsung.chatexample.models.application;

import com.samsung.chatexample.models.domain.DialogMetadataD;

import java.util.Date;

public class DialogMetadata extends DialogMetadataD {
    public String id;
    public Date creationDate;


    public DialogMetadata(DialogMetadataD metadataD, String id) {
        this.creationDate = new Date(metadataD.createDate);
        this.createDate = metadataD.createDate;
        this.id = id;
    }
}
