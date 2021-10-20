package servertest;

/**
 *
 * @author ferens
 */
public class ServerTest {

    public static void main(String[] args) {

        userinterface.StandardIO myUI = new userinterface.StandardIO();
        server.Server myServer = new server.Server(5555, 100, myUI);
        usercommandhandler.UserCommandHandler myUserCommandHandler = new usercommandhandler.UserCommandHandler(myUI, myServer);
        myUI.setCommandHandler(myUserCommandHandler);
        Thread myUIthread = new Thread(myUI);
        myUIthread.start();     
        myUI.display("1:\tQuit\n"
                + "2:\tlisten\n"
                + "3:\tSet Port\n"
                + "4:\tGet Port\n"
                + "5:\tStop listening\n"
                + "6:\tStart Server Socket\n");
    }
}
