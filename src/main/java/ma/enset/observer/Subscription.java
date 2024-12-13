package ma.enset.observer;

public class Subscription<T> {
    private final Observer<T> observer;
    private final IObservable<T> observable;
    private int id;

    public Subscription(Observer<T> observer, int id, IObservable<T> observable){
        this.observer = observer;
        this.observable = observable;
        this.id = id;
    }

    public void unsubscribe(){
        observable.unsubscribe(observer);
    }

}
