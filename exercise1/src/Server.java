import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    // Main method
    public static void main(String[] args) {
        try {
            ServerSocket myserver = new ServerSocket(8000);
            Socket listening = myserver.accept();

            BufferedReader input = new BufferedReader(new InputStreamReader(listening.getInputStream()));

            PrintWriter output = new PrintWriter(listening.getOutputStream(), true);

            while (true) {
                String a = "";
                String b = "";
                a = input.readLine();
                System.out.println(a);
                System.out.println("请输入你想说的话");
                Scanner in = new Scanner(System.in);
                b = in.nextLine();
                output.println(b);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

}
