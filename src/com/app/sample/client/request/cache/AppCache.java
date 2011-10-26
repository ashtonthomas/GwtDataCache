package com.app.sample.client.request.cache;

import java.util.HashMap;
import java.util.TreeSet;

public class AppCache {
  private static HashMap<String, CacheObject> cache = new HashMap<String, CacheObject>();
  private static HashMap<Integer, TreeSet<String>> clearKeys = new HashMap<Integer, TreeSet<String>>();

  
  public static void store(String key, Object value){
    store(key, value, 600);//default to ten minutes
  }
  
  public static void store(String key, Object value, int seconds){
    cache.put(key, new CacheObject(value, seconds));
  }
  
  public static Object get(String key){
    if(cache.containsKey(key)){
      return ((CacheObject)cache.get(key)).getData();
    }
    return null;
  }
  
  public static boolean isExpired(String key){
    if(cache.containsKey(key)){
      return cache.get(key).isExpired();
    }
    return true;
  }
  
  public static void purgeCache(){
    cache.clear();
  }
  
  /**
   * This method takes in a Key and ties the Key
   * to an eventKey. the eventKey should correspond
   * to an event that will most likely make some
   * data in the cache stale. Calling this method will
   * associate the key/data pair with the eventKey
   * 
   * Use a collection of set eventKeys to organize events
   * 
   * @param key the key used to uniquely store data in the cache
   * @param eventKey the event that will key the data 
   * associated with the key
   * @return the key. To be used in the actual storage of
   * data objects
   */
  public static void cacheClear(String key, int eventKey){
    if(clearKeys.containsKey(eventKey)){
      clearKeys.get(eventKey).add(key);
    }else{
      TreeSet<String> newKeys = new TreeSet<String>();
      newKeys.add(key);
      clearKeys.put(eventKey, newKeys);
    }
  }
  
  /**
   * Clear all data that is linked to the eventKey
   * 
   * @param eventKey
   */
  public static void clearEvent(int eventKey){
    for(String key:clearKeys.get(eventKey)){
      if(cache.containsKey(key)){
        cache.get(key).forceExpire();
      }
    }
  }
  
}
