package org.youjhin.hw12patternsspring.notifications;

import org.youjhin.hw12patternsspring.services.observer.Observer;

public class LoggingObserver implements Observer {
    @Override
    public void update(Object obj) {
        //логика для логирования куда хочется
        System.out.println("Отправили изменения в лог "+ obj.toString());
    }
}
