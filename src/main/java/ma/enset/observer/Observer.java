package ma.enset.observer;

public interface Observer<T> {
    void update(T state);
}
