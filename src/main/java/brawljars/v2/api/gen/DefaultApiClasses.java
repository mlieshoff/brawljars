package brawljars.v2.api.gen;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import brawljars.v2.api.Api;
import brawljars.v2.api.BrawlerApi;

public class DefaultApiClasses {

  private final Map<Class<? extends Api>, String> apiClassMap = Collections.unmodifiableMap(new HashMap<Class<? extends Api>, String>(){{
    put(BrawlerApi.class, "brawljars.v2.api.BrawlerApiImpl");
  }});

  public Map<Class<? extends Api>, String> getApiClassMap() {
    return apiClassMap;
  }

}
