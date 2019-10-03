import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class HotelTimer  extends TimerTask {
    private List<IObserve> observers = new ArrayList();

    HotelTimer(List observers){
        this.observers = observers;
    }

    public void run() {
        //do Hotel Tasks
        notifyObservers();
    }

    private void notifyObservers(){
        for(IObserve observer: observers){
            observer.observe();
        }
    }

}
