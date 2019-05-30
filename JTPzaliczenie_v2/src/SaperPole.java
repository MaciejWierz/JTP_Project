import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * Klasa, kt�ra jest podstawowym polem w grze
 * @author Maciej Wierzbicki
 *
 */
public class SaperPole extends JComponent {

	/*
	 * rozmiar pola
	 */
	 int size;
	 /*
	  * lokalizacja pola, oraz lokalizacja wzgl�dem innych p�l, oraz index w tablicy p�l
	  */
	 int x,y,px,py,index;
	 /**
	  * obrazek kt�ry jest wy�wietlany
	  */
	 Image img;
	 /*
	  * Aplet zawieraj�cy to pole
	  */
	 SaperAplet sa;
	 /**
	  * Ilo�� bomb z kt�rymi s�siaduje danyc przycisk
	  */
	 int state = 0;
	 /**
	  * Flaga pokazuj�ca czy warto�� przycisku jest ukryta
	  */
	 boolean hide = true;
	 /**
	  * Flaga pokazuj�ca czy przycisk jest bomb�
	  */
	 boolean bomb = false;
	 /**
	  * flaga pokazuj�ca czy przycisk jest oznaczony flag�
	  */
	 boolean flag = false;
	 
	/**
	 * Konstruktor przypisuj�cy warto�ci dla atrybut�w
	 * @param s  rozmiar pola
	 * @param xx lokalizacja x
	 * @param yy lokalizacja y
	 * @param ssa Aplet zaiwieraj�cy pole
	 * @param pxx lokalizacja x wzgl�dem innych p�l
	 * @param pyy lokalizacja y wzgl�dem innych p�l
	 */
	SaperPole(int s, int xx, int yy, SaperAplet ssa,int pxx, int pyy){
		sa = ssa;
		size = s;
		x=xx;y=yy;
		py=pxx; px=pyy;
		setSize(s,s);
		setLocation(xx, yy);
		readImage();
	}
	
	/**
	 * Metoda czytaj�ca obrazek z pliku
	 */
	public void readImage(){
		try {
			
			if(flag==true)img = ImageIO.read(new File("images/flaga.bmp"));
			else if(hide==true)img = ImageIO.read(new File("images/pole.bmp"));
			else if(bomb==true)img = ImageIO.read(new File("images/bomba.bmp"));
			else if(state==1)img = ImageIO.read(new File("images/1.bmp"));
			else if(state==2)img = ImageIO.read(new File("images/2.bmp"));
			else if(state==3)img = ImageIO.read(new File("images/3.bmp"));
			else if(state==4)img = ImageIO.read(new File("images/4.bmp"));
			else if(state==5)img = ImageIO.read(new File("images/5.bmp"));
			else if(state==6)img = ImageIO.read(new File("images/6.bmp"));
			else if(state==7)img = ImageIO.read(new File("images/7.bmp"));
			else if(state==8)img = ImageIO.read(new File("images/8.bmp"));
			else if(state==0)img = ImageIO.read(new File("images/0.bmp"));	
		}catch(IOException e) {
			System.err.println(e);
		}
	}
	
