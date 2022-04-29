package server;

import model.Message;
import model.User;
import model.Type;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;

public class ServerThread implements Runnable{

    // declare executing socket
    private Socket socket;

    private Message clientObject;
    private User user;
    private String msgToHandle;

    private boolean isSucsess;

    //declare input/output stream
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    public ServerThread(Socket socket){
        this.socket = socket;
    }
    // method

    public void run() {

        System.out.println("incoming call from: " + socket.getInetAddress().getHostAddress());
        try{
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
//            clientHandler(socket, inputStream, outputStream);
           while (true){
               clientObject = (Message) inputStream.readObject();
               if(getStateToHandle(clientObject) == Type.EXIT){
                   System.out.println("client: " + socket.getRemoteSocketAddress() + "exit");
                   break;
               }
               switch (getStateToHandle(clientObject)){
                   case REGISTER -> {
                       setIsSuccess(false);
                        while (!isSucsess){
                            setUser(clientObject.getUser());

                        }
                   }
                   //end of case 1

                   case LOGIN -> {
                       setIsSuccess(false);
                       break;

                   }
                   //end of case 2

                   case ECHO -> {
                       setIsSuccess(false);
                       System.out.println("client:" + socket.getRemoteSocketAddress());
                       System.out.println("******ECHO*****");
                       while (true){
                           msgToHandle = clientObject.getMessage();
                           if(msgToHandle.isEmpty()){
                               System.out.println("error");
                               break;
                           } else if (msgToHandle.equalsIgnoreCase("E")) {
                               System.out.println("client: " + socket.getRemoteSocketAddress() + " exit ECHO");
                               break;

                           } else {
                                msgToHandle = msgToHandle.replaceAll("[^a-zA-Z0-9]","");
                               System.out.println("client: " + socket.getRemoteSocketAddress());
                               System.out.println("Standardized message:" + msgToHandle);
                                clientObject.setMessage(msgToHandle);

                                //send back to client
                               outputStream.writeObject(clientObject);
                               outputStream.flush();
                           }


                       }
                       break;

                   }
////                   end of case 3
//                   case EXIT -> {
//                       System.out.println("client: " + socket.getRemoteSocketAddress() + "exit.");
//                       break;
//                   }
                   //end of case 4

               }
           }


        } catch (IOException | ClassNotFoundException e){
            System.err.println("error. check your server");
            e.printStackTrace();
        }
    }

    // Getter and Setter
    public Message getClientObject() {
        return clientObject;
    }

    public void setClientObject(Message clientObject) {
        this.clientObject = clientObject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMsgToHandle() {
        return msgToHandle;
    }

    public void setMsgToHandle(String msgToHandle) {
        this.msgToHandle = msgToHandle;
    }

    public Type getStateToHandle(Message clientObject){
        return clientObject.getMsgType();
    }

    // check whether client processing has been handled successfully or not

    public boolean isSucsess() {
        return isSucsess;
    }

    public void setIsSuccess(boolean sucsess) {
        isSucsess = sucsess;
    }

    private boolean isSuccess(){
        boolean isSuccess = false;

        return isSuccess;
    }

}
