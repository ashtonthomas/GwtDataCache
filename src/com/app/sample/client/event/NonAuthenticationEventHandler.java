package com.app.sample.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface NonAuthenticationEventHandler extends EventHandler{
  void onNonAuthenticationEvent(NonAuthenticationEvent event);
}
