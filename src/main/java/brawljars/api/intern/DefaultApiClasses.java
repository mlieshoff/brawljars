package brawljars.api.intern;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import brawljars.api.Api;
import brawljars.api.intern.brawlers.BrawlersApi;
import brawljars.api.intern.rankings.RankingsApi;

public class DefaultApiClasses {

  private final Map<Class<? extends Api>, String> apiClassMap = Collections.unmodifiableMap(new HashMap<Class<? extends Api>, String>(){{
    put(BrawlersApi.class, "brawljars.api.intern.brawlers.BrawlersApiImpl");
    put(RankingsApi.class, "brawljars.api.intern.rankings.RankingsApiImpl");
  }});

  public Map<Class<? extends Api>, String> getApiClassMap() {
    return apiClassMap;
  }

}