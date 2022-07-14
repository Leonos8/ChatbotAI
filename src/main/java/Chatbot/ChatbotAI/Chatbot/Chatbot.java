package Chatbot.ChatbotAI.Chatbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Chatbot.ChatbotAI.GUI.GUI;
import Web.Website;

public class Chatbot 
{
	private static final boolean TRACE_MODE=false;
	public boolean trainBool=false;
	public boolean adding=false;
	
	static String botName="super";
	static String textLine="";
	static String response="";
	
	public static final File currDir=new File(".");
	public static final String absolutePath=currDir.getAbsolutePath();
	public static final String path=absolutePath.substring(0, absolutePath.length()-2);
	static File file=new File(path+File.separator+"src"+File.separator+"main"+
	File.separator+"resources"+File.separator+"prompts.txt");
	
	static HashMap<String, String> prompts=new HashMap<>(); //key, value
	
	public static ArrayList<String> chatHistory=new ArrayList<String>();
	
	/*public static void main(String[] args)
	{
		runChatbot();
	}*/
	
	public Chatbot()
	{
		this.trainBool=false;
		
		System.out.println(trainBool);
	}
	
	public void runChatbot()
	{
		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Scanner sc;
		try {
			sc = new Scanner(file);
			
			do
			{
				String line=sc.nextLine();
				
				String[] tmp = new String[2];
				tmp[0]=line.substring(0, line.indexOf(":"));
				tmp[1]=line.substring(line.indexOf(":")+1);
				
				prompts.put(tmp[0], tmp[1]);
			}while(sc.hasNextLine());
				
				String input=textLine.toLowerCase();
				
				if(input.equalsIgnoreCase("Open google"))
				{
					Website.openGoogle();
					
					response="Opening Google.com";
				}
				else if(input.equalsIgnoreCase("Open yahoo"))
				{
					Website.openYahoo();
					
					response="Opening Yahoo.com";
				}
				else if(input.equalsIgnoreCase("Open youtube"))
				{
					Website.openYoutube();
					
					response="Opening Youtube.com";
				}
				else if(input.equalsIgnoreCase("Open spotify"))
				{
					Website.openSpotify();
					
					response="Opening Spotify.com";
				}
				else if(input.equalsIgnoreCase("Open weather"))
				{
					Website.openWeather();
					
					response="Opening Weather.com";
				}
				else if(input.length()>=6 && input.substring(0, 6).equalsIgnoreCase("Lookup"))
				{
					Website.lookup(input.substring(7));
					
					response="Looking up: "+input.substring(7);	
				}
				else if(prompts.containsKey(input))
				{
					response=prompts.get(input);
				}
				else
				{
					
					response="Sorry, I do not understand. Would you like to teach me?";
					trainBool=true;
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GUI.textPane.setText(GUI.textPane.getText()+"Human: "+getInput()); //TODO change Human to saved value
		GUI.textPane.setText(GUI.textPane.getText()+"\n");
		
		GUI.textPane.setText(GUI.textPane.getText()+"Robot: "+getResponse());
		GUI.textPane.setText(GUI.textPane.getText()+"\n");
	}
	
	public void setInput(String textLine)
	{
		this.textLine=textLine;
	}
	
	public String getInput()
	{
		return textLine;
	}
	
	public void setResponse(String response)
	{
		this.response=response;
	}
	
	public String getResponse()
	{
		return response;
	}
	
	public void setAdding(boolean adding)
	{
		this.adding=adding;
	}
	
	public boolean getAdding()
	{
		return adding;
	}
	
	public void setTrainBool(boolean tb)
	{
		this.trainBool=tb;
	}
	
	public boolean getTrainBool()
	{
		return trainBool;
	}
	
	public void addInput(String input)
	{
		try {
			FileWriter fw=new FileWriter(file, true);
			
			if(chatHistory.size()>2)
			{
				fw.write(chatHistory.get(chatHistory.size()-6)+":"+input+"\n");
			}
			
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response="Ok, thank you";
		GUI.textPane.setText(GUI.textPane.getText()+"Robot: "+response);
		GUI.textPane.setText(GUI.textPane.getText()+"\n");
	}
	
	public void train()
	{
		chatHistory.add(textLine);
		chatHistory.add(response);
		
		//runChatbot();
		
		/*System.out.println(chatHistory.size());
		for(int i=0; i<chatHistory.size(); i++)
		{
			System.out.println(chatHistory.get(i));
		}*/
		if(chatHistory.size()>3)
		{
			response="What does "+chatHistory.get(chatHistory.size()-4)+" mean? Please tell me how i should"
					+ " respond.";
		}
		
		GUI.textPane.setText(GUI.textPane.getText()+"Human: "+getInput()); //TODO change Human to saved value
		GUI.textPane.setText(GUI.textPane.getText()+"\n");
		
		GUI.textPane.setText(GUI.textPane.getText()+"Robot: "+getResponse());
		GUI.textPane.setText(GUI.textPane.getText()+"\n");
		
		System.out.println("training");
	}
}
