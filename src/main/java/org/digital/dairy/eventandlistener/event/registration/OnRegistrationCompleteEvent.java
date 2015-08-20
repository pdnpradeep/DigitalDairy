package org.digital.dairy.eventandlistener.event.registration;

import org.digital.dairy.model.entity.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * Created by Pradeep.P on 11-04-2015.

 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private final String appUrl;
    private final Locale locale;
    private final User user;

    public OnRegistrationCompleteEvent(User user, Locale locale, String appUrl) {
        super(user);

        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public User getUser() {
        return user;
    }

    public Locale getLocale() {
        return locale;
    }
}
