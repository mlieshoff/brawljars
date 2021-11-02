package brawljars.v2.api;

public abstract class AbstractApi implements Api {

  private final ApiContext apiContext;

  public AbstractApi(ApiContext apiContext) {
    this.apiContext = apiContext;
  }

  @Override
  public ApiContext getApiContext() {
    return apiContext;
  }

}
