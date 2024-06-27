package controller;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import model.Recursos;
import view.TelaPrincipal;
import view.TelaPopUp;
import view.TelaMenu;

public class Control extends Thread implements ActionListener{

	private TelaPrincipal telaPrincipal;
	private TelaMenu telaMenu;
	private TelaPopUp popup;
	private Recursos recursos;
	private BufferedImage fundoFase01, fundoFase01_2;
	private int player_posX, player_posY, sprite_atual, posDestinoX, posDestinoY, cont_mov, cont_passos, dormir,
	contPassosFolego, spriteAtualDesc, spriteAtualCoelho, coelhoPosX, coelhoPosY, pmX1, pmX2, pmY1, pmY2, cont_marker, cont_comandos, faseAtual;
	private boolean movendo, continuouMovimento, progressBarCheck, desfazerTudo, unicoVirar, mostrarMarker, dormindo, comeu, visualizou, jaMoveu;
	private String dir_atual;
	private ArrayList<ImageIcon> seq_Comandos;
	private ArrayList<String> seq_linhas, direcoes;
	private ArrayList<Integer> seq_passos;

	public Control() {
		recursos = new Recursos();
		telaPrincipal = new TelaPrincipal();
		telaMenu = new TelaMenu();
		popup = new TelaPopUp();
		fundoFase01 = new BufferedImage(672, 512, BufferedImage.TYPE_4BYTE_ABGR);
		fundoFase01_2 = new BufferedImage(320, 512, BufferedImage.TYPE_4BYTE_ABGR);
		player_posX = recursos.getPersonagem().posX;
		player_posY = recursos.getPersonagem().posY;
		posDestinoX = 0; 
		posDestinoY = 0;
		sprite_atual = recursos.getPersonagem().aparencia;
		movendo = false;
		cont_mov = 999999999;
		dir_atual = "esquerda";
		cont_passos = 0;
		seq_Comandos = new ArrayList<>();
		seq_linhas = new ArrayList<>();
		direcoes = new ArrayList<>();
		seq_passos = new ArrayList<>();
		continuouMovimento = false;
		progressBarCheck = false;
		desfazerTudo = false;
		unicoVirar = false;
		mostrarMarker = false;
		contPassosFolego = 0;
		dormindo = false;
		spriteAtualDesc = 0;
		spriteAtualCoelho = 0;
		comeu = false;
		dormir = 1;
		visualizou = false;
		cont_marker = 0;
		jaMoveu = false;
		cont_comandos = 1;
		faseAtual = 0;
		
		pmX1 = 38;
		pmX2 = 26;
		
		pmY1 = 47;
		pmY2 = 17;

		montarTelaMenu();
		montarTelaPrincipal();
		montarPopUp();
		controlComandos();



	}
	private void montarTelaPrincipal() {	

		telaPrincipal.getBttnVirarEsquerda().setIcon(recursos.getVirar_esq());
		telaPrincipal.getBttnVirarDireita().setIcon(recursos.getVirar_dir());
		telaPrincipal.getBttnAndarFrente().setIcon(recursos.getAndar());
		telaPrincipal.getBttnAvancar().setIcon(recursos.getAvancar());
		telaPrincipal.getBttnDesfazer().setIcon(recursos.getDesfazer());
		telaPrincipal.getBttnDesfazerTudo().setIcon(recursos.getDesfazerTudo());
		telaPrincipal.getBttnsom().setIcon(recursos.getSomOn());
		telaPrincipal.getBttnSair().setIcon(recursos.getIsonceSair());
		telaPrincipal.getBttnComer().setIcon(recursos.getIconComer());
		telaPrincipal.getBttnDescansar().setIcon(recursos.getIconDesc());
		telaPrincipal.getBttnMarcador().setIcon(recursos.getMarkSentir());
		telaPrincipal.getLabelComida().setIcon(recursos.getComida());
		telaPrincipal.getLabelEnergia().setIcon(recursos.getEnergia());
		telaPrincipal.getLabelMarkBotao().setIcon(recursos.getMarkBotao());
		telaPrincipal.getLabelLoboDormindo().setIcon(recursos.getLoboDormindo01());
		telaPrincipal.getPanelDormir().setVisible(false);

		recursos.getFundoFase01_c1().montarMapa(672, 512);
		recursos.getFundoFase01_c2().montarMapa(672, 512);
		recursos.getFundoFase01_c3().montarMapa(672, 512);
		recursos.getFundoFase01_2_c1().montarMapa(320, 512);
		recursos.getFundoFase01_2_c2().montarMapa(320, 512);
		
	}


