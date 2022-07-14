package Web;

import java.io.IOException;

public class Website 
{
	public static void lookup(String lookupString)
	{
		String url="https://www.google.com/search?q=";
		if(!lookupString.contains(" "))
		{
			url+=lookupString;
		}
		else
		{
			String[] lookupArray=lookupString.split(" ");
			
			url+=lookupArray[0];
			
			for(int i=1; i<lookupArray.length; i++)
			{
				url+="+"+lookupArray[i];
			}
		}
		
		
		try {
		java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (IOException ex) {
		ex.printStackTrace();
		}
	}
	
	public static void openGoogle()
	{
		String url="https://www.google.com";
		try {
		java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (IOException ex) {
		ex.printStackTrace();
		}
	}
	
	public static void openSpotify()
	{
		String url="https://www.spotify.com";
		try {
		java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (IOException ex) {
		ex.printStackTrace();
		}
	}
	
	public static void openWeather()
	{
		String url="https://weather.com/weather/today/"
				+ "l/0a79ef77b04fbecf5d6b5112daa1ee4ca9cfbbbae5abe499ba9646677068a0c2";
		try {
		java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (IOException ex) {
		ex.printStackTrace();
		}
	}
	
	public static void openYahoo()
	{
		String url="https://www.yahoo.com";
		try {
		java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (IOException ex) {
		ex.printStackTrace();
		}
	}

	public static void openYoutube() 
	{
		String url="https://www.youtube.com";
		try {
		java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (IOException ex) {
		ex.printStackTrace();
		}
	}
}
