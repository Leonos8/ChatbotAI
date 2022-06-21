package Chatbot.ChatbotAI.GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
	static JButton micButton;
	
	JTextPane textPane;
	
	int width=600;
	int height=400;
	
	boolean micOn=false;
	
	public GUI()
	{
		createFrame();
		
		createPanel();
		
		frame.validate();
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
		createMicButton();
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
		
		JScrollPane scrollPane=new JScrollPane(textPane);
		scrollPane.setBounds(15,15,width-45,height-100);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setVisible(true);
		
		panel.add(scrollPane);
	}
	
	public void createMicButton()
	{
		micButton=new JButton();
		
		micButton.setBounds(70, height-70, 25, 25);
		//micButton.setBounds(0, 0, 400, 400);
		
		try {
			setMicImageIcon(micOn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		micButton.addActionListener(this);
		micButton.setVisible(true);
		
		panel.add(micButton);
	}
	
	private static ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) 
	{
	    Image img = icon.getImage();  
	    Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  
	    		java.awt.Image.SCALE_SMOOTH);  
	    
	    return new ImageIcon(resizedImage);
	}
	
	private static void setMicImageIcon(boolean micOn) throws IOException
	{
		File currDir=new File(".");
		
		String path=currDir.getAbsolutePath();
		path=path.substring(0, path.length()-2);	
		
		String imagePath=path+File.separator+"src"+File.separator+"main"
				+File.separator+"resources"+File.separator+"Images"+File.separator;
		
		Image micOffImg=ImageIO.read(new File(imagePath+"MicOff.png"));
	    Image micOnImg=ImageIO.read(new File(imagePath+"MicOn.png"));
	    
	    ImageIcon micOffImgIcon=resizeIcon(new ImageIcon(micOffImg), 20, 20);
	    ImageIcon micOnImgIcon=resizeIcon(new ImageIcon(micOnImg), 20, 20);
	    
	    if(micOn)
	    {
	    	micButton.setIcon(micOnImgIcon);
	    }
	    else
	    {
	    	micButton.setIcon(micOffImgIcon);
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==sendButton)
		{
			runCB();
		}
		
		if(e.getSource()==micButton)
		{
			if(micOn)
			{
				micOn=false;
				
				try {
					setMicImageIcon(micOn);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else
			{
				micOn=true;
				
				try {
					setMicImageIcon(micOn);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
