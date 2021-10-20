/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermessagehandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServerMessageHandler {
    userinterface.StandardIO myUI;
    client.Client myClient;

    public ServerMessageHandler(client.Client myClient) {
        this.myClient = myClient;
    }
    
    //Assuming that serverMessage is only reading time
    public void handleServerMessage(byte msg) {
        char firstChar = (char) msg;
        String fullMessage = "";
        fullMessage = fullMessage + firstChar;
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        for (int i = 0; i < sdf.format(cal.getTime()).length() - 1; i++){ // -1 due to the 
            msg = myClient.getMessageFromServer();
            char currentMessageChar = (char)msg;
            fullMessage = fullMessage + currentMessageChar;
        }
        myClient.sendMessageToUI(fullMessage);
    }
}