	/**
	 * Metoda rysuj�ca obiekt
	 */
	public void drawField(Graphics g)
	{
		g.drawImage(img, x, y, size, size, sa);
	}
	/**
	 * Metoda ustawiaj�ca index pola
	 * @param i - nowy index pola
	 */
	public void setIndex(int i)
	{
		index=i;
	}
	/**
	 * Metoda zliczaj�ca bomby w s�dsiaduj�cych polach
	 * @param board tablica wszystkich p�l
	 */
	public void countBombs(List<SaperPole> board)
	{
		int x=sa.fieldNumber;
		
		if(px>0&&py>0&&px<x-1&&py<x-1)
		{
			
			if(board.get(index-1).bomb==true)state++;
			if(board.get(index+1).bomb==true)state++;
			if(board.get(index+x).bomb==true)state++;
			if(board.get(index-x).bomb==true)state++;
			if(board.get(index+x+1).bomb==true)state++;
			if(board.get(index+x-1).bomb==true)state++;
			if(board.get(index-x+1).bomb==true)state++;
			if(board.get(index-x-1).bomb==true)state++;

		}
		if(px==0&&py==0)
		{
			if(board.get(index+1).bomb==true)state++;
			if(board.get(index+1+x).bomb==true)state++;
			if(board.get(index+x).bomb==true)state++;
		}
		if(px==x-1&&py==0)
		{
			if(board.get(index-1).bomb==true)state++;
			if(board.get(index-1+x).bomb==true)state++;
			if(board.get(index+x).bomb==true)state++;
		}
		if(px==0&&py==x-1)
		{
			if(board.get(index+1).bomb==true)state++;
			if(board.get(index+1-x).bomb==true)state++;
			if(board.get(index-x).bomb==true)state++;
		}
		if(px==x-1&&py==x-1)
		{
			if(board.get(index-1).bomb==true)state++;
			if(board.get(index-1-x).bomb==true)state++;
			if(board.get(index-x).bomb==true)state++;
		}
		if(py==0&&px>0&&px<x-1)//g�ra
		{
			if(board.get(index-1).bomb==true)state++;
			if(board.get(index+1).bomb==true)state++;
			if(board.get(index-1+x).bomb==true)state++;
			if(board.get(index+1+x).bomb==true)state++;
			if(board.get(index+x).bomb==true)state++;
		}
		if(px==0&&py>0&&py<x-1)//lewo
		{
			if(board.get(index-x).bomb==true)state++;
			if(board.get(index+x).bomb==true)state++;
			if(board.get(index+1-x).bomb==true)state++;
			if(board.get(index+1+x).bomb==true)state++;
			if(board.get(index+1).bomb==true)state++;
		}
		if(px==x-1&&py>0&&py<x-1)//prawo
		{
			if(board.get(index-x).bomb==true)state++;
			if(board.get(index+x).bomb==true)state++;
			if(board.get(index-1-x).bomb==true)state++;
			if(board.get(index-1+x).bomb==true)state++;
			if(board.get(index-1).bomb==true)state++;
		}
		if(py==x-1&&px>0&&px<x-1)
		{
			if(board.get(index-1).bomb==true)state++;
			if(board.get(index+1).bomb==true)state++;
			if(board.get(index-1-x).bomb==true)state++;
			if(board.get(index+1-x).bomb==true)state++;
			if(board.get(index-x).bomb==true)state++;
		}
		
		readImage();
	}
	/**
	 * Metoda wykonywana po klikni�ciu w przycisk lewym przyciskiem myszy - je�li nie s�siaduje z �adnymi bombami, to wy�wietla r�wnie� s�siaduj�ce pola
	 * @param board lista wszystkich p�l
	 * @return zwraca true, je�li zosta�a klikni�ta bomba
	 */
	public boolean showBombs(List<SaperPole> board) {
		int x=sa.fieldNumber;
		hide=false;
		readImage();
		   sa.repaint();
			
		if(bomb==true) return true;
		
		else if(state!=0)return false;
		
		else if(px>0&&py>0&&px<x-1&&py<x-1)
		{	
			if(board.get(index-1).hide==true)board.get(index-1).showBombs(board);
			if(board.get(index+1).hide==true)board.get(index+1).showBombs(board);
			if(board.get(index+x).hide==true)board.get(index+x).showBombs(board);
			if(board.get(index-x).hide==true)board.get(index-x).showBombs(board);
			if(board.get(index+x+1).hide==true)board.get(index+x+1).showBombs(board);
			if(board.get(index+x-1).hide==true)board.get(index+x-1).showBombs(board);
			if(board.get(index-x+1).hide==true)board.get(index-x+1).showBombs(board);
			if(board.get(index-x-1).hide==true)board.get(index-x-1).showBombs(board);
		}
		else if(px==0&&py==0)
		{
			if(board.get(index+1).hide==true)board.get(index+1).showBombs(board);
			if(board.get(index+1+x).hide==true)board.get(index+1+x).showBombs(board);
			if(board.get(index+x).hide==true)board.get(index+x).showBombs(board);
		}
		else if(px==x-1&&py==0)
		{
			if(board.get(index-1).hide==true)board.get(index-1).showBombs(board);
			if(board.get(index-1+x).hide==true)board.get(index-1+x).showBombs(board);
			if(board.get(index+x).hide==true)board.get(index+x).showBombs(board);
		}
		else if(px==0&&py==x-1)
		{
			if(board.get(index+1).hide==true)board.get(index+1).showBombs(board);
			if(board.get(index+1-x).hide==true)board.get(index+1-x).showBombs(board);
			if(board.get(index-x).hide==true)board.get(index-x).showBombs(board);
		}
		else if(px==x-1&&py==x-1)
		{
			if(board.get(index-1).hide==true)board.get(index-1).showBombs(board);
			if(board.get(index-1-x).hide==true)board.get(index-1-x).showBombs(board);
			if(board.get(index-x).hide==true)board.get(index-x).showBombs(board);
		}
		else if(py==0&&px>0&&px<x-1)//g�ra
		{
			if(board.get(index-1).hide==true)board.get(index-1).showBombs(board);
			if(board.get(index+1).hide==true)board.get(index+1).showBombs(board);
			if(board.get(index-1+x).hide==true)board.get(index-1+x).showBombs(board);
			if(board.get(index+1+x).hide==true)board.get(index+1+x).showBombs(board);
			if(board.get(index+x).hide==true)board.get(index+x).showBombs(board);
		}
		else if(px==0&&py>0&&py<x-1)//lewo
		{
			if(board.get(index-x).hide==true)board.get(index-x).showBombs(board);
			if(board.get(index+x).hide==true)board.get(index+x).showBombs(board);
			if(board.get(index+1-x).hide==true)board.get(index+1-x).showBombs(board);
			if(board.get(index+1+x).hide==true)board.get(index+1+x).showBombs(board);
			if(board.get(index+1).hide==true)board.get(index+1).showBombs(board);
		}
		else if(px==x-1&&py>0&&py<x-1)//prawo
		{
			if(board.get(index-x).hide==true)board.get(index-x).showBombs(board);
			if(board.get(index+x).hide==true)board.get(index+x).showBombs(board);
			if(board.get(index-1-x).hide==true)board.get(index-1-x).showBombs(board);
			if(board.get(index-1+x).hide==true)board.get(index-1+x).showBombs(board);
			if(board.get(index-1).hide==true)board.get(index-1).showBombs(board);
		}
		else if(py==x-1&&px>0&&px<x-1)//d�
		{
			if(board.get(index-1).hide==true)board.get(index-1).showBombs(board);
			if(board.get(index+1).hide==true)board.get(index+1).showBombs(board);
			if(board.get(index-1-x).hide==true)board.get(index-1-x).showBombs(board);
			if(board.get(index+1-x).hide==true)board.get(index+1-x).showBombs(board);
			if(board.get(index-x).hide==true)board.get(index-x).showBombs(board);
		}

		return false;
	}
	
}
