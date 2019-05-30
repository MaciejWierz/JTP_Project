import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import javax.swing.JApplet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
/**
 * @author Maciej Wierzbicki
 * Najwa�niejsza klasa w programie.
 * Wy�wietla Aplet, oraz steruje rozgrywk�.
 */

public class SaperAplet extends JApplet implements MouseListener {
	/**
	 * Odpowiada za pierwsze dopasowanie rozmiaru ekranu w aplecie
	 */
	boolean firstSetSize = true;
	/**
	 * szeroko�� i wysoko�� apletu
	 */
	int width = 800, height = 800;
	/**
	 * ilo�� kolumn i rz�d�w p�l w grze
	 */
	int fieldNumber = 10;
	/**
	 * rozmiar pojedy�czego pola w aplecie
	 */
	int fsize=30;
	/**
	 * przycisk do resetowania planszy (u�miechni�ta twarz)
	 */
	SaperButton sbutton;
	/**
	 * liczniki znajduj�ce si� nad plansz�
	 */
	SaperNumber timeCounterField1,timeCounterField2,flagsCounter;
	/**
	 * lista p�l w grze 
	 */
	List<SaperPole> board = new ArrayList<>();
	/**
	 * flaga pokazuj�ca czy gra jest przegrana
	 */
	boolean lose=false;
	/**
	 * flaga odpowiadaj�ca czy gra jest wygrana
	 */
	boolean win=false;
	/**
	 * flaga odpowiadaj�ca za poziom trudno�ci
	 */
	int difficulty = 0;
	/**
	 * zmienna pokazuj�ca ile jest bomb
	 */
	int allBombs;
	/**
	 * zmienna pokazuj�ca ile flag si� u�y�o
	 */
	int bombs=0;
	/**
	 * ile klikni�� zosta�o wykonanych podczas trwania gry
	 */
	int click=0;
	/**
	 * zegar kt�ry zawiera metode od�wierzaj�c� licznik czasu
	 */
	TimeCounter clock;
	/**
	 * timer kt�ry odpala metode z objektu clock
	 */
	Timer timer;

	
	/**
	 * Konstruktor odpalaj�cy aplet i wpisuj�cy domy�lne warto�ci atrybut�w
	 */
	public SaperAplet() {
		JDialog okno = new FieldNumberFrame(this);
		okno.setVisible(true);
		fsize=fieldNumber*fieldNumber;
		for(int i=0; i<fieldNumber;i++)
		for(int j=0;j<fieldNumber;j++) board.add(new SaperPole(30,i*30+30,j*30,this,i,j));
		
		for(int i = 0; i<board.size(); i++)board.get(i).setIndex(i);
		
		sbutton = new SaperButton(30,(30*(fieldNumber-1)),0,this);
		addMouseListener(this);
		
		timeCounterField1 = new SaperNumber(30, (30*(fieldNumber-1)), 0, this);
		timeCounterField2 = new SaperNumber(30, (30*(fieldNumber-2)), 0, this);
		flagsCounter = new SaperNumber(30, 0, 0, this);
		
		timer = new Timer();
		clock = new TimeCounter(timeCounterField1,timeCounterField2,this);
		timer.schedule (clock, 1000, 1000);
		
		
		
	}

	
	public void init() {
	getContentPane().setBackground(Color.gray);
	}
	
