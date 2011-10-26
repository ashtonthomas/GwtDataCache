package com.app.sample.client;

import com.app.sample.client.event.NonAuthenticationEvent;
import com.app.sample.client.event.NonAuthenticationEventHandler;
import com.app.sample.client.widgets.ExceptionPopup;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;

public class AcrintaUncaughtExceptionHandler implements UncaughtExceptionHandler, NonAuthenticationEventHandler{
  
  public void onUncaughtException(Throwable e) {
     new ExceptionPopup("Unknown Error", e.getMessage()).center();
  }

  public void onNonAuthenticationEvent(NonAuthenticationEvent event) {
    String msg = "";
    if(event.getMessage()!=null){
      msg=event.getMessage();
    }
    new ExceptionPopup("Your Session Has Ended", msg).center();
    new Timer() {
      public void run() {
        Window.Location.reload();
      }
    }.schedule(500);
  }

}
