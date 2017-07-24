//coding=UTF-8
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class socket {
    static  String input = "";
    static String output= "";
    public static void main(String[] args){
        try{
            Socket client = new Socket("localhost",8000);
            BufferedReader accept = new BufferedReader(new InputStreamReader(client.getInputStream() ));
            PrintWriter send = new PrintWriter(client.getOutputStream(),true);
            while(true){
                Scanner in = new Scanner(System.in);
                System.out.println("请输入你想输入的话：");
                input = in.nextLine();
                if(input.equals("BYE"))
                    System.exit(0);
                send.println(input);
                output=accept.readLine();
                System.out.println(output);
            }
        }
        catch (IOException e){
            System.out.println("IOException"+"type:e "+e);
        }
    }

}
