package com.app.sample.client.request.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.SerializationException;

@RemoteServiceRelativePath("foo.rpc")
public interface FooService extends RemoteService {
  ArrayList<String> getAllFoos() throws SerializationException;
  Void addNewFoo(String foo) throws SerializationException;
}
