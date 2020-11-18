package ge.edu.btu.chat.server;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public  static  void main(String[] args) {
        ServerSocket serverSocket; Socket socket = null;
        ObjectInputStream objectInputStream; String str;
        int clientId = 0; ServerThread serverThread;


        try {
            serverSocket = new ServerSocket(3011);

            while (true) {
                socket = serverSocket.accept();
                serverThread = new ServerThread(socket, clientId);
                serverThread.start(); clientId++;
            }

        } catch (Exception KK) {
            System.out.println("მოხდა შეცდომა :  " + KK);
        }
    }
}
