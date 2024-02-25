package org.youjhin.hw12patternsspring.notifications;

import org.youjhin.hw12patternsspring.services.observer.Observer;

public class EmailNotificationObserver implements Observer {

    @Override
    public void update(Object obj) {
        // логика отправки на мыло
        System.out.println("Отправили письмо с объектом на почту "+ obj.toString());
    }
}
