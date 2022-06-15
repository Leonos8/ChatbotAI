package Chatbot.ChatbotAI.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import Chatbot.ChatbotAI.Chatbot.Chatbot;

public class GUI implements ActionListener
{
	JFrame frame;
	
	JPanel panel;
	
	JTextField inputField;
	
	JButton sendButton;
	
	JTextPane textPane;
	
	int width=600;
	int height=400;
	
	public GUI()
	{
		createFrame();
		
		createPanel();
	}
	
	public void createFrame()
	{
		frame=new JFrame("Chatbot");
		
		frame.setBounds(0, 0, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void createPanel()
	{
		panel=new JPanel();
		
		panel.setBackground(Color.cyan);
		panel.setVisible(true);
		panel.setLayout(null);
		
		frame.getContentPane().add(panel);
		
		createInputField();
		createSendButton();
		createTextPane();
	}
	
	public void createInputField()
	{
		inputField=new JTextField();
		
		inputField.setBounds(100, height-70, width-200, 25);
		inputField.setVisible(true);

		inputField.addKeyListener(new KeyAdapter() 
		{
	        public void keyPressed(KeyEvent e) 
	        {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER) 
	            {
	            	runCB();
	            }
	        }
		});
		
		panel.add(inputField);
	}
	
	public void createSendButton()
	{
		sendButton=new JButton("Send");
		
		sendButton.setBounds(width-95, height-70, 75, 25);
		sendButton.addActionListener(this);
		sendButton.setVisible(true);
		
		panel.add(sendButton);
	}
	
	public void createTextPane()
	{
		textPane=new JTextPane();
		textPane.setEditable(false);
		textPane.setVisible(true);
		textPane.setBounds(15, 15, width-45, height-100);
		
		JScrollPane scrollPane=new JScrollPane(textPane);
		scrollPane.setBounds(0,15,20,textPane.getHeight());
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setVisible(true);
		
		//panel.add(textPane);
		panel.add(scrollPane);
	}
	
	public void runCB()
	{
		Chatbot cb=new Chatbot();
		cb.setInput(inputField.getText());
		cb.runChatbot();
		
		textPane.setText(textPane.getText()+"Human: "+cb.getInput()); //TODO change Human to saved value
		textPane.setText(textPane.getText()+"\n");
		
		textPane.setText(textPane.getText()+"Robot: "+cb.getResponse());
		textPane.setText(textPane.getText()+"\n");
		
		inputField.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==sendButton)
		{
			runCB();
		}
	}
}
