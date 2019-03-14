import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class SaperFrame extends JFrame  {

	public SaperPanel graPanel;
	public JPanel g;
	MWMenuItem menuNowaGra;
	MWMenuItem mnZakoncz;
	MWMenuItem mninstrukcja;
	MWMenuItem mnoAutorze;

	public SaperFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		graPanel = new SaperPanel();
		graPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		graPanel.setLayout(new BorderLayout(0, 0));
		graPanel.setVisible(true);
		
		
		g=graPanel;
		getContentPane().add(g);
		
		/*JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Opcje");
		menuBar.add(mnNewMenu);
		
		menuNowaGra = new MWMenuItem(0, this);
		mnNewMenu.add(menuNowaGra);
		
		mnZakoncz = new MWMenuItem(1, this);
		mnNewMenu.add(mnZakoncz);
		
		JMenu mnNewMenu_1 = new JMenu("Pomoc");
		menuBar.add(mnNewMenu_1);
		
		mninstrukcja = new MWMenuItem(2, this);
		mnNewMenu_1.add(mninstrukcja);
		
		mnoAutorze = new MWMenuItem(3, this);
		mnNewMenu_1.add(mnoAutorze);
		
		*/

	}
/*
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		
		if(source.getClass()==menuNowaGra.getClass()) ((MWMenuItem)source).onClick(graPanel);
		
	}*/

}
