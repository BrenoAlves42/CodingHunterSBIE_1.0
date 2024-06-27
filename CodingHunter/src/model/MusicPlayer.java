package model;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class MusicPlayer extends Thread{

	private AudioInputStream musica_menu, matou_coelho, respawnou_coelho;
    private Clip clipMenu, clipMatou, clipRespawn;
    private FloatControl gainControl;
 
    public MusicPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		super();
		musica_menu = AudioSystem.getAudioInputStream(MusicPlayer.class.getResource("/musica_menu.wav"));
		clipMenu = AudioSystem.getClip();
		
		matou_coelho = AudioSystem.getAudioInputStream(MusicPlayer.class.getResource("/comer.wav"));
		clipMatou = AudioSystem.getClip();
	
	}


	public void tocarMusicaMenu(Boolean tocar) throws LineUnavailableException, IOException {

		
		if(tocar == true) {
			
			clipMenu.open(musica_menu);
			gainControl = (FloatControl) clipMenu.getControl(FloatControl.Type.MASTER_GAIN);
	    	gainControl.setValue(-20.0f); 
		    clipMenu.start(); 
		    clipMenu.loop(99);
		}   
               
    }
	
	public void tocarSomComer() throws LineUnavailableException, IOException {

		
		
			
			clipMatou.open(matou_coelho);
			gainControl = (FloatControl) clipMatou.getControl(FloatControl.Type.MASTER_GAIN);
	    	gainControl.setValue(-20.0f); 
	    	
		  
               
    }
    
	
	public void run(){
		try {
			
		
	        	    
	        	
	       
	        
	    } catch(Exception ex) {
	       JOptionPane.showMessageDialog(null, "Musica não encontrada");
	    }
	}


	public AudioInputStream getAudio() {
		return musica_menu;
	}


	public Clip getClipMenu() {
		return clipMenu;
	}


	public FloatControl getGainControl() {
		return gainControl;
	}


	public AudioInputStream getMusica_menu() {
		return musica_menu;
	}


	public AudioInputStream getMatou_coelho() {
		return matou_coelho;
	}


	public AudioInputStream getRespawnou_coelho() {
		return respawnou_coelho;
	}


	public Clip getClipMatou() {
		return clipMatou;
	}


	public Clip getClipRespawn() {
		return clipRespawn;
	}
	
}
