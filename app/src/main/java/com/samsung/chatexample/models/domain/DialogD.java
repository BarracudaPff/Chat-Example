package com.samsung.chatexample.models.domain;

import java.util.Map;
import java.util.Objects;

public class DialogD {
    public DialogMetadataD metadata;
    public Map<String, MessageD> messages;

    public DialogD() {
    }

    public DialogD(DialogMetadataD metadata, Map<String, MessageD> messages) {
        this.metadata = metadata;
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DialogD dialogD = (DialogD) o;
        return Objects.equals(metadata, dialogD.metadata) && Objects.equals(messages, dialogD.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metadata, messages);
    }

    @Override
    public String toString() {
        return "DialogD{" +
                "metadata=" + metadata +
                ", messages=" + messages +
                '}';
    }
}
