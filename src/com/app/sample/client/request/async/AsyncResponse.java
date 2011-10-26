package com.app.sample.client.request.async;

/**
 * To be used in the new Request framework
 * @author ashtonthomas
 *
 * @param <T>
 */
public interface AsyncResponse<T> {
  public void onSuccess(T obj);
  public void onFailure(Throwable caught);
}
