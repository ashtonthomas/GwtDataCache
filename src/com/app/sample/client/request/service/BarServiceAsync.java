package com.app.sample.client.request.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BarServiceAsync {
  void getAllBars(AsyncCallback<ArrayList<String>> callback);
  void addNewBar(String bar, AsyncCallback<Void> callback);
}
