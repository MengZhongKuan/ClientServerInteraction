package client;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Client implements Runnable{
    
    InputStream input;
    OutputStream output;
    Socket clientSocket = null;
    servermessagehandler.ServerMessageHandler myServerMessageHandler;
    userinterface.StandardIO myUI;
    byte msg = ' ', clientCommand = ' ';
    int portNumber = 0;
    String host = " ";
    boolean isConnected = false;
    boolean stopThisThread = false;
    
    
    public Client(String host, int portNumber, userinterface.StandardIO myUI) {
        this.host = host;
        this.portNumber = portNumber;
        this.myUI = myUI;
        this.myServerMessageHandler = new servermessagehandler.ServerMessageHandler(this);
    }
    
    //Connect to server
    public void connectToServer(){
        try{
            clientSocket = new Socket(host, portNumber);
            isConnected = true;
            Thread clientServerThread = new Thread(this);
            clientServerThread.start();
        }
        catch (IOException e){
            System.err.println(e.toString());
            System.err.println("Cannot create ClientSocket, exiting program.");
            System.exit(0);
        }
        finally{
        }
    }
    
    //Disconnect using Server
    //Send 'd'
    public boolean disconnectFromServer(){
        char disconnectWord = 'd';
        sendMessageToServer(disconnectWord);
        byte confirmation = getMessageFromServer();
        if((char)confirmation == 'd'){ //If disconnect is successful
            return true;
        }
        else{ //If disconnect is unsuccessful
            return false;
        }     
    }
    
    //For 
    public byte getMessageFromServer() {
        try {
            input = clientSocket.getInputStream();
            msg = (byte) input.read();
        } catch (IOException e) {
            System.err.println("cannot read from socket; exiting program.");
            System.exit(0);
        } finally {
            return msg;
        }
    }
    
    // Sends msg to server that uses getMessageFromClient()
    public void sendMessageToServer(char msg) {
        try {
            output = clientSocket.getOutputStream();
            output.write(msg);
            output.flush();
        } catch (IOException e) {
            System.err.println("cannot send to socket; exiting program.");
            System.exit(0);
        } finally {
        }
    }
    
    public void setPort(int portNumber){
        this.portNumber = portNumber;
    }
    
    public int getPort(){
        return this.portNumber;
    }
    
    public void sendMessageToUI(String theString){
        myUI.display(theString);
    }
    
    @Override
    public void run(){
        if(isConnected){
            while (stopThisThread == false){
                try{
                    input = clientSocket.getInputStream();
                    msg = (byte) input.read();
                    myServerMessageHandler.handleServerMessage(msg);
                } 
                catch (IOException e){
                    if(e.toString().contains("Connection reset"))
                        sendMessageToUI("Connection was unexpectedly reset by remote host; stopping thread and disconnecting client: "+ clientSocket.getRemoteSocketAddress());
                    else
                        sendMessageToUI("Cannot read from socket; stopping thread and disconnecting client." + clientSocket.getRemoteSocketAddress() + "error message is: "+ e);
                    disconnectFromServer();
                    stopThisThread = true;
                } finally{
                }
            }
        }
    }
    
}
