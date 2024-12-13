package ma.enset.observer;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> implements IObservable<T> {
    private final List<Observer<T>> observers;
    private T state;

    public Observable(T state){
        this.state = state;
        this.observers = new ArrayList<>();
    }

    public Subscription<T> subscribe(Observer<T> observer){
        Subscription<T> subscription = new Subscription<T>(observer, observers.size(), this);
        observers.add(observer);
        return subscription;
    }

    public void unsubscribe(Observer<T> observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for (Observer<T> observer : observers) {
            observer.update(this.state);
        }
    }

    public void setState(T state){
        this.state = state;
        notifyObservers();
    }

    public T getState(){
        return state;
    }
}
