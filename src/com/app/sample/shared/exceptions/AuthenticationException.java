package com.app.sample.shared.exceptions;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.SerializationException;

public class AuthenticationException extends SerializationException implements IsSerializable {
  
  public AuthenticationException(){}
  
  public AuthenticationException(String s){
    super(s, null);
  }
  
  public AuthenticationException(String s, Throwable cause){
    super(s, cause);
  }
  

}
