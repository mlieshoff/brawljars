package brawljars.common;

import java.util.Map;

public class Utils {

  public static boolean isNotEmpty(Map<?, ?> map) {
    return map != null && map.size() > 0;
  }

  public static boolean isNotEmpty(Object[] array) {
    return array != null && array.length > 0;
  }

  public static boolean isBlank(String s) {
    return s == null || s.isEmpty();
  }

  public static boolean isNotBlank(String s) {
    return !isBlank(s);
  }

  public static void require(String key, String s) {
    if (isBlank(s)) {
      throw new IllegalArgumentException(key + " must be set! But was: " + s);
    }
  }

  public static void require(String key, Object o) {
    if (o == null) {
      throw new IllegalArgumentException(key + " must be set! But was: " + o);
    }
  }

}
