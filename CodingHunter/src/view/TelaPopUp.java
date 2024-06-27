package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class TelaPopUp extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JButton bttnOk;
	private JLabel labelTexto;

	public TelaPopUp() {
		setUndecorated(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 120);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		
		labelTexto = new JLabel("");
		labelTexto.setFont(new Font("ABeeZee", Font.BOLD, 16));
		labelTexto.setForeground(Color.WHITE);
		labelTexto.setBounds(7, 11, 570, 52);
		
		
		contentPane.add(labelTexto);
		
		bttnOk = new JButton("");
		bttnOk.setBounds(253, 74, 69, 23);
		contentPane.add(bttnOk);
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton getBttnOk() {
		return bttnOk;
	}

	public JLabel getLabelTexto() {
		return labelTexto;
	}

}
