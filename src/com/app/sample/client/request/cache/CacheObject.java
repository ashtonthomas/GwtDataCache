package com.app.sample.client.request.cache;

import java.util.Date;

public class CacheObject {
  private Object data;
  private Date created_at;
  private Date expires_at;
  
  public CacheObject(Object data, int seconds){
    created_at = new Date();
    if(seconds<=0){
      seconds = 525600;
    }
    expires_at = new Date(created_at.getTime() + seconds*1000);
    this.data = data;
  }
  
  public Object getData(){
    return data;
  }
  
  public boolean isExpired(){
    Date date = new Date();
    return date.after(expires_at);
  }
  
  public void forceExpire(){
    //force expire
    expires_at = new Date(created_at.getTime() - 1);//set expire for before created
  }
  
}
