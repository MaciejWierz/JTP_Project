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
 * Klasa, która jest podstawowym polem w grze
 * @author Maciej Wierzbicki
 *
 */
public class SaperPole extends JComponent {

	/*
	 * rozmiar pola
	 */
	 int size;
	 /*
	  * lokalizacja pola, oraz lokalizacja wzglêdem innych pól, oraz index w tablicy pól
	  */
	 int x,y,px,py,index;
	 /**
	  * obrazek który jest wyœwietlany
	  */
	 Image img;
	 /*
	  * Aplet zawieraj¹cy to pole
	  */
	 SaperAplet sa;
	 /**
	  * Iloœæ bomb z którymi s¹siaduje danyc przycisk
	  */
	 int state = 0;
	 /**
	  * Flaga pokazuj¹ca czy wartoœæ przycisku jest ukryta
	  */
	 boolean hide = true;
	 /**
	  * Flaga pokazuj¹ca czy przycisk jest bomb¹
	  */
	 boolean bomb = false;
	 /**
	  * flaga pokazuj¹ca czy przycisk jest oznaczony flag¹
	  */
	 boolean flag = false;
	 
	/**
	 * Konstruktor przypisuj¹cy wartoœci dla atrybutów
	 * @param s  rozmiar pola
	 * @param xx lokalizacja x
	 * @param yy lokalizacja y
	 * @param ssa Aplet zaiwieraj¹cy pole
	 * @param pxx lokalizacja x wzglêdem innych pól
	 * @param pyy lokalizacja y wzglêdem innych pól
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
	 * Metoda czytaj¹ca obrazek z pliku
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
	 * Metoda rysuj¹ca obiekt
	 */
	public void drawField(Graphics g)
	{
		g.drawImage(img, x, y, size, size, sa);
	}
	/**
	 * Metoda ustawiaj¹ca index pola
	 * @param i - nowy index pola
	 */
	public void setIndex(int i)
	{
		index=i;
	}
	/**
	 * Metoda zliczaj¹ca bomby w s¹dsiaduj¹cych polach
	 * @param board tablica wszystkich pól
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
		if(py==0&&px>0&&px<x-1)//góra
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
	 * Metoda wykonywana po klikniêciu w przycisk lewym przyciskiem myszy - jeœli nie s¹siaduje z ¿adnymi bombami, to wyœwietla równie¿ s¹siaduj¹ce pola
	 * @param board lista wszystkich pól
	 * @return zwraca true, jeœli zosta³a klikniêta bomba
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
		else if(py==0&&px>0&&px<x-1)//góra
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
		else if(py==x-1&&px>0&&px<x-1)//dó³
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
