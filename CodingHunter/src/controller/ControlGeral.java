package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import model.MusicPlayer;

public class ControlGeral implements ActionListener{
	
	
	Control controle;
	MusicPlayer controladorMusica;
	private boolean som;
	
	
	public ControlGeral() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		super();
		
		controle = new Control();
		controladorMusica = new MusicPlayer();
		
		
		controle.start();
		controladorMusica.start();
		controladorMusica.tocarMusicaMenu(true);
		controladorMusica.tocarSomComer();
		
		controle.getTelaPrincipal().getBttnsom().addActionListener(this);
		controle.getTelaPrincipal().getBttnComer().addActionListener(this);
		
		som = true;
		
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(controle.getTelaPrincipal().getBttnsom())) {
			
			if(som) {
				controle.getTelaPrincipal().getBttnsom().setIcon(controle.getRecursos().getSomOff());
				som = false;
				controladorMusica.getClipMenu().stop();
				
			}else {
				controle.getTelaPrincipal().getBttnsom().setIcon(controle.getRecursos().getSomOn());
				som = true;
				controladorMusica.getClipMenu().start();
				}
			
		}
	}

}
