package Chatbot.ChatbotAI.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Chatbot.ChatbotAI.Chatbot.Chatbot;

public class GUI implements ActionListener
{
	JFrame frame;
	
	JPanel panel;
	
	JTextField inputField;
	
	JButton sendButton;
	
	int width=600;
	int height=400;
	
	public GUI()
	{
		createFrame();
		
		createPanel();
		
		//createInputField();
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
	            	Chatbot cb=new Chatbot();
	    			cb.setInput(inputField.getText());
	    			cb.runChatbot();
	    			inputField.setText("");
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
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==sendButton)
		{
			Chatbot cb=new Chatbot();
			cb.setInput(inputField.getText());
			cb.runChatbot();
			inputField.setText("");
		}
	}
}
