package com.hqt.happyhostel.utils;

import com.google.gson.Gson;
import com.hqt.happyhostel.dao.HostelDAO;
import com.hqt.happyhostel.dto.Account;
import com.hqt.happyhostel.utils.GetHttpSessionConfigurator;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

@ServerEndpoint(value = "/push-noti-websocket", configurator = GetHttpSessionConfigurator.class)
public class PushNotiWebSocket {
    static Set<Session> users = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void handleOpen(Session session) {
        users.add(session);
    }

    @OnMessage
    public void handleMessage(String Message) throws IOException {
        JsonObject json = Json.createReader(new StringReader(Message)).readObject();
        HashMap<String, Object> map = new Gson().fromJson(json.toString(), HashMap.class);

        String sender = map.get("sender").toString();
        String receiver = map.get("receiver").toString();
        String hostelReceiverId = map.get("hostel_receiver_id").toString();
        String accountReceiverId = map.get("account_receiver_id").toString();
        String message = map.get("message").toString();

        switch (sender) {
            //người gửi là owner
            case "hostel_owner":
                switch (receiver) {
                    //người nhận là hostel
                    case "hostel":
                        ArrayList<Integer> renterList = new HostelDAO().getListRenterIdByHostelId(Integer.parseInt(hostelReceiverId));
                        for (Session session : users) {
                            Account account = (Account) session.getUserProperties().get("user");
                            for (int idList : renterList) {
                                if (account.getAccId() == idList) session.getBasicRemote().sendText(message);
                            }
                        }
                        break;


                    case "hostel_renter":
                        for (Session session : users) {
                            Account account = (Account) session.getUserProperties().get("user");
                            if (account.getAccId() == Integer.parseInt(accountReceiverId)) session.getBasicRemote().sendText(message);
                        }
                        break;
                }
                break;

            //người gửi là renter
            case "hostel_renter":
                switch (receiver) {
                    case "hostel_owner":
                        for (Session session : users) {
                            Account account = (Account) session.getUserProperties().get("user");
                            if (account.getAccId() == Integer.parseInt(accountReceiverId)) session.getBasicRemote().sendText(message);
                        }
                        break;
                }
                break;

            //người gửi là hệ thống
            case "system":
                switch (receiver) {
                    case "hostel_renter":
                        break;
                    case "hostel_owner":
                        break;
                }
                break;

            default:
                break;
        }

    }

    @OnClose
    public void handleClose(Session session) {
        users.remove(session);
    }

    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }

}
