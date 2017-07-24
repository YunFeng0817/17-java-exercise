import javax.swing.*;
import java.awt.*;

public class draw extends JFrame{
    public static void main(String[] args){
        draw frame = new draw();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
    }
    private int rand=100;
    private int start=50;
    private  JPanel pane = new JPanel();

    public void paint(Graphics g){
        g.setColor(new Color(0,0,0));
        g.drawOval(start, start, rand, rand);//绘制第1个圆形
        g.setColor(new Color(255,0,0));
        g.drawOval(start+rand-10, start, rand, rand);//绘制第2个圆形
        g.setColor(new Color(255,255,0));
        g.drawOval(start+2*(rand-10), start, rand, rand);//绘制第3个圆形
        g.setColor(new Color(0,255,0));
        g.drawOval(start+rand-50, start+60, rand, rand);//绘制第4个圆形
        g.setColor(new Color(0,0,255));
        g.drawOval(start+2*rand-50, start+60, rand, rand);//绘制第5个圆形
    }



}
