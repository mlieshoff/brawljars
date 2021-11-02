package brawljars.v2.api.generated;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import brawljars.v2.api.Api;
import brawljars.v2.api.brawlers.BrawlersApi;

public class DefaultApiClasses {

  private final Map<Class<? extends Api>, String> apiClassMap = Collections.unmodifiableMap(new HashMap<Class<? extends Api>, String>(){{
    put(BrawlersApi.class, "brawljars.v2.api.brawlers.BrawlersApiImpl");
  }});

  public Map<Class<? extends Api>, String> getApiClassMap() {
    return apiClassMap;
  }

}
