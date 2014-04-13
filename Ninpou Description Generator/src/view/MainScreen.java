package view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.ItemControl;
import controller.SkillControl;

public class MainScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTabbedPane tabPane;
	private JPanel skillPane, itemPane, creditPane;
	
	public MainScreen() { 
		super("Ninpou Description Generator - v1.1");
		tabPane = new JTabbedPane();
		tabPane.setUI(new PPTTabbedPaneUI());
		itemPane = new Items();
		skillPane = new Skills();
		creditPane = new Credits();
		tabPane.addTab("Items", itemPane);
		tabPane.addTab("Skills", skillPane);
		tabPane.addTab("Credits", creditPane);
		add(tabPane);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				SkillControl.save();
				ItemControl.save();
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				new MainScreen().setVisible(true);
			}
		}).start();
	}
}
