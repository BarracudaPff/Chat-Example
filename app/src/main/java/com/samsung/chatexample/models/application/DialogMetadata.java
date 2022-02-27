package com.samsung.chatexample.models.application;

import com.samsung.chatexample.models.domain.DialogMetadataD;

import java.util.Date;

public class DialogMetadata extends DialogMetadataD {
    public String id;
    public Date createDate;

    public DialogMetadata(DialogMetadataD metadataD, String id) {
        this.createDate = new Date(metadataD.createDate);
        this.id = id;
    }
}
