package brawljars.v2.api;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import brawljars.v2.api.generated.DefaultApiClasses;

public class BrawlJars {

  private final ApiContext apiContext;

  private Map<Class<?>, String> apiClassMap = new HashMap<>();

  public BrawlJars(String url, String apiKey, Connector connector) {
    apiContext = new ApiContext(url, apiKey, connector);
    apiClassMap.putAll(new DefaultApiClasses().getApiClassMap());
  }

  public <T extends Api> T getApi(Class<T> apiInterface) {
    String apiClassName = apiClassMap.get(apiInterface);
    // assert
    try {
      Class<?> apiImplClass = Class.forName(apiClassName);
      // assert
      Constructor constructor = apiImplClass.getDeclaredConstructor(ApiContext.class);
      constructor.setAccessible(true);
      T impl = (T) constructor.newInstance(apiContext);
      return impl;
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

}
