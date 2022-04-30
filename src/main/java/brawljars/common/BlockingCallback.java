package brawljars.common;

import java.util.concurrent.atomic.AtomicReference;

public class BlockingCallback<T extends IResponse> implements Callback<T> {

  private AtomicReference<T> futureResult = new AtomicReference<>();
  private AtomicReference<Exception> futureException = new AtomicReference<>();

  @Override
  public void onResponse(T result) {
    futureResult.set(result);
  }

  @Override
  public void onException(Exception exception) {
    futureException.set(exception);
  }

  public T get() {
    while (futureResult.get() == null || futureException.get() == null) {
      if (futureException.get() != null) {
        throw new IllegalStateException(futureException.get());
      } else if (futureResult.get() != null) {
        return futureResult.get();
      }
    }
    throw new IllegalStateException("not possible state reached :)");
  }
}
