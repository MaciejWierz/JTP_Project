import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MWMenuItem extends JMenuItem {

	public SaperFrame sf;
	int rodzaj;
	//0 - nowa gra,   1 - Zakoncz,  2 - Instrukcja,  3 - O autorze 
	
	
	MWMenuItem(int r, SaperFrame s)
	{
		sf = s;
		rodzaj = r;
		if(rodzaj==0) setText("NowaGra");
		else if(rodzaj==1)setText("Zakoncz");
		else if(rodzaj==2)setText("Instrukcja");
		else if(rodzaj==3)setText("O autorze");
		else System.out.print("Blad MWMenuItem");
		//addActionListener(sf);
	}
	
	
	public void onClick(JPanel pane)
	{
		if(rodzaj==0)JOptionPane.showMessageDialog  (pane, "Wiadomoœæ", "tytu³", 1); 
		else if(rodzaj==1)System.exit(0);
		else if(rodzaj==2)JOptionPane.showMessageDialog  (pane, "Wiadomoœæ", "tytu³", 1); 
		else if(rodzaj==3)JOptionPane.showMessageDialog  (pane, "Grê wykona³ Maciej Wierzbicki z grupy I6X4S1", "O autorze", 1); 
	}
	
	
	
}
