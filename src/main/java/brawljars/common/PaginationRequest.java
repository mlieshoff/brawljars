package brawljars.common;

import static brawljars.common.Utils.isNotBlank;

import java.util.Map;

public class PaginationRequest extends Request {

  static final String QUERY_PARAM_LIMIT = "limit";
  static final String QUERY_PARAM_AFTER = "after";
  static final String QUERY_PARAM_BEFORE = "before";

  private final int limit;

  private final String after;
  private final String before;

  protected PaginationRequest(int limit, String after, String before, boolean storeRawResponse) {
    super(storeRawResponse);
    this.limit = limit;
    this.after = after;
    this.before = before;
  }

  public Map<String, String> getQueryParameters() {
    Map<String, String> map = super.getQueryParameters();
    if (limit > 0) {
      map.put(QUERY_PARAM_LIMIT, String.valueOf(limit));
    }
    if (isNotBlank(after)) {
      map.put(QUERY_PARAM_AFTER, after);
    }
    if (isNotBlank(before)) {
      map.put(QUERY_PARAM_BEFORE, before);
    }
    return map;
  }

}
