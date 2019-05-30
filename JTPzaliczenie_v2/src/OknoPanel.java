import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
/**
 * Klasa odpowiadaj¹ca za panel znajduj¹cy siê w oknie konfiguracyjnym
 * @author Maciej Wierzbicki
 */
public class OknoPanel extends JPanel {
	/**
	 * W tym polu wpisuje siê rozmiar planszy
	 */
	JTextField textField;
	/**
	 * przycisk zatwierdzaj¹cy konfiguracje
	 */
	JButton btnZatwierdz;
	/**
	 * Pole odpowiadaj¹ce za wybór poziomu trudnoœci
	 */
	JComboBox comboBox;
	/**
	 * Tekst informuj¹cy u¿ytkownika co ma zrobiæ
	 */
	JLabel lblWybierzPoziomTrudnoci;
	/**
	 * Tekst informuj¹cy u¿ytkownika co ma zrobiæ
	 */
	JLabel lblPodajWielkoscPlanszy;
	/**
	 * Przycisk koñcz¹cy dzia³anie programu
	 */
	JButton btnZakoncz;
	/**
	 * Konstruktor panelu
	 */
	public OknoPanel() {
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 52, 137, 20);
		add(textField);
		textField.setColumns(10);
		
		lblPodajWielkoscPlanszy = new JLabel("Podaj wielkosc planszy (mi\u0119dzy 5 a 20): ");
		lblPodajWielkoscPlanszy.setBounds(10, 21, 280, 20);
		add(lblPodajWielkoscPlanszy);
		
		lblWybierzPoziomTrudnoci = new JLabel("Wybierz poziom trudno\u015Bci:");
		lblWybierzPoziomTrudnoci.setBounds(10, 83, 180, 20);
		add(lblWybierzPoziomTrudnoci);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 114, 111, 20);
		comboBox.addItem("£atwy");
		comboBox.addItem("Œredni");
		comboBox.addItem("Trudny");
		add(comboBox);
		
		btnZatwierdz = new JButton("Zatwierdz");
		btnZatwierdz.setBounds(23, 160, 111, 29);
		add(btnZatwierdz);
		
		btnZakoncz = new JButton("Zakoncz");
		btnZakoncz.setBounds(158, 160, 111, 29);
		add(btnZakoncz);

	}
}
