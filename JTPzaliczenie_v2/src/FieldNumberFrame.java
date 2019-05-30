import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Klasa wyœwietlaj¹ca okno konfiguracji gry
 * @author Maciej Wierzbicki
 */
public class FieldNumberFrame extends JDialog {

	/**
	 * Konstruktor okna konfiguracyjnego
	 * @param Aplet zawieraj¹cy grê
	 */
	FieldNumberFrame(SaperAplet owner){
		super();
		owner.repaint();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setTitle("Okno konfiguracyjne");
		if(owner.win==true)setTitle("Gratulacje! Zagraj jeszcze raz!");
		else if(owner.lose==true)setTitle("Przegrana! Zagraj jeszcze raz!");
		OknoPanel panel = new OknoPanel();
		panel.setLayout(null);

		panel.btnZatwierdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				int liczba;
				try {
				liczba = Integer.parseInt(panel.textField.getText());
				}catch(Exception e) {
					liczba=0;
				}
				
				if(liczba>4&&liczba<21)
				{
					owner.difficulty=panel.comboBox.getSelectedIndex();
					owner.fieldNumber=liczba;
					setVisible(false);
				}
				else 
				{
					panel.textField.setText("");
					JOptionPane.showMessageDialog(panel,"Niepoprawnie wprowadzona liczba!", "B³¹d", 0);
				}
				
			}
		});
		
		
		panel.btnZakoncz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
		});

		add(panel);
		
		
		setSize(300,230);
		this.setLocation(300, 300);
	}
	
}
