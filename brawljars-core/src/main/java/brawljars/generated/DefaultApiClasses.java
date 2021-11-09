package brawljars.generated;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import brawljars.Api;

public class DefaultApiClasses {

  private final Map<Class<? extends Api>, String> apiClassMap = Collections.unmodifiableMap(new HashMap<Class<? extends Api>, String>(){{
    put(BrawlersApi.class, "brawljars.generated.BrawlersApiImpl");
  }});

  public Map<Class<? extends Api>, String> getApiClassMap() {
    return apiClassMap;
  }

}