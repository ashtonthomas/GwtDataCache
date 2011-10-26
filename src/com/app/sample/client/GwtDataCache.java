package com.app.sample.client;

import java.util.ArrayList;

import com.app.sample.client.event.NonAuthenticationEvent;
import com.app.sample.client.request.AppRequest;
import com.app.sample.client.request.BarRequest;
import com.app.sample.client.request.FooRequest;
import com.app.sample.client.request.async.AsyncFailure;
import com.app.sample.client.request.async.AsyncSuccess;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtDataCache implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  private InlineLabel clickLabel;
  
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    //Set up dependencies - use Gin!
    EventBus eventBus = GWT.create(SimpleEventBus.class);
    AcrintaUncaughtExceptionHandler exceptionHandler = GWT.create(AcrintaUncaughtExceptionHandler.class);
    eventBus.addHandler(NonAuthenticationEvent.TYPE, exceptionHandler);
    
    FooRequest fooRequest = new FooRequest(eventBus);
    BarRequest barRequest = new BarRequest(eventBus);
    
    final AppRequest appRequest = new AppRequest(barRequest, fooRequest);
    
    
    //Set up demo
    VerticalPanel vp = new VerticalPanel();
    
    final InlineLabel label = new InlineLabel();
    clickLabel = new InlineLabel();
    vp.add(label);
    vp.add(clickLabel);
    
    vp.add(new HTML("Test Get All Foo (Test by clicking Get All a few times and notice only the first time it requests. then click add new then get all again..."));
    
    
    
    Button allFooButton = new Button("Get All Foos");
    allFooButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        click();
        label.setText("Requesting: get all foos...");
        appRequest.fooRequest.getAllFoos(new AsyncSuccess<ArrayList<String>>() {
          public void onSuccess(ArrayList<String> obj) {
            label.setText("done");
          }
        }, null);
      }
    });
    
    
    vp.add(allFooButton);
    vp.add(new HTML("--"));
    vp.add(new HTML("Test add new Foo"));
    Button addFooButton = new Button("Test add new Foo");
    addFooButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        click();
        label.setText("Requesting: Add New Foo . . . ");
        appRequest.fooRequest.addNewFoo("New Foo", new AsyncSuccess<Void>() {
          public void onSuccess(Void obj) {
            label.setText("done");
          }
          
        }, new AsyncFailure() {
          public void onFailure(Throwable caught) {
          //Handle alternative flow!
            //TODO
            label.setText("request failed..");
          }
        });
      }
    });
    vp.add(addFooButton);
    
    
    vp.add(new HTML("********************************************"));
    
    
    vp.add(new HTML("Test Get All Bar"));
    Button allBarButton = new Button("Get All bars");
    
    allBarButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        click();
        label.setText("Requesting: get all bars...");
        appRequest.barRequest.getAllBars(new AsyncSuccess<ArrayList<String>>() {
          public void onSuccess(ArrayList<String> obj) {
            label.setText("done");
          }
        }, null);
      }
    });
    
    vp.add(allBarButton);
    vp.add(new HTML("--"));
    vp.add(new HTML("Test add new Bar"));
    Button addBarButton = new Button("Add Bar");
    addBarButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        click();
        label.setText("Requesting: add enw bar...");
        appRequest.barRequest.addNewBar("New Bar", new AsyncSuccess<Void>() {
          public void onSuccess(Void obj) {
            label.setText("done");
          }
        }, new AsyncFailure() {
          public void onFailure(Throwable caught) {
            //Handle alternative flow!
            //TODO
            Window.alert("Failed: "+caught.getMessage());
          }
        });
      }
    });
    vp.add(addBarButton);
    
    RootLayoutPanel.get().add(vp);
  }
  
  public void click(){
    clickLabel.setText("Click!");
    new Timer() {
      public void run() {
        clickLabel.setText("");
      }
    }.schedule(1000);
  }
  
}