	private void montarTelaMenu() {
		telaMenu.setVisible(true); 
		telaMenu.getLabelVersion().setText("V1.0"+"  Data: "+"01/10/2022");  
		telaMenu.getLabelTitulo().setIcon(recursos.getTitulo());
		telaMenu.getBttnJogar().setIcon(recursos.getJogar());
		telaMenu.getBttnAjuda().setIcon(recursos.getAjuda());
		telaMenu.getBttnSair().setIcon(recursos.getSair());
		telaMenu.getLabelFolhas().setIcon(recursos.getFolhas());
	}

	private void montarPopUp() {
		popup.getBttnOk().setIcon(recursos.getOk());
	}

	private void gerarPopUp(String txt) {
		popup.getLabelTexto().setText((String.format("<html><body style='width: %1spx'>%1s", 350, txt)));
		popup.setVisible(true);
		telaPrincipal.setEnabled(false);

	}



	/**Calcula a perca de fôlego e de fome do personagem contando os passos que ele tomou, a cada 32 pixels (1 passo) ele perde 12 de energia, também calcula a dificuldade do jogo, que
	 * aumenta a cada vez que o jogador come um coelho, até um maximo de 2*/
	private void logicaFolego() {
		

		if(contPassosFolego >= 32) {
			telaPrincipal.getProgressFolego().setValue(telaPrincipal.getProgressFolego().getValue()-(12));
			telaPrincipal.getProgressFome().setValue(telaPrincipal.getProgressFome().getValue()-4);
			logicaMarkerComando();
			
			
			contPassosFolego = 0;
			
		}
	}
	
	/** Coloca um marcador em volta do comando que está sendo executado**/
	private void logicaMarkerComando() {
		switch (cont_comandos) {
		case 1:
			telaPrincipal.getLabelMarkBotao().setLocation(telaPrincipal.getLabelComando2().getX()-2,telaPrincipal.getLabelComando2().getY()-2);
			cont_comandos++;
			break;
		case 2:
			telaPrincipal.getLabelMarkBotao().setLocation(telaPrincipal.getLabelComando3().getX()-2,telaPrincipal.getLabelComando3().getY()-2);
			cont_comandos++;
			break;
		case 3:
			telaPrincipal.getLabelMarkBotao().setLocation(telaPrincipal.getLabelComando4().getX()-2,telaPrincipal.getLabelComando4().getY()-2);
			cont_comandos++;
			break;
		case 4:
			telaPrincipal.getLabelMarkBotao().setLocation(telaPrincipal.getLabelComando5().getX()-2,telaPrincipal.getLabelComando5().getY()-2);
			cont_comandos++;
			break;
		case 5:
			telaPrincipal.getLabelMarkBotao().setLocation(telaPrincipal.getLabelComando6().getX()-2,telaPrincipal.getLabelComando6().getY()-2);
			cont_comandos++;
			break;
		case 6:
			telaPrincipal.getLabelMarkBotao().setLocation(telaPrincipal.getLabelComando7().getX()-2,telaPrincipal.getLabelComando7().getY()-2);
			cont_comandos++;
			break;
		case 7:
			telaPrincipal.getLabelMarkBotao().setLocation(telaPrincipal.getLabelComando8().getX()-2,telaPrincipal.getLabelComando8().getY()-2);
			cont_comandos++;
			break;
		case 8:
			telaPrincipal.getLabelMarkBotao().setLocation(telaPrincipal.getLabelComando9().getX()-2,telaPrincipal.getLabelComando9().getY()-2);
			cont_comandos++;
			break;
		case 9:
			telaPrincipal.getLabelMarkBotao().setLocation(telaPrincipal.getLabelComando10().getX()-2,telaPrincipal.getLabelComando10().getY()-2);
			cont_comandos++;
			break;


		default:
			break;
		}
	}

	/**calcula o tanto de fome perdida ao descansar e o tanto de energia restaurada*/
	private void logicaDescansar() {
		dormindo = true;
		telaPrincipal.getProgressFolego().setValue(100);
		telaPrincipal.getProgressFome().setValue(telaPrincipal.getProgressFome().getValue() - 40);

	}

	/**anima o descanso do jogador*/
	private void animarDescanso() {

		if(spriteAtualDesc == 0) {
			telaPrincipal.getLabelLoboDormindo().setLocation(telaPrincipal.getLabelLoboDormindo().getX(), telaPrincipal.getLabelLoboDormindo().getY()+3);
			spriteAtualDesc = 1;
			spriteAtualDesc = 1;
		}
		else if(spriteAtualDesc == 1) {
			telaPrincipal.getLabelLoboDormindo().setLocation(telaPrincipal.getLabelLoboDormindo().getX(), telaPrincipal.getLabelLoboDormindo().getY()-3);
			spriteAtualDesc = 0;
		}
		
	}


