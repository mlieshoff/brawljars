package brawljars.v2;

import com.google.gson.Gson;

import java.io.IOException;
import brawljars.v2.api.BrawlJars;
import brawljars.v2.api.Connector;
import brawljars.v2.api.RequestContext;
import brawljars.v2.api.generated.BrawlersApi;
import brawljars.v2.api.generated.BrawlersRequest;
import brawljars.v2.api.generated.BrawlersResponse;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Test {

  public static void main(String[] args) throws IOException {
    BrawlJars brawlJarsApi = new BrawlJars("https://bsproxy.royaleapi.dev/v1",
        args[0],
        new Connector() {
          @Override
          public <T> T get(RequestContext requestContext) throws IOException {
            OkHttpClient client = new OkHttpClient.Builder()
                .build();

            Request request = new Request.Builder()
                .header("Authorization", "Bearer " + requestContext.getApiKey())
                .url(requestContext.getUrl())
                .build();

            Call call = client.newCall(request);
            Response response = call.execute();
            String json = response.body().string();
            System.out.println(json);
            T t = (T) new Gson().fromJson(json, requestContext.getResponseClass());
            System.out.println(new Gson().toJson(t));
            return t;
          }
        });
    BrawlersApi brawlersApi = brawlJarsApi.getApi(BrawlersApi.class);
    BrawlersResponse brawlersResponse = brawlersApi.getBrawlers(BrawlersRequest.builder()
        .limit(1)
        .build()
    );
  }
}
