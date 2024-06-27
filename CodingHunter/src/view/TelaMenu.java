package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class TelaMenu extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	JButton bttnJogar, bttnAjuda, bttnSair;
	JLabel labelVersion, labelTitulo, labelFolhas;
	
	public TelaMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 530);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		bttnJogar = new JButton("");
		bttnJogar.setBounds(302, 230, 178, 39);
			
		bttnAjuda = new JButton("");
		bttnAjuda.setBounds(302, 280, 178, 39);	
		
		bttnSair = new JButton("");
		bttnSair.setBounds(302, 330, 178, 39);	
		
		labelVersion = new JLabel("New label");
		labelVersion.setFont(new Font("ABeeZee", Font.BOLD, 12));
		labelVersion.setForeground(Color.WHITE);
		labelVersion.setBounds(10, 505, 299, 14);	
		
		labelTitulo = new JLabel("New label");
		labelTitulo.setBounds(51, 43, 678, 148);	
		
		contentPane.add(bttnJogar);
		contentPane.add(bttnAjuda);
		contentPane.add(bttnSair);
		contentPane.add(labelVersion);
		contentPane.add(labelTitulo);
		
		labelFolhas = new JLabel("");
		labelFolhas.setBounds(469, 239, 314, 289);
		contentPane.add(labelFolhas);
		
		setUndecorated(true);
		setVisible(false);
		
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton getBttnJogar() {
		return bttnJogar;
	}

	public JButton getBttnAjuda() {
		return bttnAjuda;
	}

	public JButton getBttnSair() {
		return bttnSair;
	}

	public JLabel getLabelVersion() {
		return labelVersion;
	}

	public JLabel getLabelTitulo() {
		return labelTitulo;
	}

	public JLabel getLabelFolhas() {
		return labelFolhas;
	}
}
