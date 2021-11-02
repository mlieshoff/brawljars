package brawljars.v2.api;

import java.io.IOException;

public interface Connector {

  <T> T get(RequestContext requestContext) throws IOException;

}