	/** Faz as ações que devem aconteceter enquanto o personagem dorme **/
	private void funcDormir() throws InterruptedException {

		if(dormindo == true) {

			telaPrincipal.getPanelComandos().setVisible(false);
			telaPrincipal.getPanelDormir().setVisible(true);

			if(dormir >= 5) {
				dormindo = false;
				dormir = 1;
				telaPrincipal.getPanelComandos().setVisible(true);
				telaPrincipal.getPanelDormir().setVisible(false);
			}

			animarDescanso();
			paint();


			sleep(400);
			dormir++;

		}
	}

	
	private void animarCoelho() {
		if(spriteAtualCoelho == 0)
			spriteAtualCoelho = 2;
		else if(spriteAtualCoelho == 2)
			spriteAtualCoelho = 4;
		else if(spriteAtualCoelho == 4)
			spriteAtualCoelho = 0;
	}


	/**move o coelho para um local aleatório*/
	private void moverCoelho() {

		switch (faseAtual) {
		case 0:
			coelhoPosX = 224;
			coelhoPosY = 328+16;
			break;
			
		case 1:
			coelhoPosX = 96;
			coelhoPosY = 232+16;
			break;
			
		case 2:
			coelhoPosX = 384;
			coelhoPosY = 232+16;
			break;
			
		case 3:
			coelhoPosX = 544;
			coelhoPosY = 328+16;
			break;
		case 4:
			coelhoPosX = 352;
			coelhoPosY = 456+16;
			break;

		default:
			break;
		}

	}


	/** permite ao jogador comer o coelho, coelho é transportado para num novo local aleatório e sua contagem para mudar de lugar é reiniciada*/
	private void logicaComer() {

		if(player_posX == coelhoPosX && player_posY == coelhoPosY-16 || player_posY == coelhoPosY+16) {
			gerarPopUp("Parabéns, você comeu o coelho e sua fome foi restaurada! Continue com a caçada");
			telaPrincipal.getProgressFome().setValue(100);
			faseAtual++;
			if(faseAtual == 5) {
				faseAtual = 0;
			}
			moverCoelho();

		}
	}

	/**ações enquanto o jogador se move */
	public void validarMovendo() {
		if(movendo == true) {
			telaPrincipal.setEnabled(false);
		}else {
			telaPrincipal.setEnabled(true);
		}


	}
	
	private void revelarMarcador() throws InterruptedException {
		if(mostrarMarker == true) {
			cont_marker++;
			pmX1+=32;
			pmX2+=32;
			pmY1+=32;
			pmY2+=32;
			telaPrincipal.setEnabled(false);
			
			if(cont_marker == 20) {
				mostrarMarker = false;
				telaPrincipal.getBttnMarcador().setEnabled(false);
				visualizou = true;
				pmX1 = 38;
				pmX2 = 26;			
				pmY1 = 47;
				pmY2 = 17;
			}
			
		}
	}
	
	/** Faz a grid de movimento desaparecer e o comando resetar após o personagem se mover**/
	private void logicaGrid() {
		if(jaMoveu == true) {
			telaPrincipal.getBttnMarcador().setEnabled(true);
			telaPrincipal.getBttnMarcador().setIcon(recursos.getMarkSentir());
			visualizou = false;
			cont_marker = 0;
			jaMoveu = false;
			
			pmX1 = 38;
			pmX2 = 26;
			
			pmY1 = 47;
			pmY2 = 17;

		}
	}
	

	/** faz botões ficarem ativos ou desativos baseado em seu contexto dentro do jogo **/
	private void checkDesativados() {
		if(seq_Comandos.isEmpty()) {
			telaPrincipal.getBttnDesfazer().setEnabled(false);
			telaPrincipal.getBttnDesfazerTudo().setEnabled(false);
			telaPrincipal.getBttnAvancar().setEnabled(false);
		}else {
			telaPrincipal.getBttnDesfazer().setEnabled(true);
			telaPrincipal.getBttnDesfazerTudo().setEnabled(true);
			telaPrincipal.getBttnAvancar().setEnabled(true);
		}

		if(player_posX != coelhoPosX && player_posY != coelhoPosY) {
			telaPrincipal.getBttnComer().setEnabled(false);
		}else {
			telaPrincipal.getBttnComer().setEnabled(true);
		}
		
		if(telaPrincipal.getProgressFolego().getValue() == 100) {
			telaPrincipal.getBttnDescansar().setEnabled(false);
		}else {
			telaPrincipal.getBttnDescansar().setEnabled(true);
		}
	}

