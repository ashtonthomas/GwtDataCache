package com.app.sample.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class NonAuthenticationEvent extends GwtEvent<NonAuthenticationEventHandler>{
  public static final Type<NonAuthenticationEventHandler> TYPE = new Type<NonAuthenticationEventHandler>();
  private String message;
  
  public NonAuthenticationEvent(){}
  
  public NonAuthenticationEvent(String message){
    this.message = message;
  }
  
  public com.google.gwt.event.shared.GwtEvent.Type<NonAuthenticationEventHandler> getAssociatedType() {
    return TYPE;
  }

  public void dispatch(NonAuthenticationEventHandler handler) {
    handler.onNonAuthenticationEvent(this);
  }
  
  /**
   * This can be null
   * @return
   */
  public String getMessage(){
    return message;
  }

  
}
