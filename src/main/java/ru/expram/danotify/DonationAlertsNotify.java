package ru.expram.danotify;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

public class DonationAlertsNotify {

    private final String BASE_URL = "https://socket.donationalerts.ru:443";
    private String token;
    private Socket socket;
    private ObjectMapper objectMapper;

    public DonationAlertsNotify(String token) {
        this.token = token;
        this.socket = IO.socket(URI.create(BASE_URL));
        this.objectMapper = new ObjectMapper();
    }

    public void setupEvent(Donation event) {
        socket.on("donation", args -> {
            Donate donate = null;
            try {
                donate = objectMapper.readValue((String) args[0], Donate.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            event.onDonate(donate);
        });
    }


    public void connect() {
        try {
            socket.connect();
            JSONObject object = new JSONObject();
            object.put("token", token);
            object.put("type", "minor");
            socket.emit("add-user", object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
