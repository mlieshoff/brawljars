# brawljars
A Java Wrapper For Official Supercell Brawl Stars Api

## Actual version: 2.0.0

## Simplest Usage ##

Note: Please combine the builder methods as it makes sense. The demonstrated was is showing only all possibilities.
For more information please check

https://developer.brawlstars.com/#/documentation

Use one of these endpoints:
```
Official endpoint
https://api.brawlstars.com/v1
```
```
Proxy endpoint
https://bsproxy.royaleapi.dev/v1
```
Use built-in http connector
```java
Connector connector = new OkHttpConnector();
```
or use custom implementation
```
Connector connector = new Connector() {
  @Override
  public <T> T get(RequestContext requestContext) throws IOException {
    // dont forget to use header auth
    String authHeader =  "Authorization: Bearer " + requestContext.getApiKey();
  }
});
```
connect to the api with creating a *BrawlJars* instance. 
```
BrawlJars brawlJars = new BrawlJars("https://bsproxy.royaleapi.dev/v1", "my-api-key", connector);
```

list all supported apis
```
System.out.println(brawlJars.listApis());
```

create an instance for a specific api
```
BrawlersApi brawlersApi = brawlJarsApi.getApi(BrawlersApi.class);
```

### Brawlers api
```java
// find all brawlers
BrawlersApi brawlersApi = brawlJarsApi.getApi(BrawlersApi.class);
BrawlersResponse bBrawlersResponse = brawlersApi.findAll(BrawlersRequest.builder()
  // paging
  .limit()
  .after()
  .before()
  .build()
);
```

```java
// find brawler by id
BrawlersApi brawlersApi = brawlJarsApi.getApi(BrawlersApi.class);
BrawlerResponse brawlerResponse = brawlersApi.findById(BrawlersRequest.builder("16000000")
  .build()
);
```

## Asynchronous usage

All requests can have a *callback*. Then execution will be asynchronous.

## How to bind the bintray repository ##

## Continuous Integration ##

## Repository ##

https://packagecloud.io/instructions/install_cli
https://github.com/computology/maven-packagecloud-wagon

## Logging ##

We are using SLF4j.
