package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import java.util.Timer;
import java.util.TimerTask;

public  class TimerAction
{
    public static Timer timer = new Timer();


    public static void addAction(TimerTask timerTask,long delay)
    {
        timer.schedule(timerTask,delay);
    }
}
