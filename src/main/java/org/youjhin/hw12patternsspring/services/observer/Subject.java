package org.youjhin.hw12patternsspring.services.observer;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(Object obj);
}
