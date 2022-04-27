package brawljars.api.intern;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import brawljars.api.Api;
import brawljars.api.intern.players.PlayerApi;
import brawljars.api.intern.club.ClubApi;
import brawljars.api.intern.rankings.RankingApi;
import brawljars.api.intern.brawlers.BrawlerApi;
import brawljars.api.intern.event.EventApi;

public class DefaultApiClasses {

  private final Map<Class<? extends Api>, String> apiClassMap = Collections.unmodifiableMap(new HashMap<Class<? extends Api>, String>(){{
    put(PlayerApi.class, "brawljars.api.intern.players.PlayerApiImpl");
    put(ClubApi.class, "brawljars.api.intern.club.ClubApiImpl");
    put(RankingApi.class, "brawljars.api.intern.rankings.RankingApiImpl");
    put(BrawlerApi.class, "brawljars.api.intern.brawlers.BrawlerApiImpl");
    put(EventApi.class, "brawljars.api.intern.event.EventApiImpl");
  }});

  public Map<Class<? extends Api>, String> getApiClassMap() {
    return apiClassMap;
  }

}