package usercommandhandler;

public class UserCommandHandler {
   userinterface.StandardIO myUI;
    client.Client myClient;

    public UserCommandHandler(userinterface.StandardIO myUI, client.Client myClient) {
        this.myUI = myUI;
        this.myClient = myClient;
    }

    public void handleUserCommand(String theCommand) {

        switch (Integer.parseInt(theCommand)) {
            case 1: //Quit
                myUI.display("Exiting Program");
                System.exit(0);
                break;
            case 2: //Connect to Server
                myClient.connectToServer();
                myUI.display("Connected to Server");
                break;
            case 3: //Disconnect From Server
                myClient.disconnectFromServer();
                myUI.display("Disconnected from Server");
                break;
            case 4: //Get time
                char timeKeyword = 't';
                myClient.sendMessageToServer(timeKeyword); //Printed out in ServerMessageHandler
                break;
            default:
                break;
        }
    }
}
