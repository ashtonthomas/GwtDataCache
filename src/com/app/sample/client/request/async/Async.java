package com.app.sample.client.request.async;

import com.app.sample.client.event.NonAuthenticationEvent;
import com.app.sample.client.widgets.ExceptionPopup;
import com.app.sample.shared.exceptions.AuthenticationException;
import com.app.sample.shared.exceptions.AuthorizationException;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

public class Async<T> implements AsyncCallback<T> {
  private AsyncSuccess<T> success;
  private EventBus eventBus;
  private AsyncResponse<T> callback;
  
  /**
   * This is the older way of calling a request and
   * just passing a success callback
   * 
   * @param eventBus
   * @param success
   */
  public Async(EventBus eventBus, AsyncSuccess<T> success){
    this.success = success;
    this.eventBus = eventBus;
  }
  
  /**
   * This is the newer way with a callback that is
   * responsible for both sucess and failure. However
   * this Async object should do some prechecks onFailure
   * even if the callback handles onFailure too.
   * Once Async is done, the callback.onFailure can
   * be called in case it has an alternative flow
   * 
   * @param eventBus
   * @param callback
   */
  public Async(EventBus eventBus, AsyncResponse<T> callback){
    this.eventBus = eventBus;
    this.callback = callback;
  }
  
  public void onFailure(Throwable caught) {
    
    //Check for specific exceptions
    if(caught instanceof InvocationException){
      new ExceptionPopup("Invocation Exception", caught.getMessage()).center();
    }else if(caught instanceof IncompatibleRemoteServiceException){
      new ExceptionPopup("Incompatible Remote Service Exception", caught.getMessage()).center();
    }else if(caught instanceof AuthenticationException){
      eventBus.fireEvent(new NonAuthenticationEvent());
    }else if(caught instanceof AuthorizationException){
      new ExceptionPopup("Authorization Exception", caught.getMessage()).center();
    }else{
      new ExceptionPopup("Unknown Exception", caught.getMessage()).center();
    }
    
    //Only if the appropriate callback was passed
    if(callback!=null){
      callback.onFailure(caught);
    }
  }

  public void onSuccess(T result) {
    if(callback!=null){
      callback.onSuccess(result);
    }else{
      success.onSuccess(result);
    }
    
  }
  
}
