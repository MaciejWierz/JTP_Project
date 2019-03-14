import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class SaperPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public SaperPanel() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		System.out.println("asassa");
		
		JButton btnNewButton = new JButton("New button");
		add(btnNewButton);
		
		JButton btnAaaaaaa = new JButton("aaaaaaa");
		this.add(btnAaaaaaa);


	}

}
