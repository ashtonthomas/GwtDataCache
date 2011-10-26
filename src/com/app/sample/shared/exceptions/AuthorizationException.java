package com.app.sample.shared.exceptions;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.SerializationException;

public class AuthorizationException extends SerializationException implements IsSerializable{
  
  public AuthorizationException(){}
  
  public AuthorizationException(String s){
    super(s, null);
  }
  
  public AuthorizationException(String s, Throwable cause){
    super(s, cause);
  }
}
