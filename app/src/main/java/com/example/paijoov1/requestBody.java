package com.example.paijoov1;

public class requestBody {
    final int user_id;
    final String access_token;

    public requestBody(int user_id, String access_token) {
        this.user_id = user_id;
        this.access_token = access_token;
    }
}
