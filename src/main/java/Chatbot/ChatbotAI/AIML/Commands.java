package Chatbot.ChatbotAI.AIML;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Commands 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		searchInputs("GOOGLE");
	}
	public static void searchInputs(String searchValue) throws FileNotFoundException
	{
		File currDir=new File(".");
		
		String path=currDir.getAbsolutePath();
		path=path.substring(0, path.length()-2);
		String resourcesPath=path+File.separator+"src"+File.separator+"main"+File.separator+"resources"
		+File.separator+"bots"+File.separator+"super"+File.separator+"aiml"+File.separator;
		
		//System.out.println(resourcesPath);
		
		File directoryPath=new File(resourcesPath);
		
		String[] contents=directoryPath.list();
		
		Scanner sc;
		File file;
		
		for(int i=0; i<contents.length; i++)
		{
			//System.out.println(contents[i]);
			file=new File(resourcesPath+contents[i]);
			sc=new Scanner(file);
			//System.out.println(sc.nextLine());
			
			while(sc.hasNext())
			{
				//System.out.println(sc.nextLine());
				if(sc.nextLine().contains(searchValue.toUpperCase()))
				{
					System.out.println(resourcesPath+contents[i]);
				}
			}
			//System.out.println();
			//System.out.println();
			//System.out.println();
		}
	}
}
