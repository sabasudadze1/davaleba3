package ge.edu.btu.chat.client;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket;
        ObjectOutputStream objectOutputStream = null;
        boolean isStart = true;
        String stopper = "ნახვამდის";


        try {
            socket = new Socket("localhost", 3011);
            System.out.println("მიმდინარეობს კავშირის დამყარება ....");

            Scanner myObject = new Scanner(System.in);

            while (isStart) {
                System.out.println("გთხოვთ დასვით კითხვა: ");  String MyQuestion = myObject.nextLine();

                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(MyQuestion);

                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                String str = (String) objectInputStream.readObject();
                System.out.println(str);

                if(MyQuestion.equals(stopper)){  isStart = false; }
            }
            objectOutputStream.close();
            socket.close();
            System.out.println("კავშირის დასასრული.");

        } catch (Exception KK) {
            System.out.println("მოხდა შეცდომა : " + KK);
        }
    }
}
