package brawljars.api.intern;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import brawljars.api.Api;
import brawljars.api.intern.brawlers.BrawlerApi;
import brawljars.api.intern.rankings.RankingApi;
import brawljars.api.intern.players.PlayerApi;

public class DefaultApiClasses {

  private final Map<Class<? extends Api>, String> apiClassMap = Collections.unmodifiableMap(new HashMap<Class<? extends Api>, String>(){{
    put(BrawlerApi.class, "brawljars.api.intern.brawlers.BrawlerApiImpl");
    put(RankingApi.class, "brawljars.api.intern.rankings.RankingApiImpl");
    put(PlayerApi.class, "brawljars.api.intern.players.PlayerApiImpl");
  }});

  public Map<Class<? extends Api>, String> getApiClassMap() {
    return apiClassMap;
  }

}