package com.example.paijoov1.Chat_Chin;

public class Message {

    private String sender;
    private String content;

    public Message(String s, String c)
    {
        this.sender = s;
        this.content = c;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String get_content()
    {
        return content;
    }

    public String get_sender()
    {
        return sender;
    }

}