	/**
	 * metoda odpowiadaj�ca za rysowanie element�w w aplecie
	 */
	public void paint(Graphics g) {
		int j=0;
		paintComponents(g);
		firstsetSize();
		
		fsize=getSize().width/fieldNumber;
		g.setColor(Color.red);
		for(int i=0;i<fieldNumber*fieldNumber;i++)
		{
			j=i%fieldNumber;
			board.get(i).size=fsize;
			board.get(i).x=j*fsize;
			board.get(i).y=((i-j)/fieldNumber)*fsize+fsize;
			board.get(i).drawField(g);
		}
		sbutton.size=fsize;
		sbutton.x=((fieldNumber-1)*fsize)/2;
		
		timeCounterField1.size=fsize;
		timeCounterField1.x=((fieldNumber-1)*fsize);
		timeCounterField2.size=fsize;
		timeCounterField2.x=((fieldNumber-2)*fsize);
		flagsCounter.size=fsize;
		
		timeCounterField1.drawField(g);
		timeCounterField2.drawField(g);
		flagsCounter.drawField(g);
		sbutton.drawField(g);
		
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Metoda obs�uguj�ca klikni�cia mysz� lewy i prawy przycisk myszy
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(""+(e.getX()/fsize+1)+"  "+e.getY()/fsize);
		if(e.getButton()==MouseEvent.BUTTON3)
		{
			for(int i=0; i<board.size();i++)
				if(board.get(i).px==(e.getX()/fsize) && board.get(i).py==e.getY()/fsize-1)
				{
					
					
					System.out.println("PX: "+board.get(i).px+"PY: "+board.get(i).py);
					if(board.get(i).flag==false) {
						board.get(i).flag = true;
						flagsCounter.fullNumber--;
					}
					else {
						board.get(i).flag = false;
						flagsCounter.fullNumber++;
					}
					board.get(i).readImage();
				}
				
		}
//-----------------------------------------------------------------------------------------------------------------
		else if(e.getButton()==MouseEvent.BUTTON1)
		{
			if(fieldNumber/2==(e.getX()/fsize)&&e.getY()/fsize==0&&click!=0) reset();
				
			else
			{
				for(int i=0; i<board.size();i++)
				if(board.get(i).px==(e.getX()/fsize) && board.get(i).py==e.getY()/fsize-1)
				{
					if(click==0)reset(board.get(i));
					click++;
					//System.out.println("PX: "+board.get(i).px+"PY: "+board.get(i).py);
					lose = board.get(i).showBombs(board);
					if(lose==true) 
						{
						repaint();
						lose();
						}
				checkWin();		
				}
			}		
		}
		repaint();
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Reset planszy przy u�yciu przycisku (u�miechni�tej twarzy)
	 */
	void reset()
	{
		click=0;
		for(int i=0;i<board.size();i++)
		{
			board.get(i).bomb=false;
			board.get(i).state=0;
			board.get(i).hide=true;
			board.get(i).flag = false;
			board.get(i).readImage();
		}
			
	}
	/**
	 * Metoda zliczaj�ca ilo�� bomb w s�siednich polach we wszystkich polach
	 */
	void countBombsAll()
	{
		for(int i=0;i<board.size();i++)board.get(i).countBombs(board);
	}
	/**
	 * Metoda ustawia rozmiar apletu po uruchomieniu gry
	 */
	void firstsetSize()
	{
		if(firstSetSize==true)
		{
		setSize(fieldNumber*30,fieldNumber*30+30);
		firstSetSize=false;
		}
	}

	/**
	 * Metoda obs�uguj�ca przegran�
	 */
	void lose()
	{
		JDialog okno = new FieldNumberFrame(this);
		okno.setVisible(true);
		
		board = new ArrayList<>();
		fsize=30;
		fsize=fieldNumber*fieldNumber;
		for(int i=0; i<fieldNumber;i++)
		for(int j=0;j<fieldNumber;j++) board.add(new SaperPole(30,i*30+30,j*30,this,i,j));
		for(int i = 0; i<board.size(); i++)board.get(i).setIndex(i);
		
		sbutton = new SaperButton(30,(30*(fieldNumber-1)),0,this);
		click=0;
		lose=false;
		win=false;
	}
	/**
	 * Metoda u�ywana do ustawienia pola po wcisni�ciu jakiego� pola na planszy
	 * @param p pole kt�re zosta�o wcisniet�
	 */
	void reset(SaperPole p)
	{
		for(int i=0;i<board.size();i++)
		{
			board.get(i).state=0;
			board.get(i).bomb=false;
			board.get(i).hide=true;
			board.get(i).flag = false;
		}
		setBombs(p);
		for(int i=0;i<board.size();i++) board.get(i).readImage();
		flagsCounter.fullNumber=allBombs;
		timeCounterField1.fullNumber=0;
		timeCounterField2.fullNumber=0;
		clock = new TimeCounter(timeCounterField1,timeCounterField2,this);
	}
	/**
	 * Metoda ustawiaj�ca bomby, uwzgl�dniaj�c wci�ni�te pole
	 * @param p - pole kt�re zosta�o wci�ni�te
	 */
	void setBombs(SaperPole p)
	{
		if(difficulty==0)allBombs = fieldNumber;
		else if(difficulty==1) allBombs = (int) (fieldNumber*1.5);
		else if(difficulty==2) allBombs =  (fieldNumber*2);
		Random generator = new Random();  
		int[] indexbomb = new int[allBombs];
		
		for(int i=0;i<fieldNumber*fieldNumber;i++)board.get(i).bomb = false;
		for(int i=0; i<allBombs; i++)
			{
			indexbomb[i] = generator.nextInt(fieldNumber*fieldNumber-1);
			//System.out.println(""+indexbomb[i]+p.index);
			if(indexbomb[i]==p.index)i--;
			else
			{
				for(int j = 0; j<i; j++) {
				if(indexbomb[i]==indexbomb[j])i--; 
				//System.out.println("I: "+indexbomb[i]+" p: "+p.index+"J: "+indexbomb[j]);
				}	
			}

				
			board.get(indexbomb[i]).bomb = true;
			}
		for(int i=0;i<fieldNumber*fieldNumber;i++)board.get(i).readImage();
		countBombsAll();
	}
	/*
	 * Metoda sprawdzaj�ca czy gra zosta�a wygrana
	 */
	void checkWin()
	{
		int countEnd = 0;
		for(int i=0;i<board.size();i++)
		{
			if(board.get(i).hide==false||board.get(i).bomb==true)countEnd++;
		}
		if (countEnd==fieldNumber*fieldNumber) 
			{
			win=true;
			lose();
			}
	}
	

	
	
}
