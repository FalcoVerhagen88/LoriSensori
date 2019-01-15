package com.lorisensori.application.event;

import org.springframework.context.ApplicationEvent;

import com.lorisensori.application.domain.Medewerker;


public class OnUserAccountChangeEvent extends ApplicationEvent {

    private Medewerker user;
    private String action;
    private String actionStatus;

    public OnUserAccountChangeEvent(Medewerker user, String action, String actionStatus) {
        super(user);
        this.user = user;
        this.action = action;
        this.actionStatus = actionStatus;
    }

    public Medewerker getMedewerker() {
        return user;
    }

    public void setMedewerker(Medewerker user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }
}