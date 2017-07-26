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
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JLabel;

public class chat extends JFrame {

    private JPanel contentPane;
    private String input=new String();
    private String output=new String();
    JButton button = new JButton("\u53D1\u9001");
    JTextArea textArea;
    JTextArea fromFriend = new JTextArea();
    JTextArea fromMe = new JTextArea();
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
        textArea.setLineWrap(true);
        scrollPane.setViewportView(textArea);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBorder(null);
        scrollPane_1.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane_1.setBounds(0, 0, 388, 541);
        contentPane.add(scrollPane_1);
        fromFriend.setTabSize(4);
        fromFriend.setBorder(null);
        fromFriend.setForeground(new Color(255, 160, 122));


        fromFriend.setEditable(false);
        fromFriend.setWrapStyleWord(true);
        fromFriend.setLineWrap(true);
        fromFriend.setFont(new Font("微软雅黑", Font.BOLD, 20));
        scrollPane_1.setViewportView(fromFriend);

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

        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane_2.setBorder(null);
        scrollPane_2.setBounds(388, 0, 394, 541);
        contentPane.add(scrollPane_2);

        fromMe = new JTextArea();
        fromMe.setTabSize(4);
        fromMe.setBorder(null);
        fromMe.setEditable(false);
        fromMe.setLineWrap(true);
        fromMe.setForeground(Color.MAGENTA);
        fromMe.setWrapStyleWord(true);
        fromMe.setFont(new Font("微软雅黑", Font.BOLD, 20));
        scrollPane_2.setViewportView(fromMe);
        button.addActionListener(e -> {
            input = textArea.getText();
            textArea.setText("");
            fromMe.append("我说："+input+"\n");
        });

    }

    //链接服务器
    public void socket(int port){
        try {
            Socket client = new Socket(InetAddress.getByName("localhost"), port);
            fromFriend.append("连接服务器成功"+"\n");
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
                            if(input.indexOf("\n")!=-1)
                            {
                                try{sleep(10);}
                                catch(InterruptedException e)
                                {}
                                String[] arys = input.split("\n");
                                input="";
                                for (int i=0;i<arys.length;i++)
                                {
                                    input+=arys[i];
                                    input+="π";
                                }
                                System.out.println(input);
                            }
                            send.println(input);
                            try{sleep(10);}
                            catch(InterruptedException e)
                            {}
                            input="";
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
                if (output.indexOf("π")!=-1)
                {
                    fromFriend.append("他(她)说："+"\n");
                    //System.out.println(output);
                    String[] arys = output.split("π");
                    for(int i=0;i<arys.length;i++)
                    {
                        fromFriend.append(arys[i]+"\n");
                    }
                }
                else if (!output.equals("")){
                    fromFriend.append("他(她)说："+output+"\n");
                }
                output="";
            }

        }
        catch (IOException g){
            fromFriend.append("端口号错误，服务器连接失败");
            System.out.println("IOException"+"type:e "+g);
        }
    }
}