	/**coloca os icones das ações escolhidas na caixa de comandos*/
	public void montarSeqComandos() {
		switch (seq_Comandos.size()) {
		case 1:
			telaPrincipal.getLabelComando1().setIcon(seq_Comandos.get(0));
			break;
		case 2:
			telaPrincipal.getLabelComando2().setIcon(seq_Comandos.get(1));
			break;
		case 3:
			telaPrincipal.getLabelComando3().setIcon(seq_Comandos.get(2));
			break;
		case 4:
			telaPrincipal.getLabelComando4().setIcon(seq_Comandos.get(3));
			break;
		case 5:
			telaPrincipal.getLabelComando5().setIcon(seq_Comandos.get(4));
			break;
		case 6:
			telaPrincipal.getLabelComando6().setIcon(seq_Comandos.get(5));
			break;
		case 7:
			telaPrincipal.getLabelComando7().setIcon(seq_Comandos.get(6));
			break;
		case 8:
			telaPrincipal.getLabelComando8().setIcon(seq_Comandos.get(7));
			break;
		case 9:
			telaPrincipal.getLabelComando9().setIcon(seq_Comandos.get(8));
			break;
		case 10:
			telaPrincipal.getLabelComando10().setIcon(seq_Comandos.get(9));
			break;

		default:
			break;
		}
	}

	/** Calcula a posição onde o jogador vai estar após realizar o movimento, os passos são em pixels e os movimentos são em tiles de 32 pixels*/
	public void calcular_movimento(int movimentos, String direcao) {

		int var = 0;
		int passos = 0;
		movendo = true;
		posDestinoX = player_posX;
		posDestinoY = player_posY;

		while (var < movimentos) {

			if(direcao == "baixo") {
				dir_atual = "baixo";
				while (var < movimentos) {
					while(passos < 32) {
						posDestinoY++;
						passos++;
					}
					passos = 0;
					var++;
				}
			}
			else if(direcao == "cima") {
				dir_atual = "cima";
				while (var < movimentos) {
					while(passos < 32) {
						posDestinoY--;
						passos++;
					}
					passos = 0;
					var++;
				}
			}

			else if (direcao == "direita"){
				dir_atual = "direita";
				while (var < movimentos) {
					while(passos < 32) {
						posDestinoX++;
						passos++;
					}
					passos = 0;
					var++;
				}
			}

			else if (direcao == "esquerda") {
				dir_atual = "esquerda";
				while (var < movimentos) {
					while(passos < 32) {
						posDestinoX--;
						passos++;
					}
					passos = 0;
					var++;
				}
			}
		}
		if(posDestinoY > 456 || posDestinoY < 192 || posDestinoX > 608 || posDestinoX < 64 ) {

			gerarPopUp("A distancia a ser percorrida ultrapassa os limites do mapa");

			int valor = telaPrincipal.getProgressFolego().getValue();

			posDestinoX = player_posX;
			posDestinoY = player_posY;

			telaPrincipal.getProgressFolego().setValue(valor);


		}

	}

	/**dependendo de qual direção o personagem está indo, usa um tipo de animação diferente */
	private void animar_movimento () {
		switch (dir_atual) {
		case "esquerda":
			if(sprite_atual == 0)
				sprite_atual = 4;
			else if(sprite_atual == 4)
				sprite_atual = 8;
			else if(sprite_atual == 8)
				sprite_atual = 12;
			else if(sprite_atual == 12)
				sprite_atual = 0;
			break;
		case "direita":
			if(sprite_atual == 1)
				sprite_atual = 5;
			else if(sprite_atual == 5)
				sprite_atual = 9;
			else if(sprite_atual == 9)
				sprite_atual = 13;
			else if(sprite_atual == 13)
				sprite_atual = 1;
			break;
		case "cima":
			if(sprite_atual == 3)
				sprite_atual = 7;
			else if(sprite_atual == 7)
				sprite_atual = 11;
			else if(sprite_atual == 11)
				sprite_atual = 15;
			else if(sprite_atual == 15)
				sprite_atual = 3;
			break;
		case "baixo":
			if(sprite_atual == 2)
				sprite_atual = 6;
			else if(sprite_atual == 6)
				sprite_atual = 10;
			else if(sprite_atual == 10)
				sprite_atual = 14;
			else if(sprite_atual == 14)
				sprite_atual = 2;
			break;


		default:
			break;
		}

	}

	/** move o personagem até o local calculado no metodo de calcular movimentos*/
	private void mover_personagem() {

		int distMov = 4;

		if(movendo == true) {
			switch (dir_atual) {
			case "esquerda":
				cont_mov = player_posX;
				if(player_posX > posDestinoX) {						
					player_posX-=distMov;
					cont_mov-=distMov;
					contPassosFolego+=distMov;
					logicaFolego();
					animar_movimento();
				}
				break;
			case "direita":
				cont_mov = player_posX;
				if(player_posX < posDestinoX) {						
					player_posX+=distMov;
					cont_mov+=distMov;
					contPassosFolego+=distMov;
					logicaFolego();
					animar_movimento();
				}
				break;
			case "cima":
				cont_mov = player_posY;
				if(player_posY > posDestinoY) {						
					player_posY-=distMov;
					cont_mov-=distMov;
					contPassosFolego+=distMov;
					logicaFolego();
					animar_movimento();
				}
				break;
			case "baixo":
				cont_mov = player_posY;
				if(player_posY < posDestinoY) {	
					player_posY+=distMov;
					cont_mov+=distMov;
					contPassosFolego+=distMov;
					logicaFolego();
					animar_movimento();
				}
				break;

			default:
				break;
			}

		}

	}

