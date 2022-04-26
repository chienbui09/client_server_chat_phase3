package client;

import model.Message;

import java.net.Socket;
import java.util.Scanner;

public class SendingHandle implements Runnable{
    Socket socket;
    Message clientMsg;
    Scanner scanner;

    String selector;
    //Constructor
    public SendingHandle(){
        socket = new Socket();
        clientMsg = new Message();
        Scanner = new Scanner(System.in);
    }

    public SendingHandle(Socket socket, Message clientMsg, Scanner scanner) {
        this.socket = socket;
        this.clientMsg = clientMsg;
        this.scanner = scanner;
    }

    public Message getClientMsg() {
        return clientMsg;
    }

    public void setClientMsg(Message clientMsg) {
        this.clientMsg = clientMsg;
    }

    @Override
    public void run() {
        System.out.println("******************START******************");
        System.out.println("\n\n");
        System.out.println("chose the action:");
        System.out.println("\t 1. REGISTER" +
                            "\n\t 2. LOGIN" +
                            "\n\t 3. ECHO" +
                            "\n\t 4. EXIT"
                );
    }
}
