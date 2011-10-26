package com.app.sample.client.request;

public class AppRequest {
  public BarRequest barRequest;
  public FooRequest fooRequest;
  
  public AppRequest(BarRequest barRequest, FooRequest fooRequest){
    this.barRequest = barRequest;
    this.fooRequest = fooRequest;
  }
}
