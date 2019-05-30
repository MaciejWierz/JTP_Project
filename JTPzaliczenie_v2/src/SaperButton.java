import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
/**
 * Klasa odpowiadaj�ca za przycisk resetu (u�miechni�ta twarz)
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
	  * obrazek kt�ry jest wy�wietlany
	  */
	 Image img;
	 /*
	  * Aplet zawieraj�cy ten przycisk
	  */
	 SaperAplet owner;
	 
	 /**
	  * Konstruktor ustawiaj�cy parametry
	  * @param s rozmiar
	  * @param xx lokalizacja x
	  * @param yy lokalizacja y
	  * @param ssa Aplet zawieraj�cy przycisk
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
		 * Metoda czytaj�ca obrazek z pliku
		 */
		public void readImage(){
			try {
				img = ImageIO.read(new File("images/przycisk.bmp"));
				
			}catch(IOException e) {
				System.err.println(e);
			}
		}
		
		/**
		 * Metoda rysuj�ca obiekt
		 */
		public void drawField(Graphics g)
		{
			g.drawImage(img, x, y, size, size, owner);
		}
		

		
		
}
