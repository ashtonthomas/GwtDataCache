package com.app.sample.client.request.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.SerializationException;

@RemoteServiceRelativePath("bar.rpc")
public interface BarService extends RemoteService {
  ArrayList<String> getAllBars() throws SerializationException;
  Void addNewBar(String bar) throws SerializationException;
}
