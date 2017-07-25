import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JLabel;

public class chat extends JFrame {

    private JPanel contentPane;
    private String input=new String();
    private String output=new String();
    JButton button = new JButton("\u53D1\u9001");
    JTextArea textArea;
    JTextArea text = new JTextArea();
    private static int IntPort = 0;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        chat frame = new chat();
        frame.setVisible(true);
        while(IntPort==0){
        }
        frame.socket(IntPort);
    }

    /**
     * Create the frame.
     */
    public chat(){
        button = new JButton("\u53D1\u9001");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 539, 782, 214);
        contentPane.add(panel);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 31, 782, 157);
        scrollPane.validate();
        panel.add(scrollPane);

        textArea = new JTextArea();
        textArea.setFont(new Font("微软雅黑", Font.BOLD, 20));
        scrollPane.setViewportView(textArea);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 0, 782, 541);
        contentPane.add(scrollPane_1);


        text.setEditable(false);
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setFont(new Font("微软雅黑", Font.BOLD, 20));
        scrollPane_1.setViewportView(text);

        button.setBounds(669, 187, 113, 27);
        panel.add(button);

        JTextArea textArea_1 = new JTextArea();
        textArea_1.setBounds(560, 0, 106, 24);
        panel.add(textArea_1);

        JButton btnNewButton = new JButton("\u8FDE\u63A5");
        btnNewButton.addActionListener(e -> {
            if(e.getSource()==btnNewButton){
                String port = textArea_1.getText();
                IntPort = Integer.parseInt(port);
            }
        });
        btnNewButton.setBounds(669, -2, 113, 27);
        panel.add(btnNewButton);

        JLabel label = new JLabel("\u8BF7\u8F93\u5165\u5BF9\u65B9\u7684\u7AEF\u53E3\u53F7");
        label.setBounds(416, 2, 135, 22);
        panel.add(label);
        button.addActionListener(e -> {
            input = textArea.getText();
            textArea.setText("");
            text.append("\t\t\t\t"+input+"\n");
            input="";
        });

    }

    //链接服务器
    public void socket(int port){
        try {
            Socket client = new Socket("localhost", port);
            text.append("连接服务器成功"+"\n");
            BufferedReader accept = new BufferedReader(new InputStreamReader(client.getInputStream()));

            //发送到服务器  开一个新线程
            Thread socketOut  = new Thread(){
                public void run(){
                    try{
                        PrintWriter send;
                        send = new PrintWriter(client.getOutputStream(),true);
                        while(true){
                            while(input.equals("")){

                            }
                            send.println(input);
                            try{sleep(500);}
                            catch(InterruptedException e)
                            {}
                        }

                    }
                    catch (IOException e){
                        System.out.println(e);
                    }
                }
            };
            socketOut.start();
            //从服务器接收信息
            while(true){
                output = accept.readLine();
                text.append(output+"\n");
            }

        }
        catch (IOException g){
            System.out.println("IOException"+"type:e "+g);
        }
    }
}

