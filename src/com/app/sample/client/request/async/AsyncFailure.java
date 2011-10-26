package com.app.sample.client.request.async;

/**
 * An app activity can call a service
 * either directly or via an AppRequest and has
 * the option to pass AsyncFailure which can
 * handle alternative app flow given an exception
 * 
 * Note: that this onFailure will be called after any
 * error checking and handling in Async
 * 
 * @author ashtonthomas
 *
 */
public interface AsyncFailure {
  public void onFailure(Throwable caught);
}
