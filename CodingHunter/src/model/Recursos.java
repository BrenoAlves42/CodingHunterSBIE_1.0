package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Recursos {

	
	private TileLoader fundoFase01_c1, fundoFase01_c2, fundoFase01_c3, fundoFase01_2_c1, fundoFase01_2_c2;
	private SpriteLoader personagem, coelho, personagemDormindo;
	private ImageIcon desfazer, virar_esq, virar_dir, andar, avancar, somOn, somOff, desfazerTudo, acoesGameplay, acoesDiretas, isonceSair, iconComer, iconDesc, markSentir,
	comida, energia, titulo, jogar, ajuda, sair, ok, folhas, tipoacoes, code01, code02, code03, code04, icone1, icone2, icone3, icone4, markBotao,
	loboDormindo01, loboDormindo02;
	private BufferedImage marker, cenario;

	public Recursos() {	
		fundoFase01_c1 = new TileLoader(16, 21, 32, 32, "/tileSet1.png", "/fundoMatrizFase1_c1.txt");
		fundoFase01_c2 = new TileLoader(16, 21, 32, 32, "/tileSet1.png", "/fundoMatrizFase1_c2.txt");
		fundoFase01_c3 = new TileLoader(16, 21, 32, 32, "/tileSet1.png", "/fundoMatrizFase1_c3.txt");
		fundoFase01_2_c1 = new TileLoader(16, 10, 32, 32, "/tileSet1.png", "/fundoMatrizFase1_2_c1.txt");
		fundoFase01_2_c2 = new TileLoader(16, 10, 32, 32, "/tileSet1.png", "/fundoMatrizFase1_2_c2.txt");
			
		try {
			
			marker = ImageIO.read(getClass().getResource("/marker.png"));
			cenario = ImageIO.read(getClass().getResource("/imgCenr.png"));
			personagem = new SpriteLoader("/spritesheet_lobo.png", 0, 4, 4, 288, 328);
			desfazer = new ImageIcon(getClass().getResource("/icone_desfazer.png"));
			virar_esq = new ImageIcon(getClass().getResource("/icone_set_esq.png"));
			virar_dir = new ImageIcon(getClass().getResource("/icone_set_dir.png"));
			andar = new ImageIcon(getClass().getResource("/icone_andar.png"));
			avancar = new ImageIcon(getClass().getResource("/icone_ir.png"));
			somOn  = new ImageIcon(getClass().getResource("/icone_SomOn.png")); 
			somOff = new ImageIcon(getClass().getResource("/icone_SomOff.png"));
			coelho = new SpriteLoader("/spriteSheet_coelho.png", 0, 3, 2, 320, 360);
			desfazerTudo = new ImageIcon(getClass().getResource("/icone_desfazerTudo.png"));
			isonceSair = new ImageIcon(getClass().getResource("/iconeSair.png"));
			iconComer = new ImageIcon(getClass().getResource("/icone_comer.png"));
			iconDesc = new ImageIcon(getClass().getResource("/icone_descansar.png"));
			markSentir = new ImageIcon(getClass().getResource("/icone_sentir.png"));
			comida = new ImageIcon(getClass().getResource("/icone_comida.png"));
			energia = new ImageIcon(getClass().getResource("/icone_energia.png"));
			personagemDormindo = new SpriteLoader("/spriteSheet_loboDormindo.png", 0, 2, 1, 0, 0);
			titulo = new ImageIcon(getClass().getResource("/titulo.png"));
			jogar = new ImageIcon(getClass().getResource("/icone_jogar.png"));
			ajuda = new ImageIcon(getClass().getResource("/icone_ajuda.png"));
			sair = new ImageIcon(getClass().getResource("/icone_sair.png"));
			ok = new ImageIcon(getClass().getResource("/icone_ok.png"));
			folhas = new ImageIcon(getClass().getResource("/folhas.png"));
			tipoacoes = new ImageIcon(getClass().getResource("/tipoacoes.png"));
			markBotao = new ImageIcon(getClass().getResource("/icone_markBotao.png"));
			loboDormindo01 = new ImageIcon(getClass().getResource("/loboDormindo01.png"));
			loboDormindo02 = new ImageIcon(getClass().getResource("/loboDormindo01.png"));
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public TileLoader getFundoFase01_c1() {
		return fundoFase01_c1;
	}

	public SpriteLoader getPersonagem() {
		return personagem;
	}

	public TileLoader getFundoFase01_c2() {
		return fundoFase01_c2;
	}

	public ImageIcon getDesfazer() {
		return desfazer;
	}

	public ImageIcon getVirar_esq() {
		return virar_esq;
	}

	public ImageIcon getVirar_dir() {
		return virar_dir;
	}

	public ImageIcon getAndar() {
		return andar;
	}

	public ImageIcon getAvancar() {
		return avancar;
	}

	public ImageIcon getSomOn() {
		return somOn;
	}

	public ImageIcon getSomOff() {
		return somOff;
	}

	public BufferedImage getMarker() {
		return marker;
	}

	public SpriteLoader getCoelho() {
		return coelho;
	}

	public ImageIcon getDesfazerTudo() {
		return desfazerTudo;
	}

	public ImageIcon getAcoesGameplay() {
		return acoesGameplay;
	}

	public ImageIcon getAcoesDiretas() {
		return acoesDiretas;
	}

	public ImageIcon getIsonceSair() {
		return isonceSair;
	}

	public ImageIcon getIconComer() {
		return iconComer;
	}

	public ImageIcon getIconDesc() {
		return iconDesc;
	}

	public ImageIcon getComida() {
		return comida;
	}

	public ImageIcon getEnergia() {
		return energia;
	}

	public SpriteLoader getPersonagemDormindo() {
		return personagemDormindo;
	}

	public ImageIcon getTitulo() {
		return titulo;
	}

	public ImageIcon getJogar() {
		return jogar;
	}

	public ImageIcon getAjuda() {
		return ajuda;
	}

	public ImageIcon getSair() {
		return sair;
	}

	public ImageIcon getOk() {
		return ok;
	}

	public ImageIcon getFolhas() {
		return folhas;
	}

	public ImageIcon getTipoacoes() {
		return tipoacoes;
	}

	public ImageIcon getCode01() {
		return code01;
	}

	public ImageIcon getCode02() {
		return code02;
	}

	public ImageIcon getCode03() {
		return code03;
	}

	public ImageIcon getCode04() {
		return code04;
	}

	public ImageIcon getIcone1() {
		return icone1;
	}

	public ImageIcon getIcone2() {
		return icone2;
	}

	public ImageIcon getIcone3() {
		return icone3;
	}

	public ImageIcon getIcone4() {
		return icone4;
	}

	public TileLoader getFundoFase01_c3() {
		return fundoFase01_c3;
	}

	public TileLoader getFundoFase01_2_c1() {
		return fundoFase01_2_c1;
	}

	public TileLoader getFundoFase01_2_c2() {
		return fundoFase01_2_c2;
	}

	public ImageIcon getMarkBotao() {
		return markBotao;
	}

	public ImageIcon getMarkSentir() {
		return markSentir;
	}

	public ImageIcon getLoboDormindo01() {
		return loboDormindo01;
	}

	public ImageIcon getLoboDormindo02() {
		return loboDormindo02;
	}

	public BufferedImage getCenario() {
		return cenario;
	}

	


	
	
	
}
