public class Matcher {
  public static String match(String str, String pt) {
    Pattern pat = Pattern.compile(pt);
    Matcher m = pat.matcher(str);
    String res = "";
    while (m.find()) {
        res = m.group(1);
    }
    return res;
}
}
