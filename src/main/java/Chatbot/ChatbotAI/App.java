package Chatbot.ChatbotAI;

import javax.swing.SwingUtilities;

import Chatbot.ChatbotAI.GUI.GUI;

public class App {
	private static final boolean TRACE_MODE = false;
	static String botName = "super";

	public static void main(String[] args) throws Exception
	{
		//Chatbot cb=new Chatbot();
		
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				GUI gui=new GUI();
			}
		});
	}
}