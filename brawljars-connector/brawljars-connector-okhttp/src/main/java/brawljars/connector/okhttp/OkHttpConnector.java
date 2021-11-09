package brawljars.connector.okhttp;

import com.google.gson.Gson;

import java.io.IOException;
import brawljars.Connector;
import brawljars.RequestContext;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpConnector implements Connector {

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
    return (T) new Gson().fromJson(json, requestContext.getResponseClass());
  }

}