	/** para o movimento do personagem caso ele atinga o destino ou fique sem energia, também direciona o personagem para uma nova direção caso ele tenha parado devido a fazer uma curva*/
	public void parar_movimento() {
		int x = 0;

		if(movendo == true) {	
			if(cont_mov == posDestinoX && dir_atual == "esquerda" || cont_mov == posDestinoX && dir_atual == "direita"
					|| cont_mov == posDestinoY && dir_atual == "cima" || cont_mov == posDestinoY && dir_atual == "baixo"){
				x++;

				if(direcoes.isEmpty()) {
					if(progressBarCheck == true) {
						progressBarCheck = false;
					}

					movendo = false;
					jaMoveu = true;
					cont_comandos = 1;
					telaPrincipal.getLabelMarkBotao().setVisible(false);
					limpar_telas();
					reset();

				}else{							
					if(continuouMovimento == false) {
						direcionar(direcoes.get(x-1));
						calcular_movimento(seq_passos.get(x), dir_atual);	
						direcoes.clear();
					}				

				}

			}
		}
	}

	/** gira o personagem na direção desejada */
	public void direcionar(String direcao) {
		switch (dir_atual) {
		case "esquerda":
			if(direcao == "esquerda") {
				dir_atual = "baixo";
				sprite_atual = 2;
			}else if(direcao == "direita"){
				dir_atual = "cima";
				sprite_atual = 3;
			}
			break;
		case "direita":
			if(direcao == "esquerda") {
				dir_atual = "cima";
				sprite_atual = 3;
			}else if(direcao == "direita"){
				dir_atual = "baixo";
				sprite_atual = 2;
			}
			break;
		case "cima":
			if(direcao == "esquerda") {
				dir_atual = "esquerda";
				sprite_atual = 0;
			}else if(direcao == "direita"){
				dir_atual = "direita";
				sprite_atual = 1;
			}
			break;
		case "baixo":
			if(direcao == "esquerda") {
				dir_atual = "direita";
				sprite_atual = 1;
			}else if(direcao == "direita"){
				dir_atual = "esquerda";
				sprite_atual = 0;
			}
			break;


		default:
			break;
		}
	}

	/** lê a sequencia de comandos presentes da caixa de comandos */
	public void lerSeqCom() {	

		int indcAtual = 0;

		if(seq_passos.isEmpty()) {
			seq_passos.add(0);
		}

		for(int i = 0; i < seq_Comandos.size();i++) {

			if(seq_Comandos.get(i) == recursos.getAndar()) {
				cont_passos++;
				seq_passos.set(indcAtual, cont_passos);
			}
			else if(seq_Comandos.get(i) != recursos.getAndar()) {
				cont_passos = 0;
				indcAtual++;
				seq_passos.add(0);
			}
		}


	}

	/** lógica para impedir que mais de um botão de virar seja escolhido durante a escolha dos comandos */
	public void validarUnicoVirar(int x) {

		switch (x) {
		case 1:
			if(telaPrincipal.getLabelComando1().getIcon().equals(recursos.getVirar_esq()) || telaPrincipal.getLabelComando1().getIcon().equals(recursos.getVirar_dir())) {
				unicoVirar = false;
			}
			break;
		case 2:
			if(telaPrincipal.getLabelComando2().getIcon().equals(recursos.getVirar_esq()) || telaPrincipal.getLabelComando2().getIcon().equals(recursos.getVirar_dir())) {
				unicoVirar = false;
			}
			break;
		case 3:
			if(telaPrincipal.getLabelComando3().getIcon().equals(recursos.getVirar_esq()) || telaPrincipal.getLabelComando3().getIcon().equals(recursos.getVirar_dir())) {
				unicoVirar = false;
			}
			break;
		case 4:
			if(telaPrincipal.getLabelComando4().getIcon().equals(recursos.getVirar_esq()) || telaPrincipal.getLabelComando4().getIcon().equals(recursos.getVirar_dir())) {
				unicoVirar = false;
			}
			break;
		case 5:
			if(telaPrincipal.getLabelComando5().getIcon().equals(recursos.getVirar_esq()) || telaPrincipal.getLabelComando5().getIcon().equals(recursos.getVirar_dir())) {
				unicoVirar = false;
			}
			break;
		case 6:
			if(telaPrincipal.getLabelComando6().getIcon().equals(recursos.getVirar_esq()) || telaPrincipal.getLabelComando6().getIcon().equals(recursos.getVirar_dir())) {
				unicoVirar = false;
			}
			break;
		case 7:
			if(telaPrincipal.getLabelComando7().getIcon().equals(recursos.getVirar_esq()) || telaPrincipal.getLabelComando7().getIcon().equals(recursos.getVirar_dir())) {
				unicoVirar = false;
			}
			break;
		case 8:
			if(telaPrincipal.getLabelComando8().getIcon().equals(recursos.getVirar_esq()) || telaPrincipal.getLabelComando8().getIcon().equals(recursos.getVirar_dir())) {
				unicoVirar = false;
			}
			break;
		case 9:
			if(telaPrincipal.getLabelComando9().getIcon().equals(recursos.getVirar_esq()) || telaPrincipal.getLabelComando9().getIcon().equals(recursos.getVirar_dir())) {
				unicoVirar = false;
			}
			break;
		case 10:
			if(telaPrincipal.getLabelComando10().getIcon().equals(recursos.getVirar_esq()) || telaPrincipal.getLabelComando10().getIcon().equals(recursos.getVirar_dir())) {
				unicoVirar = false;
			}
			break;

		default:
			break;
		}
		
	}

