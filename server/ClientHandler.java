package server;

import client.Client;
import com.mysql.cj.x.protobuf.MysqlxNotice;
import model.Message;
import model.Type;
import model.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler {
    private User user;
    private Message clientObject;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private String msgToHandle;
    Socket socket;
    public ClientHandler (Socket socket, ObjectInputStream inputStream, ObjectOutputStream outputStream){
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }
    public Type getActionFromRequest(Message clientObject){
        return clientObject.getMsgType();
    }

    public void execute(){


        while (true){

        }
    }

    // Getter and Setter
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message getClientMsg() {
        return clientObject;
    }

    public void setClientMsg(ObjectInputStream inputStream) {
        this.clientObject = inputStream.readObject();
    }

    public String getMsgToHandle() {
        return msgToHandle;
    }

    public void setMsgToHandle(String msgToHandle) {
        this.msgToHandle = msgToHandle;
    }
}
