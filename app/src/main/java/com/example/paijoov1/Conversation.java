package com.example.paijoov1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Calendar;

public class Conversation {
    @SerializedName("messages")
    @Expose
    private ArrayList<Messages> messages = null;
    @SerializedName("lastMessageTimestamp")
    @Expose
    private String lastMessageTimestamp;
    @SerializedName("conversation_id")
    @Expose
    private Integer Conversation_id;



    public static class Messages {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("author_id")
        @Expose
        private Integer author_id;
        @SerializedName("recipient_id")
        @Expose
        private Integer recipient_id;
        @SerializedName("content_type")
        @Expose
        private Integer content_type;
        @SerializedName("content")
        @Expose
        private TextContent content;
        @SerializedName("seen")
        @Expose
        private Boolean seen;
        @SerializedName("received")
        @Expose
        private Boolean received;
        @SerializedName("created_at")
        @Expose
        private String created_at;
        @SerializedName("conversation_id")
        @Expose
        private Integer conversation_id;


        public static class TextContent {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("content")
            @Expose
            private String content;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(int author_id) {
            this.author_id = author_id;
        }

        public int getRecipient_id() {
            return recipient_id;
        }

        public void setRecipient_id(int recipient_id) {
            this.recipient_id = recipient_id;
        }

        public int getContent_type() {
            return content_type;
        }

        public void setContent_type(int content_type) {
            this.content_type = content_type;
        }

        public TextContent getContent() {
            return content;
        }

        public void setContent(TextContent content) {
            this.content = content;
        }

        public Boolean getSeen() {
            return seen;
        }

        public void setSeen(Boolean seen) {
            this.seen = seen;
        }

        public Boolean getReceived() {
            return received;
        }

        public void setReceived(Boolean received) {
            this.received = received;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getConversation_id() {
            return conversation_id;
        }

        public void setConversation_id(int conversation_id) {
            this.conversation_id = conversation_id;
        }

        @Override
        public String toString() {
            return "Messages{" +
                    "id=" + id +
                    ", author_id=" + author_id +
                    ", recipient_id=" + recipient_id +
                    ", content_type=" + content_type +
                    ", content=" + content +
                    ", seen=" + seen +
                    ", received=" + received +
                    ", created_at=" + created_at +
                    ", conversation_id=" + conversation_id +
                    '}';
        }

    }


    public ArrayList<Messages> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Messages> messages) {
        this.messages = messages;
    }

    public String getLastMessageTimestamp() {
        return lastMessageTimestamp;
    }

    public void setLastMessageTimestamp(String lastMessageTimestamp) {
        this.lastMessageTimestamp = lastMessageTimestamp;
    }

    public Integer getConversationId() {
        return Conversation_id;
    }

    public void setConversationId(Integer conversationId) {
        this.Conversation_id = conversationId;
    }
}