	/** desfaz o ultimo comando escolhido, ou todos, dependendo de qual botão foi clicaldo*/
	public void desfazer() {
		if(desfazerTudo == false) {

			try {
				if(telaPrincipal.getLabelComando1().getIcon().equals(recursos.getVirar_esq()) || telaPrincipal.getLabelComando1().getIcon().equals(recursos.getVirar_dir())) {
					direcoes.remove(direcoes.size()-1);
				}
				switch (seq_Comandos.size()) {
				case 1:
					validarUnicoVirar(1);
					telaPrincipal.getLabelComando1().setIcon(null);

					break;
				case 2:
					validarUnicoVirar(2);
					telaPrincipal.getLabelComando2().setIcon(null);
					break;
				case 3:
					validarUnicoVirar(3);
					telaPrincipal.getLabelComando3().setIcon(null);
					break;
				case 4:
					validarUnicoVirar(4);
					telaPrincipal.getLabelComando4().setIcon(null);
					break;
				case 5:
					validarUnicoVirar(5);
					telaPrincipal.getLabelComando5().setIcon(null);
					break;
				case 6:
					validarUnicoVirar(6);
					telaPrincipal.getLabelComando6().setIcon(null);
					break;
				case 7:
					validarUnicoVirar(7);
					telaPrincipal.getLabelComando7().setIcon(null);
					break;
				case 8:
					validarUnicoVirar(8);
					telaPrincipal.getLabelComando8().setIcon(null);
					break;
				case 9:
					validarUnicoVirar(9);
					telaPrincipal.getLabelComando9().setIcon(null);
					break;
				case 10:
					validarUnicoVirar(10);
					telaPrincipal.getLabelComando10().setIcon(null);
					break;

				default:
					break;
				}
				seq_Comandos.remove(seq_Comandos.size()-1);
			} catch (Exception e) {
				gerarPopUp("Impossivel deletar pois a linha de comandos está vazia");

			}

		}else {
			unicoVirar = false;
			seq_Comandos.clear();
			direcoes.clear();
			desfazerTudo = false;
			telaPrincipal.getLabelComando1().setIcon(null);
			telaPrincipal.getLabelComando2().setIcon(null);
			telaPrincipal.getLabelComando3().setIcon(null);
			telaPrincipal.getLabelComando4().setIcon(null);
			telaPrincipal.getLabelComando5().setIcon(null);
			telaPrincipal.getLabelComando6().setIcon(null);
			telaPrincipal.getLabelComando7().setIcon(null);
			telaPrincipal.getLabelComando8().setIcon(null);
			telaPrincipal.getLabelComando9().setIcon(null);
			telaPrincipal.getLabelComando10().setIcon(null);
		}

	}

	/** caso o jogador morra de fome o jogo é fechado 
	 * @throws InterruptedException */
	private void logicaPerder() throws InterruptedException {
		if(telaPrincipal.getProgressFome().getValue() <= 0) {
			gerarPopUp("Você morreu de fome, retornando ao menu principal");		
			sleep(2000);
			popup.setVisible(false);
			telaPrincipal.setEnabled(true);
			resetTudo();
			
		}
	}
	
