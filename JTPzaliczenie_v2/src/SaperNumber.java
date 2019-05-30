import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
/**
 * Klasa, która jest polem, na którym znajduje siê jakaœ liczba
 * @author Maciej Wierzbicki
 *
 */
public class SaperNumber extends JComponent {
	 /**
	 * rozmiar przycisku
	 */
	 int size;
	 /**
	  * koordynaty przycisku
	  */
	 int x,y;
	 /**
	  * Wyœwietlane obrazy (jednoœci i dziesi¹tki)
	  */
	 Image imgJ, imgD;
	 /*
	  * Aplet zawieraj¹cy to pole
	  */
	 SaperAplet sa;
	 /**
	  * cyfra dziesi¹tek i jednoœci wyœwietlanych
	  */
	 int d=0,j=0;
	 /**
	  * pe³na wyœwietlana liczba
	  */
	 int fullNumber=0;
	
	 /**
	  * Konstruktor przypisuj¹ccy parametry
	  * @param s rozmiar
	  * @param xx lokalizacja x
	  * @param yy lokalizacja y
	  * @param ssa Aplet zawieraj¹cy przycisk
	  */
		public SaperNumber(int s, int xx, int yy, SaperAplet ssa) {
			sa = ssa;
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
			j=fullNumber%10;
			d=(fullNumber-j)/10;
			try {
				if(j==0)imgJ = ImageIO.read(new File("numbers/0j.bmp"));
				else if(j==1)imgJ = ImageIO.read(new File("numbers/1j.bmp"));
				else if(j==2)imgJ = ImageIO.read(new File("numbers/2j.bmp"));
				else if(j==3)imgJ = ImageIO.read(new File("numbers/3j.bmp"));
				else if(j==4)imgJ = ImageIO.read(new File("numbers/4j.bmp"));
				else if(j==5)imgJ = ImageIO.read(new File("numbers/5j.bmp"));
				else if(j==6)imgJ = ImageIO.read(new File("numbers/6j.bmp"));
				else if(j==7)imgJ = ImageIO.read(new File("numbers/7j.bmp"));
				else if(j==8)imgJ = ImageIO.read(new File("numbers/8j.bmp"));
				else if(j==9)imgJ = ImageIO.read(new File("numbers/9j.bmp"));
				if(d==0)imgD = ImageIO.read(new File("numbers/0D.bmp"));
				else if(d==1)imgD = ImageIO.read(new File("numbers/1D.bmp"));
				else if(d==2)imgD = ImageIO.read(new File("numbers/2D.bmp"));
				else if(d==3)imgD = ImageIO.read(new File("numbers/3D.bmp"));
				else if(d==4)imgD = ImageIO.read(new File("numbers/4D.bmp"));
				else if(d==5)imgD = ImageIO.read(new File("numbers/5D.bmp"));
				else if(d==6)imgD = ImageIO.read(new File("numbers/6D.bmp"));
				else if(d==7)imgD = ImageIO.read(new File("numbers/7D.bmp"));
				else if(d==8)imgD = ImageIO.read(new File("numbers/8D.bmp"));
				else if(d==9)imgD = ImageIO.read(new File("numbers/9D.bmp"));
			}catch(IOException e) {
				System.err.println(e);
			}
		}
		
		/**
		 * Metoda rysuj¹ca obiekt
		 */
		public void drawField(Graphics g)
		{
			readImage();
			g.drawImage(imgD,x, y, size/2, size,sa);
			g.drawImage(imgJ,x+(size/2), y, size/2, size,sa);
		}


}
