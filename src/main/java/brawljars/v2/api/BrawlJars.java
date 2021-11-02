package brawljars.v2.api;

import java.util.HashMap;
import java.util.Map;
import brawljars.v2.api.gen.DefaultApiClasses;

public class BrawlJars {

  private final String url;
  private final String apiKey;

  private Map<Class<?>, String> apiClassMap = new HashMap<>();

  public BrawlJars(String url, String apiKey) {
    this.url = url;
    this.apiKey = apiKey;
    apiClassMap.putAll(new DefaultApiClasses().getApiClassMap());
  }

  public <T extends Api> T getApi(Class<T> apiInterface) {
    String apiClassName = apiClassMap.get(apiInterface);
    // assert
    try {
      Class<?> apiImplClass = Class.forName(apiClassName);
      // assert
      return (T) apiImplClass.getConstructor().newInstance();
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

}
