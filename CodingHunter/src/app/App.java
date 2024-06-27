
package app;


import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import controller.ControlGeral;


public class App{


	public static void main(String[] args) {


		try {
			new ControlGeral();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		
	}
}
