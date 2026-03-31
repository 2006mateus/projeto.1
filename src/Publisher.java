import java.util.ArrayList;

public class Publisher {
    private ArrayList<Effects> subscribers = new ArrayList<>();

    public void subscribe(Effects efeito){
        subscribers.add(efeito);
    }

    public void unsubscribe(Effects efeito){
        subscribers.remove(efeito);
    }

    public void notifySubscribers(){
        for (Effects effect : subscribers){
             effect.getNotify();
        }
    }
}