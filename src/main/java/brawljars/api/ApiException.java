package brawljars.api;

public class ApiException extends RuntimeException {

  public ApiException(Throwable throwable) {
    super(throwable);
  }

}
