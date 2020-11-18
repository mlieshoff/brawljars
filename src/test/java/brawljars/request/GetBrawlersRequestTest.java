package brawljars.request;

class GetBrawlersRequestTest extends PageableRequestTestBase<GetBrawlersRequest> {

  @Override
  Object getBuilder() {
    return GetBrawlersRequest.builder();
  }

}