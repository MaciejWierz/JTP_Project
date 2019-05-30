import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
/**
 * Klasa odpowiadaj¹ca za przycisk resetu (uœmiechniêta twarz)
 * @author Maciej Wierzbicki
 */
public class SaperButton extends JComponent {

	/**
	 * rozmiar przycisku
	 */
	 int size;
	 /**
	  * koordynaty przycisku
	  */
	 int x,y;
	 /**
	  * obrazek który jest wyœwietlany
	  */
	 Image img;
	 /*
	  * Aplet zawieraj¹cy ten przycisk
	  */
	 SaperAplet owner;
	 
	 /**
	  * Konstruktor ustawiaj¹cy parametry
	  * @param s rozmiar
	  * @param xx lokalizacja x
	  * @param yy lokalizacja y
	  * @param ssa Aplet zawieraj¹cy przycisk
	  */
		public SaperButton(int s, int xx, int yy, SaperAplet ssa) {
			owner = ssa;
			size = s;
			x=xx;
			y=yy;
			setSize(s,s);
			setLocation(xx, yy);
			readImage();
		}




		/**
		 * Metoda czytaj¹ca obrazek z pliku
		 */
		public void readImage(){
			try {
				img = ImageIO.read(new File("images/przycisk.bmp"));
				
			}catch(IOException e) {
				System.err.println(e);
			}
		}
		
		/**
		 * Metoda rysuj¹ca obiekt
		 */
		public void drawField(Graphics g)
		{
			g.drawImage(img, x, y, size, size, owner);
		}
		

		
		
}
