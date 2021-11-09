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

## How to bind the bintray repository ##

```xml
<repositories>
  <repository>
    <id>mlieshoff-brawljars</id>
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

## Working Agreement

1. Get a ticket
* Assign it to you
* Move it in the projects board from "To Do" to "In Progress" 
2. Create a branch: issue<ticket number>_<small_description>
3. Implement your changes, do TDD!
* Reformat the code with the projects formatter (intellij_code_formatter.xml)
* Analyze the changed files with the projects inspection rules (intellij_inspection_rules.xml)
* Commit with message like: [Issue<ticket_number>] <your message>
4. Create a pull request as fast as possible, base it to master
* Name it like: [Issue<ticket_number>] <your message>
* Put label WIP
5. You're done if:
* All tests are green (old and new ones)
* Code coverage is 100%
* Build system is also okay with compiling and tests, check pull request mergeability
* Remove label WIP
6. Shift the ticket to done into the projects board
7. Do "Squash and Merge" in your pull request
8. Copy the pull requests link to your ticket as close comment and close the ticket
9. Shift the ticket to merged in the projects board
10. If you have to update from master:
a) use merge not rebase

### Testing
1. Create an end to end test
2. Create an integration test
3. Create an unit test