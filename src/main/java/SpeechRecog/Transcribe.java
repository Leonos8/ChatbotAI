package SpeechRecog;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class Transcribe 
{
	//Logger
	public Logger logger=Logger.getLogger(getClass().getName());
	
	public String result;
	
	Thread speechThread;
	Thread resourcesThread;
	
	public LiveSpeechRecognizer recognizer;
	
	public Transcribe()
	{
		//Loading message
		logger.log(Level.INFO, "Loading...\n");
		
		//Configuration
		Configuration config=new Configuration();
		
		//Load models
		config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		config.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		config.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
		
		try {
			recognizer=new LiveSpeechRecognizer(config);
		} catch(IOException ex)
		{
			logger.log(Level.SEVERE, null, ex);
		}
		
		//Start recog process
		recognizer.startRecognition(true);
		
		//Start threads
		startSpeechThread();
		startResourcesThread();
	}
	
	protected void startSpeechThread()
	{
		if(speechThread!=null && speechThread.isAlive())
		{
			return;
		}
		
		//initialize
		speechThread=new Thread(()->{
			logger.log(Level.INFO, "You can start to speak...\n");
			
			try {
				while(true)
				{
					//This method will return when the end of speech is reached
					SpeechResult speechResult=recognizer.getResult();
					
					if(speechResult!=null)
					{
						result=speechResult.getHypothesis();
						System.out.println("You said: ["+result+"]\n");
					}
					else
						logger.log(Level.INFO, "I cant understand what you said.\n");
				}
			} catch(Exception ex)
			{
				logger.log(Level.WARNING, null, ex);
			}
			
			logger.log(Level.INFO, "speechThread has exited...");
		});
		
		//Start
		speechThread.start();
	}
	
	//Starts a thread that checks if the necessary resources are available
	protected void startResourcesThread()
	{
		if(resourcesThread!=null && resourcesThread.isAlive())
		{
			return;
		}
		
		//Initialize
		resourcesThread=new Thread(()->{
			try {
				
				//Detect if microphone is available
				while(true)
				{
					if(AudioSystem.isLineSupported(Port.Info.MICROPHONE))
					{
						//logger.log(Level.INFO, "Microphone is available\n");
					}
					else
					{
						//logger.log(Level.INFO, "Microphone is not available\n");
					}
					
					Thread.sleep(350);
				}
			} catch(Exception ex)
			{
				logger.log(Level.WARNING, null, ex);
				resourcesThread.interrupt();
			}
		});
		
		//Start
		resourcesThread.start();
	}
	
	public static void main(String[] args)
	{
		new Transcribe();
	}
}
