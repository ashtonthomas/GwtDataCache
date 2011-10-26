package com.app.sample.server;

import java.util.ArrayList;

import com.app.sample.client.request.service.BarService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class BarServiceImpl extends RemoteServiceServlet implements BarService {

  public ArrayList<String> getAllBars() throws SerializationException {
    System.out.println("TEST: HIT THE SERVER: getAllBars");
    //Get stuff
    ArrayList<String> list = new ArrayList<String>();
    
    list.add("Hello");
    list.add("Bob");
    list.add("GWT");
    
    return list;
  }

  public Void addNewBar(String bar) throws SerializationException {
    System.out.println("TEST: HIT THE SERVER: addNewbar");
    //Do stuff
    return null;
  }

}
