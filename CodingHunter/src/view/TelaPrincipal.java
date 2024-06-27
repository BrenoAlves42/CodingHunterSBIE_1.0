package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;

import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JProgressBar;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panelPrincipal, panelComandos, panelJogo, panelGridComandos, panelJogoCenario;

	private JLabel labelJogador, labelComando1, labelComando2, labelComando3, labelComando4, labelComando5,
	labelComando6, labelComando7, labelComando8, labelComando9, labelComando10, labelComida, labelEnergia, labelTipoAcoes, labelMarkBotao;
	private JButton bttnAvancar, bttnAndarFrente, bttnVirarEsquerda, bttnVirarDireita, bttnDesfazer, bttnsom, bttnSair, bttnDesfazerTudo, bttnComer, bttnDescansar, bttnMarcador;
	private JProgressBar progressFome, progressFolego;
	private JPanel panelDormir;
	private JLabel labelLoboDormindo;


	public TelaPrincipal() {

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1010, 728);
		setLocationRelativeTo(null);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.BLACK);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		setUndecorated(true);

		panelComandos = new JPanel();
		panelComandos.setBackground(new Color(128, 0, 0));
		panelComandos.setBounds(8, 526, 989, 190);
		panelComandos.setLayout(null);

		bttnAvancar = new JButton();
		bttnAvancar.setBounds(585, 69, 49, 50);
		bttnAvancar.setBorderPainted(false);
		bttnAvancar.setContentAreaFilled(false);
		bttnAvancar.setFocusPainted(false);
		bttnAvancar.setOpaque(false);

		panelGridComandos = new JPanel();
		panelGridComandos.setBackground(new Color(128, 0, 0));
		panelGridComandos.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255)), "Console de comandos", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(255, 255, 255)));
		panelGridComandos.setBounds(214, 0, 361, 168);
		panelGridComandos.setLayout(null);


		labelComando1 = new JLabel();
		labelComando1.setBounds(10, 29, 58, 50);
		labelComando2 = new JLabel();	
		labelComando2.setBounds(78, 29, 58, 50);
		labelComando3 = new JLabel();
		labelComando3.setBounds(146, 29, 58, 50);
		labelComando4 = new JLabel();
		labelComando4.setBounds(214, 29, 58, 50);
		labelComando5 = new JLabel();
		labelComando5.setBounds(282, 29, 58, 50);
		labelComando6 = new JLabel();
		labelComando6.setBounds(10, 90, 58, 50);
		labelComando7 = new JLabel();
		labelComando7.setBounds(78, 90, 58, 50);
		labelComando8 = new JLabel();
		labelComando8.setBounds(146, 90, 58, 50);
		labelComando9 = new JLabel();
		labelComando9.setBounds(214, 90, 58, 50);
		labelComando10 = new JLabel();
		labelComando10.setBounds(282, 90, 58, 50);

		bttnAndarFrente = new JButton("");
		bttnAndarFrente.setBounds(10, 11, 58, 50);

		bttnVirarEsquerda = new JButton("");
		bttnVirarEsquerda.setBounds(78, 11, 58, 50);

		bttnVirarDireita = new JButton("");
		bttnVirarDireita.setBounds(146, 11, 58, 50);

		bttnDesfazer = new JButton("");
		bttnDesfazer.setBounds(126, 130, 58, 25);

		panelJogo = new JPanel();
		panelJogo.setBackground(Color.WHITE);
		panelJogo.setBounds(325, 10, 672, 512);
		panelJogo.setLayout(null);

		bttnsom = new JButton();
		bttnsom.setBounds(78, 149, 38, 30);

		bttnSair = new JButton();
		bttnSair.setBounds(10, 149, 58, 30);

		bttnDesfazerTudo = new JButton("");
		bttnDesfazerTudo.setBounds(126, 165, 58, 25);

		bttnComer = new JButton("");
		bttnComer.setBounds(78, 69, 58, 50);

		bttnDescansar = new JButton("");
		bttnDescansar.setBounds(146, 69, 58, 50);

		bttnMarcador = new JButton();
		bttnMarcador.setFont(new Font("ABeeZee", Font.PLAIN, 12));
		bttnMarcador.setBounds(10, 69, 58, 50);;

		labelMarkBotao = new JLabel("New label");
		labelMarkBotao.setBounds(0, 64, 64, 56);
		labelMarkBotao.setVisible(false);

		panelJogoCenario = new JPanel();
		panelJogoCenario.setBackground(Color.WHITE);
		panelJogoCenario.setBounds(8, 10, 317, 512);
		panelJogoCenario.setLayout(null);
		
		panelDormir = new JPanel();
		panelDormir.setBackground(new Color(128, 0, 0));
		panelDormir.setBounds(8, 526, 989, 190);
		panelPrincipal.add(panelDormir);
		panelDormir.setLayout(null);
		
		labelLoboDormindo = new JLabel("New label");
		labelLoboDormindo.setBounds(371, -5, 249, 192);
		panelDormir.add(labelLoboDormindo);

		panelPrincipal.add(panelComandos);
		panelComandos.add(bttnAvancar);
		panelComandos.add(panelGridComandos);
		panelGridComandos.add(labelComando1);
		panelGridComandos.add(labelComando2);
		panelGridComandos.add(labelComando3);
		panelGridComandos.add(labelComando4);
		panelGridComandos.add(labelComando5);
		panelGridComandos.add(labelComando6);
		panelGridComandos.add(labelComando7);
		panelGridComandos.add(labelComando8);
		panelGridComandos.add(labelComando9);
		panelGridComandos.add(labelComando10);	
		panelGridComandos.add(labelMarkBotao);
		panelComandos.add(bttnAndarFrente);
		panelComandos.add(bttnVirarEsquerda);
		panelComandos.add(bttnVirarDireita);
		panelComandos.add(bttnDesfazer);
		panelComandos.add(bttnsom);
		panelComandos.add(bttnSair);
		panelComandos.add(bttnDesfazerTudo);
		panelComandos.add(bttnComer);
		panelComandos.add(bttnDescansar);
		panelPrincipal.add(panelJogo);
		panelComandos.add(bttnMarcador);
		
		progressFome = new JProgressBar();
		progressFome.setBounds(785, 5, 204, 22);
		panelComandos.add(progressFome);
		
		progressFome.setBackground(Color.BLACK);
		progressFome.setToolTipText("");
		progressFome.setFont(new Font("ABeeZee", Font.BOLD, 12));
		progressFome.setValue(100);
		progressFome.setForeground(new Color(34, 139, 34));
		
		labelComida = new JLabel("");
		labelComida.setBounds(750, 5, 30, 22);
		panelComandos.add(labelComida);
		
		labelEnergia = new JLabel("");
		labelEnergia.setBounds(750, 38, 30, 22);
		panelComandos.add(labelEnergia);
		
		progressFolego = new JProgressBar();
		progressFolego.setBounds(785, 39, 204, 22);
		panelComandos.add(progressFolego);
		progressFolego.setBackground(Color.BLACK);
		
		progressFolego.setValue(100);
		progressFolego.setForeground(new Color(255, 215, 0));
		panelPrincipal.add(panelJogoCenario);

	}


	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}

	public JPanel getPanelComandos() {
		return panelComandos;
	}


	public JPanel getPanelJogo() {
		return panelJogo;
	}

	public JButton getBttnAvancar() {
		return bttnAvancar;
	}


	public JLabel getLabelTesteBoneco() {
		return labelJogador;
	}


	public JPanel getPanelGridComandos() {
		return panelGridComandos;
	}


	public JLabel getLabelComando1() {
		return labelComando1;
	}


	public JLabel getLabelComando2() {
		return labelComando2;
	}


	public JLabel getLabelComando3() {
		return labelComando3;
	}


	public JLabel getLabelComando4() {
		return labelComando4;
	}


	public JLabel getLabelComando5() {
		return labelComando5;
	}


	public JLabel getLabelComando6() {
		return labelComando6;
	}


	public JLabel getLabelComando7() {
		return labelComando7;
	}


	public JLabel getLabelComando8() {
		return labelComando8;
	}


	public JLabel getLabelComando9() {
		return labelComando9;
	}


	public JLabel getLabelComando10() {
		return labelComando10;
	}


	public JButton getBttnAndarFrente() {
		return bttnAndarFrente;
	}

	public JButton getBttnVirarEsquerda() {
		return bttnVirarEsquerda;
	}


	public JButton getBttnVirarDireita() {
		return bttnVirarDireita;
	}


	public JButton getBttnDesfazer() {
		return bttnDesfazer;
	}


	public JLabel getLabelJogador() {
		return labelJogador;
	}


	public JButton getBttnsom() {
		return bttnsom;
	}

	public JButton getBttnSair() {
		return bttnSair;
	}


	public JButton getBttnDesfazerTudo() {
		return bttnDesfazerTudo;
	}


	public JButton getBttnComer() {
		return bttnComer;
	}


	public JButton getBttnDescansar() {
		return bttnDescansar;
	}


	public JButton getBttnMarcador() {
		return bttnMarcador;
	}


	public JProgressBar getProgressFome() {
		return progressFome;
	}


	public JProgressBar getProgressFolego() {
		return progressFolego;
	}


	public JLabel getLabelComida() {
		return labelComida;
	}


	public JLabel getLabelEnergia() {
		return labelEnergia;
	}

	public JLabel getLabelTipoAcoes() {
		return labelTipoAcoes;
	}


	public JPanel getPanelJogoCenario() {
		return panelJogoCenario;
	}


	public JLabel getLabelMarkBotao() {
		return labelMarkBotao;
	}


	public JPanel getPanelDormir() {
		return panelDormir;
	}


	public JLabel getLabelLoboDormindo() {
		return labelLoboDormindo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
