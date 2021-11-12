[![](https://img.shields.io/badge/java-packagecloud.io-844fec.svg)](https://packagecloud.io/)


# brawljars
A Java Wrapper For Official Supercell Brawl Stars Api 

## Actual version: 1.M5.0
(we moved repository to https://packagecloud.io/mlieshoff/brawljars)

## Simplest Usage ##

Note: Please combine the builder methods as it makes sense. The demonstrated was is showing only all possibilities. 
For more information please check 

https://developer.brawlstars.com/#/documentation

```java
// connect to api
Api api = new Api("https://api.brawlstars.com/v1/", "my-api-key");
```

```java
// get thread safe last response object from last request 
RawResponse rawResponse = api.getLastRawResponse();

String raw = rawResponse.getRaw();
Map<String, String> responseHeaders = rawResponse.getResponseHeaders();
```

```java
// get player
GetPlayerResponse getPlayerResponse = api.getPlayer(GetPlayerRequest.builder("#28UP80RRY").build());
```

```java
// get player battle log
GetPlayerBattleLogResponse getPlayerBattleLogResponse = api.getPlayerBattleLog(GetPlayerBattleLogRequest.builder("#28UP80RRY")
  // paging
  .limit()
  .after()
  .before()
  .build()
);
```

```java
// get club
GetClubResponse getClubResponse = api.getClub(GetClubRequest.builder("#L99U2L2")
  .build()
);
```

```java
// get club members
GetClubMembersResponse getClubMembersResponse = api.getClub(GetClubMembersRequest.builder("#L99U2L2")
  // paging
  .limit()
  .after()
  .before()
  .build()
);
```

```java
// get rankings powerplay seasons
GetRankingsPowerplaySeasonsResponse getRankingsPowerplaySeasonsResponse = api.getRankingsPowerplaySeasons(GetRankingsPowerplaySeasonsRequest.builder("DE")
  // paging
  .limit()
  .after()
  .before()
  .build()
);
```

```java
// get rankings powerplay seasons season
GetRankingsPowerplaySeasonsSeasonResponse getRankingsPowerplaySeasonsResponse = api.getRankingsPowerplaySeasonsSeason(GetRankingsPowerplaySeasonsSeasonRequest.builder("DE", "42")
  // paging
  .limit()
  .after()
  .before()
  .build()
);
```

```java
// get rankings clubs
GetRankingsClubsResponse getRankingsClubsResponse = api.getRankingsClubs(GetRankingsClubsRequest.builder("DE")
  // paging
  .limit()
  .after()
  .before()
  .build()
);
```

```java
// get rankings brawler
GetRankingsBrawlerResponse getRankingsBrawlerResponse = api.getRankingsBrawler(GetRankingsBrawlerRequest.builder("DE", "16000000")
  // paging
  .limit()
  .after()
  .before()
  .build()
);
```

```java
// get rankings players
GetRankingsPlayersResponse getRankingsPlayersResponse = api.getRankingsPlayers(GetRankingsPlayersRequest.builder("DE")
  // paging
  .limit()
  .after()
  .before()
  .build()
);
```

```java
// get brawlers
GetBrawlersResponse getBrawlersResponse = api.getBrawlers(GetBrawlersRequest.builder()
  // paging
  .limit()
  .after()
  .before()
  .build()
);
```

```java
// get brawler
GetBrawlerResponse getBrawlerResponse = api.getBrawler(GetBrawlerRequest.builder("16000000")
  // paging
  .limit()
  .after()
  .before()
  .build()
);
```

## Asynchronous usage

All requests can have a *callback*. Then execution will be asynchronous.

```java
// register async callback
  .callback(new Callback<Response>() {
    @Override
    public void onResponse(Response response) {
      // handle result
    }

    @Override
    public void onException(Exception exception) {
      state.set(true);
    }
  })
  .build()
);
```

## How to bind the packagecloud repository ##

```xml
<repositories>
  <repository>
    <id>packagecloud-brawljars</id>
    <url>https://packagecloud.io/mlieshoff/brawljars/maven2</url>
    <releases>
      <enabled>true</enabled>
    </releases>
    <snapshots>
      <enabled>true</enabled>
    </snapshots>
  </repository>
</repositories>
```

## Continuous Integration ##

https://github.com/mlieshoff/brawljars/actions

## Repository ##

https://packagecloud.io/mlieshoff/brawljars

## Logging ##

We use SLF4j.

## Usage of RoyaleApi proxy ##

This wrapper can be easy connected to the proxy of our friends on RoyaleAPI. Please proceed first the steps described here:

https://docs.royaleapi.com/#/proxy

Then initialize an instance of class Api like that:

```
Api api = new Api("https://bsproxy.royaleapi.dev/v1", API_KEY);
```

That's all, enjoy :)
