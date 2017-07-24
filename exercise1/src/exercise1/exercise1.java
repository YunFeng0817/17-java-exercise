
package exercise1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class exercise1 {
	/**
	 *
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		textarea frame = new textarea();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.show();
	}
}

class textarea extends JFrame{
	private String content = new String();
	private JTextArea text=new JTextArea("write words here",10,23);
	private JButton analyse=new JButton("统计信息");;
	private JLabel result=new JLabel();
	private JScrollPane js= new JScrollPane(text);
	private final int width=600,height=400;
	public int rows=0,words=0,chars=0;
	public textarea(){
		setTitle("统计字段信息");
		setSize(width, height);

		JPanel container = new JPanel();
		text.setLineWrap(true);
		js.validate();
		ActionListener listener = new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				if(event.getSource()==analyse){
					content=text.getText();
					chars=content.length();
					String[] word = content.split(" ");
					words=word.length;
					String[] row = content.split("\n");
					rows=row.length;
					result.setText("这段文字共有"+rows+"行 "+words+"个单词 "+chars+"个字符");
				}
			}
		};
		analyse.addActionListener(listener);
		container.add(analyse);
		container.add(result);
		getContentPane().setLayout(null);
		getContentPane().add(container);
		getContentPane().add(js);
		js.setBounds(width/4,height/9,width/2,height/3);
		container.setBounds(width*2/9, height*5/9, width/2, height/3);
	}
}