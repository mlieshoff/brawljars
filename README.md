[![](https://img.shields.io/badge/java-packagecloud.io-844fec.svg)](https://packagecloud.io/)
[![Nightlies](https://github.com/mlieshoff/brawljars/actions/workflows/nightlies.yml/badge.svg)](https://github.com/mlieshoff/brawljars/actions/workflows/nightlies.yml)

# brawljars 4.0.6
A Java Wrapper For Official Supercell Brawl Stars Api

## Why we don't use the Swagger scheme?

A big sorry for that, but the quality of that scheme changes from day to day.
Another big sorry, but the OpenApi Java generator is producing code quality we like much.
That's simple why :) If you think the same way (it may differ from case to case of course), feel free to continue using our wrapper.

## Why we moved to the amazing services of packagecloud?

We moved to packagecloud.io because the bintray closed their nice hosting... And packagecloud.io is a really nice place to be :)

## Join us on Discord

https://discord.gg/WNb5c8hn

## Simplest Usage

Note: Please combine the builder methods as it makes sense. The demonstrated is showing only all possibilities.
For more information please check

https://developer.brawlstars.com/#/documentation

Use one of these endpoints:

Official endpoint
```
    https://api.brawlstars.com/v1
```

Proxy endpoint
```
    https://bsproxy.royaleapi.dev/v1
```

Bind essentials to your project
```xml
<repositories>
    <repository>
        <id>packagecloud-supercell-api-wrapper-essentials</id>
        <url>https://packagecloud.io/mlieshoff/supercell-api-wrapper-essentials/maven2</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
```

and use the dependency
```xml
<dependency>
    <groupId>supercell-api-wrapper-essentials</groupId>
    <artifactId>supercell-api-wrapper-essentials</artifactId>
    <version>1.0.1</version>
</dependency>
```

Use built-in standard connector
```java
Connector connector = new StandardConnector();
```

or use the new filesystem cached connector
```java
Connector connector = new FilesystemCachedConnector("brawljars")
```

or use custom implementation
```java
    Connector connector = new Connector() {
        @Override
        public <T extends IResponse> T get(RequestContext requestContext) throws ConnectorException {
                // do not forget to use auth header with *Bearer*
                String authHeader =  "Authorization: Bearer " + requestContext.getApiKey();
            }
        }
    );
```

connect to the api with creating a *BrawlJars* instance.
```java
    BrawlJars brawlJars = new BrawlJars("https://bsproxy.royaleapi.dev/v1", "my-api-key", connector);
```

list all supported apis
```java
    System.out.println(brawlJars.listApis());
```

### List of APIs and example usages

#### PlayerApi
```java
    // create an instance for the api
    PlayerApi api = brawlJars.getApi(PlayerApi.class);
```
```java
    // findById
    PlayerResponse response = api.findById(PlayerRequest.builder()
           .playerTag()
           // store raw response
           .storeRawResponse()
        .build()
    ).get();
```
```java
    // findBattleLog
    BattleLogResponse response = api.findBattleLog(BattleLogRequest.builder()
           .playerTag()
           // pagination
           .limit()
           .after()
           .before()
           // store raw response
           .storeRawResponse()
        .build()
    ).get();
```
#### ClubApi
```java
    // create an instance for the api
    ClubApi api = brawlJars.getApi(ClubApi.class);
```
```java
    // findClubMembers
    ClubMembersResponse response = api.findClubMembers(clubMembersRequest.builder()
           .clubTag()
           // pagination
           .limit()
           .after()
           .before()
           // store raw response
           .storeRawResponse()
        .build()
    ).get();
```
```java
    // findClub
    ClubResponse response = api.findClub(clubRequest.builder()
           .clubTag()
           // store raw response
           .storeRawResponse()
        .build()
    ).get();
```
#### RankingApi
```java
    // create an instance for the api
    RankingApi api = brawlJars.getApi(RankingApi.class);
```
```java
    // findClubRankings
    ClubRankingsResponse response = api.findClubRankings(clubRankingsRequest.builder()
           .countryCode()
           // pagination
           .limit()
           .after()
           .before()
           // store raw response
           .storeRawResponse()
        .build()
    ).get();
```
```java
    // findBrawlerRankings
    BrawlerRankingsResponse response = api.findBrawlerRankings(brawlerRankingsRequest.builder()
           .countryCode()
           .brawlerId()
           // pagination
           .limit()
           .after()
           .before()
           // store raw response
           .storeRawResponse()
        .build()
    ).get();
```
```java
    // findPlayerRankings
    PlayerRankingsResponse response = api.findPlayerRankings(playerRankingsRequest.builder()
           .countryCode()
           // pagination
           .limit()
           .after()
           .before()
           // store raw response
           .storeRawResponse()
        .build()
    ).get();
```
#### BrawlerApi
```java
    // create an instance for the api
    BrawlerApi api = brawlJars.getApi(BrawlerApi.class);
```
```java
    // findAll
    BrawlersResponse response = api.findAll(brawlersRequest.builder()
           // pagination
           .limit()
           .after()
           .before()
           // store raw response
           .storeRawResponse()
        .build()
    ).get();
```
```java
    // findById
    BrawlerResponse response = api.findById(brawlersRequest.builder()
           .brawlerId()
           // store raw response
           .storeRawResponse()
        .build()
    ).get();
```
#### EventApi
```java
    // create an instance for the api
    EventApi api = brawlJars.getApi(EventApi.class);
```
```java
    // findEventRotation
    EventRotationResponse response = api.findEventRotation(eventRotationRequest.builder()
           // store raw response
           .storeRawResponse()
        .build()
    ).get();
```

## Add or replace registered API's

```java
    BrawlJars brawlJars = new BrawlJars(...);
    brawlJars.register(MyApi.class, MyApiImpl.class.getName());
    MyApi myApi = brawlJars.getApi(MyApi.class);
    GoodiesResponse goodiesResponse = myApi.findAllGoodies(new GoodiesRequest(...))).get();
```

Custom API implementations just need to inherit from *BaseApi*.

## Asynchronous usage

All requests are returning *java.concurrent.Future*. The execution will be asynchronous by default.

## How to bind the packagecloud repository

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

## Add dependency

to Gradle:
```groovy
    implementation group: 'brawljars', name: 'brawljars', version: '4.0.6'
```

to Maven:
```xml
    <dependency>
        <groupId>brawljars</groupId>
        <artifactId>brawljars</artifactId>
        <version>4.0.6</version>
    </dependency>
```

## Continuous Integration

https://github.com/mlieshoff/brawljars/actions

## Repository

https://packagecloud.io/mlieshoff/brawljars

## Logging

We are using SLF4j.

## Usage of RoyaleApi proxy

This wrapper can be easyly connected to the proxy of our friends on RoyaleAPI. Please proceed first the steps described here:

https://docs.royaleapi.com/#/proxy

Then initialize an instance of class Api like that:

```java
    BrawlJars brawlJars = new BrawlJars("https://bsproxy.royaleapi.dev/v1", API_KEY, CONNECTOR);
```

That's all, enjoy :)

## Library updates

Minor versions
```
mvn versions:update-parent versions:use-latest-releases versions:update-properties versions:commit -DallowMajorUpdates=false
```

Major versions
```
mvn versions:update-parent versions:use-latest-releases versions:update-properties versions:commit -DallowMajorUpdates=true
```

Update plugins
```
mvn versions:display-plugin-updates -U
```

## Contributing

1. Feel free to open Pull Requests with your ideas :)