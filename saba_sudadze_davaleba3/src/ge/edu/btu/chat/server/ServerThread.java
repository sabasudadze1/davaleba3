package ge.edu.btu.chat.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket client;
    private  int clientID;
    private boolean running = true;

    public ServerThread(Socket client, int clientID) {
        this.client = client;
        this.clientID = clientID;
    }

    ObjectOutputStream objectOutputStream = null;

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("მიმდინარეობს მონაცემების წაკითხვა ...");
                ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
                String str = (String) objectInputStream.readObject();
                System.out.println("მიღებული შეტყობინება : " + str);

                String MyAnswer = null;
                switch (str) {
                    case "გამარჯობა":
                        MyAnswer = "მოგესალმებით, რით შემიძლია დაგეხმაროთ???";
                        break;
                    case "მაჩვენე კურსი":
                        MyAnswer = "3.0";
                        break;
                    case "მაჩვენე ფილიალები":
                        MyAnswer = "ჭავჭავაძის ფილიალი, დიდი დიღმის ფილიალი, ვარკეთილის ფილიალი.";
                        break;
                    case "ნახვამდის":
                        MyAnswer = "მადლობთ, რომ დაგვიკავშირდით, ნახვამდის!!!";
                        break;
                    default:
                        MyAnswer = "სამწუხაროდ ამ კითხვაზე პასუხი არ მაქვს :(";
                }
                System.out.println(MyAnswer);
                objectOutputStream = new ObjectOutputStream(client.getOutputStream());
                objectOutputStream.writeObject(MyAnswer);
            } catch (Exception KK) {
                System.out.println("მოხდა შეცდომა : " + KK);
            }
        }
    }
}

