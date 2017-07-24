import java.io.*;
import java.net.*;
import java.util.*;

public class server {
    // Main method
    public static void main(String[] args) {
        try {
            ServerSocket myserver = new ServerSocket(8000);
            Socket listening = myserver.accept();

            BufferedReader input = new BufferedReader(new InputStreamReader(listening.getInputStream()));

            PrintWriter output = new PrintWriter(listening.getOutputStream(), true);

            while (true) {
                String getWords = "";
                String pushWords = "";
                getWords = input.readLine();
                System.out.println(getWords);
                System.out.println("请输入你想说的话");
                Scanner in = new Scanner(System.in);
                pushWords = in.nextLine();
                if(pushWords.equals("BYE"))
                    System.exit(0);
                output.println(pushWords);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
