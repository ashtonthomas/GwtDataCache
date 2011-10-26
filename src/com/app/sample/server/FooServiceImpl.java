package com.app.sample.server;

import java.util.ArrayList;

import com.app.sample.client.request.service.FooService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class FooServiceImpl extends RemoteServiceServlet implements FooService {
  
  public ArrayList<String> getAllFoos() throws SerializationException {
    //Get stuff
    ArrayList<String> list = new ArrayList<String>();
    
    list.add("Hello");
    list.add("Bob");
    list.add("GWT");
    
    return list;
  }

  public Void addNewFoo(String foo) throws SerializationException {
    //Do stuff
    return null;
  }
}
