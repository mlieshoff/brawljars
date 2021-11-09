package brawljars;

import static java.util.stream.Collectors.toList;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import brawljars.generated.DefaultApiClasses;

public class BrawlJars {

  private final ApiContext apiContext;

  private final ConcurrentHashMap<Class<? extends Api>, ?> apiInstanceMap = new ConcurrentHashMap<>();
  private final Map<Class<? extends Api>, String> apiClassMap = new HashMap<>();

  public BrawlJars(String url, String apiKey, Connector connector) {
    apiContext = new ApiContext(url, apiKey, connector);
    for (Map.Entry<Class<? extends Api>, String> entry : new DefaultApiClasses().getApiClassMap().entrySet()) {
      try {
        apiClassMap.put(entry.getKey(), entry.getValue());
      } catch (Exception e) {
        throw new IllegalStateException(e);
      }
    }
  }

  private <T extends Api> T instantiateApi(String apiImplClassname) {
    try {
      Class<?> apiImplClass = Class.forName(apiImplClassname);
      Constructor constructor = apiImplClass.getDeclaredConstructor(ApiContext.class);
      constructor.setAccessible(true);
      return (T) constructor.newInstance(apiContext);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  public <T extends Api> T getApi(Class<T> apiInterface) {
    return (T) apiInstanceMap.computeIfAbsent(apiInterface, key -> instantiateApi(apiClassMap.get(key)));
  }

  public List<String> listApis() {
    return apiClassMap.keySet().stream().map(entry -> entry.getName()).collect(toList());
  }

}
