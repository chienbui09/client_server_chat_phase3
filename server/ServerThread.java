package server;

import model.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;

public class ServerThread implements Runnable{

    // declare executing socket
    private Socket socket;

    Message message;

    //declare input/output stream
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;


    // method
    boolean isUserExisted(Message message){
        return
    }

    public void run() {

    }
}