	/** pinta os elementos na tela */
	public void paint() {		

		fundoFase01_2.getGraphics().drawImage(recursos.getFundoFase01_2_c1().camada, 0, 0, null);
		fundoFase01_2.getGraphics().drawImage(recursos.getFundoFase01_2_c2().camada, 0, 0, null);
		
		fundoFase01.getGraphics().drawImage(recursos.getFundoFase01_c1().camada, 0, 0, null);	


		if(mostrarMarker == true) {
				
			fundoFase01.getGraphics().drawImage(recursos.getMarker(), player_posX+pmX1, player_posY+15, null);
			fundoFase01.getGraphics().drawImage(recursos.getMarker(), player_posX-pmX2, player_posY+15, null);

			fundoFase01.getGraphics().drawImage(recursos.getMarker(), player_posX+6, player_posY+pmY1, null);
			fundoFase01.getGraphics().drawImage(recursos.getMarker(), player_posX+6, player_posY-pmY2, null);

		}
		
		else if(mostrarMarker == false && visualizou == true) {
			fundoFase01.getGraphics().drawImage(recursos.getFundoFase01_c3().camada, 0, 0, null);
				
		}

		fundoFase01.getGraphics().drawImage(recursos.getFundoFase01_c2().camada, 0, 0, null);	

		fundoFase01.getGraphics().drawImage(recursos.getCoelho().sprites[spriteAtualCoelho], coelhoPosX, coelhoPosY, null);


		if(dormindo == false) {
			fundoFase01.getGraphics().drawImage(recursos.getPersonagem().sprites[sprite_atual], player_posX, player_posY, null);
		}
		else if(dormindo == true) {
			fundoFase01.getGraphics().drawImage(recursos.getPersonagemDormindo().sprites[spriteAtualDesc], player_posX, player_posY, null);
		}
		

		

		Graphics2D g2d = (Graphics2D) telaPrincipal.getPanelJogo().getGraphics();
		Graphics2D g2d_2 = (Graphics2D) telaPrincipal.getPanelJogoCenario().getGraphics();

		g2d.drawImage(fundoFase01, 0, 0, null);
		g2d_2.drawImage(fundoFase01_2, 0, 0, null);
		
		
		
		
		


	}



	private void controlComandos() {
		telaPrincipal.getBttnAvancar().addActionListener(this);
		telaPrincipal.getBttnAndarFrente().addActionListener(this);
		telaPrincipal.getBttnVirarEsquerda().addActionListener(this);
		telaPrincipal.getBttnVirarDireita().addActionListener(this);
		telaPrincipal.getBttnDesfazer().addActionListener(this);
		telaPrincipal.getBttnDesfazerTudo().addActionListener(this);
		telaPrincipal.getBttnSair().addActionListener(this);
		telaPrincipal.getBttnMarcador().addActionListener(this);
		telaPrincipal.getBttnDescansar().addActionListener(this);
		telaPrincipal.getBttnComer().addActionListener(this);
		telaPrincipal.getBttnsom().addActionListener(this);
		telaMenu.getBttnJogar().addActionListener(this);
		telaMenu.getBttnAjuda().addActionListener(this);
		telaMenu.getBttnSair().addActionListener(this);
		popup.getBttnOk().addActionListener(this);


	}

	public void run() {

		moverCoelho();

		while (true) {
			try {
				
				animarCoelho();
				validarMovendo();
				logicaGrid();
				if(dormindo == false) {

					logicaPerder();
					checkDesativados();
					mover_personagem();
					parar_movimento();
					revelarMarcador();


				}if(movendo == true && telaPrincipal.getProgressFolego().getValue() == 0) {
					gerarPopUp("Sua energia acabou durante o movimento e você foi forçado a parar!");
					posDestinoX = player_posX;
					posDestinoY = player_posY;	

				}

				paint();
				sleep(100);

				funcDormir();



			} catch (Exception e) {

			}
		}
	}

	public void reset() {
		cont_passos = 0;
		cont_mov = 99999999;
		seq_Comandos.clear();
		seq_linhas.clear();
		seq_passos.clear();

	}

	public void resetTudo() {
		reset();
		player_posX = 288;
		player_posY = 328;
		telaPrincipal.setVisible(false);
		telaMenu.setVisible(true);
		posDestinoX = 0; 
		posDestinoY = 0;
		sprite_atual = 0;
		movendo = false;
		cont_mov = 999999999;
		dir_atual = "esquerda";
		cont_passos = 0;
		seq_Comandos.clear();
		seq_linhas.clear();
		direcoes.clear();
		seq_passos.clear();
		continuouMovimento = false;
		progressBarCheck = false;
		desfazerTudo = false;
		unicoVirar = false;
		mostrarMarker = false;
		contPassosFolego = 0;
		dormindo = false;
		spriteAtualDesc = 0;
		spriteAtualCoelho = 0;
		comeu = false;
		dormir = 1;
		visualizou = false;
		cont_marker = 0;	
		jaMoveu = false;
		cont_comandos = 1;
		faseAtual = 0;
		pmX1 = 38;
		pmX2 = 26;
		pmY1 = 47;
		pmY2 = 17;		
		
		limpar_telas();
		moverCoelho();
		telaPrincipal.getProgressFome().setValue(100);
		telaPrincipal.getProgressFolego().setValue(100);
		telaPrincipal.getBttnMarcador().setIcon(recursos.getMarkSentir());
		telaPrincipal.getBttnMarcador().setEnabled(true);
	}


