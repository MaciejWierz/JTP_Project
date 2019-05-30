import java.util.TimerTask;
/**
 * Klasa posiadaj¹ca metode do odœwierzania licznika czasu
 * @author Maciej Wierzbicki
 */
public class TimeCounter extends TimerTask {

	/**
	 * pola odpowiadaj¹ce za licznik czasu
	 */
	  SaperNumber timefield1,timefield2;
	  /**
	   * Aplet zawieraj¹cy pola odpowiadaj¹ce za licznik czasu
	   */
	  SaperAplet owner;
	  
	  
	  /**
	   * Konstruktor ustawiaj¹cy wartoœci atrybutów
	   */
	  TimeCounter(SaperNumber fc1,SaperNumber fc2, SaperAplet ow)
	  {
		  timefield1 = fc1;
		  timefield2 = fc2;
		  owner= ow;
	  }

	  /**
	   * metoda wywo³ywana co sekundê
	   */
	@Override
	public void run() {
		if(owner.lose==false&&owner.win==false)
		{
			if(timefield1.fullNumber!=99&&timefield2.fullNumber!=99)
			{
				timefield1.fullNumber++;
				if(timefield1.fullNumber==100)
				{
					timefield1.fullNumber=0;
					timefield2.fullNumber++;
				}
				owner.repaint();
			}else
			{
				System.err.println("Osi¹gniêto maksymalny czas zegara");
				cancel();
			}
		}

		
		
	}
	
	
}
