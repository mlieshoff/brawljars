package brawljars.response;

class GetBrawlersResponseTest extends PageableResponseTestBase<GetBrawlersResponse> {

  @Override
  GetBrawlersResponse getResponse() {
    return new GetBrawlersResponse();
  }

}