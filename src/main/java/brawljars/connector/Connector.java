package brawljars.connector;

import brawljars.common.RawResponse;

public interface Connector {

  <T> T get(RequestContext requestContext) throws ConnectorException;

  RawResponse getLastRawResponse();

}
