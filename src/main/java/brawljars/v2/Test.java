package brawljars.v2;

import java.io.IOException;
import brawljars.v2.api.BrawlJars;
import brawljars.v2.api.Connector;
import brawljars.v2.api.RequestContext;
import brawljars.v2.api.brawlers.BrawlersApi;
import brawljars.v2.api.brawlers.generated.BrawlersResponse;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Test {

  public static void main(String[] args) throws IOException {
    BrawlJars brawlJarsApi = new BrawlJars("https://bsproxy.royaleapi.dev/v1",
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImUwNDE4ODZiLTBhNjAtNDUxNi1iNTk4LTMzMDA0MzNlMzkxZiIsImlhdCI6MTYwNTAwNDc3OCwic3ViIjoiZGV2ZWxvcGVyL2YzYTFhNTVmLWIyYzItZjRmMS1iY2Q2LWM3NmMxY2M1Nzg2YiIsInNjb3BlcyI6WyJicmF3bHN0YXJzIl0sImxpbWl0cyI6W3sidGllciI6ImRldmVsb3Blci9zaWx2ZXIiLCJ0eXBlIjoidGhyb3R0bGluZyJ9LHsiY2lkcnMiOlsiMTI4LjEyOC4xMjguMTI4Il0sInR5cGUiOiJjbGllbnQifV19.65JTwAPPVB4-Irj_qPG3o44sp27QJeXQxhB0yEf2PBGS7I8oHPl4xeUlM8T4V_YjV1wKNRnPLoVd8bNvCoGIfw",
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
            System.out.println(response);
            System.out.println(response.body().string());
            return null;
          }
        });
    BrawlersApi brawlersApi = brawlJarsApi.getApi(BrawlersApi.class);
    BrawlersResponse brawlersResponse = brawlersApi.getBrawlers();
  }
}
