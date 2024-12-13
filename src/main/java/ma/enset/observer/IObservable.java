package ma.enset.observer;

public interface IObservable<T> {
    Subscription<T> subscribe(Observer<T> observer);
    void unsubscribe(Observer<T> observer);
    void notifyObservers();
}
