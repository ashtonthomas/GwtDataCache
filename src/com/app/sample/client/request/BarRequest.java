package com.app.sample.client.request;

import java.util.ArrayList;

import com.app.sample.client.request.async.Async;
import com.app.sample.client.request.async.AsyncFailure;
import com.app.sample.client.request.async.AsyncResponse;
import com.app.sample.client.request.async.AsyncSuccess;
import com.app.sample.client.request.cache.AppCache;
import com.app.sample.client.request.cache.CacheClear;
import com.app.sample.client.request.service.BarService;
import com.app.sample.client.request.service.BarServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Timer;

public class BarRequest {
  private BarServiceAsync barService = GWT.create(BarService.class);
  private EventBus eventBus;
  
  public BarRequest(EventBus eventBus){
    this.eventBus = eventBus;
  }
  
  //String key:
  //BR - BarRequest
  //AL - ArrayList
  //str - String
  //methodName
  //param + values
  
  /**
   * Just pass null as last argument if you don't want to handle onFailure. 
   * The exception will be processed prior to failure being called anyway 
   * to perform higher level checks like authorization and shows a notice
   * to the user. 
   */
  public void getAllBars(final AsyncSuccess<ArrayList<String>> success, final AsyncFailure failure){
    //Generate the unique string
    final String key = "br-al-str-getAllBars";
    
    //Set any possible clear events
    AppCache.cacheClear(key, CacheClear.NEW_BAR);
    
    if(AppCache.isExpired(key)){
      barService.getAllBars(new Async<ArrayList<String>>(eventBus, new AsyncResponse<ArrayList<String>>() {
        public void onSuccess(final ArrayList<String> obj) {
          //Store and return
          
          //DEMO TIMER
          //Just to make sure you see the difference
          new Timer() {
            public void run() {
              AppCache.store(key, obj);
              success.onSuccess(obj);
            }
          }.schedule(1750);
          
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
  
  public void addNewBar(String bar, final AsyncSuccess<Void> success, final AsyncFailure failure){
    barService.addNewBar(bar, new Async<Void>(eventBus, new AsyncResponse<Void>() {
      public void onSuccess(Void obj) {
        //Clear appropriate events
        
      //DEMO TIMER
        //Just to make sure you see the difference
        new Timer() {
          public void run() {
            AppCache.clearEvent(CacheClear.NEW_BAR);
            success.onSuccess(null);
          }
        }.schedule(1750);
      }
      public void onFailure(Throwable caught) {
        if(failure!=null){
          failure.onFailure(caught);
        }
      }
    }));
  }
  
  
  
}