	public void limpar_telas() {
		telaPrincipal.getLabelComando1().setIcon(null);
		telaPrincipal.getLabelComando2().setIcon(null);
		telaPrincipal.getLabelComando3().setIcon(null);
		telaPrincipal.getLabelComando4().setIcon(null);
		telaPrincipal.getLabelComando5().setIcon(null);
		telaPrincipal.getLabelComando6().setIcon(null);
		telaPrincipal.getLabelComando7().setIcon(null);
		telaPrincipal.getLabelComando8().setIcon(null);
		telaPrincipal.getLabelComando9().setIcon(null);
		telaPrincipal.getLabelComando10().setIcon(null);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(telaPrincipal.getBttnAvancar())){

			telaPrincipal.getLabelMarkBotao().setLocation(telaPrincipal.getLabelComando1().getX()-2,telaPrincipal.getLabelComando1().getY()-2);
			telaPrincipal.getLabelMarkBotao().setVisible(true);
			
			if(telaPrincipal.getProgressFolego().getValue() == 0) {

				gerarPopUp("você está muito cansado, descanse para poder continuar");

			}else {


				lerSeqCom();

				calcular_movimento(seq_passos.get(0), dir_atual);

				progressBarCheck = true;

				unicoVirar = false;


			}


		}else if(e.getSource().equals(telaPrincipal.getBttnAndarFrente())) {

			if(seq_Comandos.size() < 10) {
				seq_Comandos.add(recursos.getAndar());
				montarSeqComandos();
			}else {

				gerarPopUp("A linha de comando já está cheia");
			}


		}else if(e.getSource().equals(telaPrincipal.getBttnVirarDireita())) {

			if(unicoVirar == false) {

				if(seq_Comandos.size() < 10) {
					seq_Comandos.add(recursos.getVirar_dir());
					montarSeqComandos();
					direcoes.add("direita");
				}else {
					gerarPopUp("A linha de comando já está cheia");
				}

				unicoVirar = true;
			}else {
				gerarPopUp("Só é possivel escolher uma direção por linha de comando");
			}




		}else if(e.getSource().equals(telaPrincipal.getBttnVirarEsquerda())) {
			if(unicoVirar == false) {

				if(seq_Comandos.size() < 10) {
					seq_Comandos.add(recursos.getVirar_esq());
					montarSeqComandos();
					direcoes.add("esquerda");
				}else {
					gerarPopUp("A linha de comando já está cheia");
				}

				unicoVirar = true;			
			}else {
				gerarPopUp("Só é possivel escolher uma direção por linha de comando");
			}



		}else if(e.getSource().equals(telaPrincipal.getBttnDesfazer())) {

			desfazer();

		}else if(e.getSource().equals(telaPrincipal.getBttnDesfazerTudo())) {

			desfazerTudo = true;
			desfazer();

		}else if(e.getSource().equals(telaPrincipal.getBttnSair())) {
			resetTudo();

		}else if(e.getSource().equals(telaPrincipal.getBttnMarcador())) {
	
				mostrarMarker = true;			

		}else if(e.getSource().equals(telaPrincipal.getBttnDescansar())) {
			logicaDescansar();

		}else if(e.getSource().equals(telaPrincipal.getBttnComer())) {
			logicaComer();


		}else if(e.getSource().equals(telaMenu.getBttnJogar())) {

			telaMenu.setVisible(false);
			telaPrincipal.setVisible(true);


		}else if(e.getSource().equals(telaMenu.getBttnSair())) {
			System.exit(0);

		}else if(e.getSource().equals(telaMenu.getBttnAjuda())) {
			try {
				Desktop.getDesktop().browse(new URL("https://youtu.be/vkWbzE8I2Lc").toURI());
			} catch (IOException | URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}else if(e.getSource().equals(popup.getBttnOk())) {
			telaPrincipal.setEnabled(true);
			popup.setVisible(false);

		}

	}

	public TelaPrincipal getTelaPrincipal() {
		return telaPrincipal;
	}


	public TelaMenu getTelaMenu() {
		return telaMenu;
	}


	public Recursos getRecursos() {
		return recursos;
	}


	public boolean isComeu() {
		return comeu;
	}


	public void setComeu(boolean comeu) {
		this.comeu = comeu;
	}







}
