package com.app.sample.client.request.async;

/**
 * An app activity must use a AsyncSuccess 
 * when calling a service either directly or
 * via the AppRequest
 * 
 * @author ashtonthomas
 *
 * @param <T>
 */
public interface AsyncSuccess<T> {
  public void onSuccess(T obj);
}
