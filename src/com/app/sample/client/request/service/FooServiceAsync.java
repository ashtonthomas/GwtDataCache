package com.app.sample.client.request.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FooServiceAsync {
  void getAllFoos(AsyncCallback<ArrayList<String>> callback);
  void addNewFoo(String bar, AsyncCallback<Void> callback);
}
