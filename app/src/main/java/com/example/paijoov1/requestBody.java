package com.example.paijoov1;

public class requestBody<C extends ContentRequestBody>{
    private int author_id;
    private int recipient_id;
    private int content_type;
    private String created_at;
    private C content;
    private int conversation_id;

    public requestBody(int author_id, int recipient_id, int content_type, String created_at, C content, int conversation_id) {
        this.author_id = author_id;
        this.recipient_id = recipient_id;
        this.content_type = content_type;
        this.created_at = created_at;
        this.content = content;
        this.conversation_id = conversation_id;
    }
}
