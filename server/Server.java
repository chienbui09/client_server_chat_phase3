package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
//        static int clientID = 0;
//        static String g_mesage = null;
        static int numOfThread = 10;
        static int serverPort = 25000;

    public static void main(String[] args)  throws Exception {
            ServerSocket listener;
//            Socket serverSocket;

            try {
                listener = new ServerSocket(serverPort);
                System.out.println("server is on");
                ExecutorService threadPool = Executors.newFixedThreadPool(numOfThread);


                while (true){
                    threadPool.execute(new ServerThread(listener.accept()));
                }
            } catch (IOException e){
                System.err.println("cant bind to port\nport is in use");
                e.printStackTrace();
            }
        }
}
