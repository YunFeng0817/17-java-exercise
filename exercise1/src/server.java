import java.io.*;
import java.net.*;
import java.util.*;

public class server {
    // Main method
    public static void main(String[] args) {
        try {
            ServerSocket myserver = new ServerSocket(8000);
            ServerSocket myAnotherServer = new ServerSocket(7000);
            Socket listening = myserver.accept();
            Socket listen = myAnotherServer.accept();

            BufferedReader input = new BufferedReader(new InputStreamReader(listening.getInputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(listen.getInputStream()));

            PrintWriter output = new PrintWriter(listening.getOutputStream(), true);
            PrintWriter out = new PrintWriter(listen.getOutputStream(),true);

            Thread onetoanother = new Thread(){
              public void run(){
                  try{
                      String getWords = "";
                      while(true) {
                          getWords = input.readLine();
                          if (getWords.equals("BYE"))
                          {
                              System.exit(0);
                          }
                          if(!getWords.equals("")){
                              out.println(getWords);
                          }
                      }
                  }
                  catch (IOException e){
                      System.out.println(e);
                  }

              }
            };

            onetoanother.start();
            while (true) {
                String pushWords = "";
                pushWords = in.readLine();
                if (pushWords.equals("BYE"))
                {
                    System.exit(0);
                }
                if(!pushWords.equals("")){
                    output.println(pushWords);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
