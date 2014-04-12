package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Credits extends JPanel {

	private static final long serialVersionUID = 1L;

	public Credits() { 
		super(new BorderLayout());
		JTextArea textArea = new JTextArea("Ninpou Description Generator\nVersion: 1.0\nRelease date: 12.apr.2014\n\nCreated by:\nteressact (teressact@outlook.com)\n\nThis software is licensed by GLP v3.0");
		textArea.setEditable(false);
		add(textArea, BorderLayout.CENTER);
	}
	
}
