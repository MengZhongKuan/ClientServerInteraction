package clienttest;

/**
 *
 * @author kuanm
 */
public class clienttest {
    public static void main(String[] args){
        userinterface.StandardIO myUI = new userinterface.StandardIO();
        client.Client myClient = new client.Client("localhost", 5555, myUI);
        usercommandhandler.UserCommandHandler myUserCommandHandler = new usercommandhandler.UserCommandHandler(myUI, myClient);
        myUI.setCommandHandler(myUserCommandHandler);
        Thread myUIthread = new Thread(myUI);
        myUIthread.start();     
        myUI.display("1:\tQuit\n"
                + "2:\tConnect to Server\n"
                + "3:\tDisconnect from Server\n"
                + "4:\tGet Time\n");
    }
}
