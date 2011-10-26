package com.app.sample.client.request;

import java.util.ArrayList;

import com.app.sample.client.request.async.Async;
import com.app.sample.client.request.async.AsyncFailure;
import com.app.sample.client.request.async.AsyncResponse;
import com.app.sample.client.request.async.AsyncSuccess;
import com.app.sample.client.request.cache.AppCache;
import com.app.sample.client.request.cache.CacheClear;
import com.app.sample.client.request.service.FooService;
import com.app.sample.client.request.service.FooServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;

public class FooRequest {
  private FooServiceAsync fooService = GWT.create(FooService.class);
  private EventBus eventBus;
  
  public FooRequest(EventBus eventBus){
    this.eventBus = eventBus;
  }
  
  /**
   * Just pass null as last argument if you don't want to handle onFailure. 
   * The exception will be processed prior to failure being called anyway 
   * to perform higher level checks like authorization and shows a notice
   * to the user. 
   */
  public void getAllFoos(final AsyncSuccess<ArrayList<String>> success, final AsyncFailure failure){
  //Generate the unique string
    final String key = "fr-al-str-getAllFoos";
    
    //Set any possible clear events
    AppCache.cacheClear(key, CacheClear.NEW_BAR);
    
    if(AppCache.isExpired(key)){
      fooService.getAllFoos(new Async<ArrayList<String>>(eventBus, new AsyncResponse<ArrayList<String>>() {
        public void onSuccess(ArrayList<String> obj) {
          //Store and return
          AppCache.store(key, obj);
          success.onSuccess(obj);
        }
        public void onFailure(Throwable caught) {
          if(failure!=null){
            failure.onFailure(caught);
          }
        }
      }));
    }else{
      success.onSuccess((ArrayList<String>)AppCache.get(key));
    }
  }
  
  public void addNewFoo(String foo, final AsyncSuccess<Void> success, final AsyncFailure failure){
    fooService.addNewFoo(foo, new Async<Void>(eventBus, new AsyncResponse<Void>(){
      public void onSuccess(Void obj) {
      //Clear appropriate events
        AppCache.clearEvent(CacheClear.NEW_BAR);
        success.onSuccess(obj);
      }
      public void onFailure(Throwable caught) {
        if(failure!=null){
          failure.onFailure(caught);
        }
      }
    }));
  }
}
