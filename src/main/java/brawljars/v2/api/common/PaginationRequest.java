package brawljars.v2.api.common;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PaginationRequest extends Request {

  static final String QUERY_PARAM_LIMIT = "limit";
  static final String QUERY_PARAM_AFTER = "after";
  static final String QUERY_PARAM_BEFORE = "before";

  private final int limit;

  private final String after;
  private final String before;

  @Builder(builderMethodName = "paginationRequestBuilder")
  protected PaginationRequest(int limit, String after, String before) {
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
