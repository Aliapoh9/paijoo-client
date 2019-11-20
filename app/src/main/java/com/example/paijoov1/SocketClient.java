package com.example.paijoov1;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;

import tech.gusavila92.websocketclient.WebSocketClient;

public class SocketClient{
    private WebSocketClient webSocketClient;
    private ArrayList<Conversation.Messages> mArr;

    public void createWebSocketClient(int id, ArrayList<Conversation.Messages> mar) {
        URI uri;
        try {
            uri = new URI("ws://10.0.2.2:8888/conversation/" + id);
            mArr = mar;
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen() {
                System.out.println("onOpen");
            }

            @Override
            public void onTextReceived(String message) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    Conversation.Messages m = objectMapper.readValue(message, Conversation.Messages.class);
                    mArr.add(m);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onBinaryReceived(byte[] data) {
                System.out.println("onBinaryReceived");
            }

            @Override
            public void onPingReceived(byte[] data) {
                System.out.println("onPingReceived");
            }

            @Override
            public void onPongReceived(byte[] data) {
                System.out.println("onPongReceived");
            }

            @Override
            public void onException(Exception e) {
                System.out.println("Exception");
                System.out.println(e.getMessage());
            }

            @Override
            public void onCloseReceived() {
                System.out.println("onCloseReceived");
            }
        };


        webSocketClient.setConnectTimeout(10000);
        //webSocketClient.addHeader("Origin", "ws://developer.example.com/conversation/1");
        webSocketClient.enableAutomaticReconnection(5000);
        webSocketClient.connect();
    }

    public void sendMessage(Conversation.Messages m){
        ObjectMapper mapper = new ObjectMapper();
        String json = "error";
        try {
            json = mapper.writeValueAsString(m);
            System.out.println("ResultingJSONstring = " + json);
        } catch (IOException e) {
            System.out.println("Error sending playEndOfStream message " + e);
        }
        //webSocketClient.send(json);


    }
    public void close(){
        webSocketClient.close();
    }

    public static void main(String[] args) {
        SocketClient s = new SocketClient();
        ArrayList<Conversation.Messages> ax = new ArrayList<Conversation.Messages>();
        s.createWebSocketClient(1, ax);
        s.sendMessage(new Conversation.Messages(1, 1, 1, 1
                , new Conversation.Messages.TextContent(1, "Bobo Boestar"), false
                , false, Calendar.getInstance().toString(), 2));
    }
}