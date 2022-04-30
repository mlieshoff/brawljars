package brawljars.connector;

import brawljars.common.IResponse;

public interface Connector {

  <T extends IResponse> T get(RequestContext requestContext) throws ConnectorException;

}
