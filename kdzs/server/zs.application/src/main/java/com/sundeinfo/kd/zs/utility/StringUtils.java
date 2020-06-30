package com.sundeinfo.kd.zs.utility;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public StringUtils() {
    }

    public static String lowerFirst(String str) {
        return isBlank(str) ? "" : str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static String upperFirst(String str) {
        return isBlank(str) ? "" : str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        } else {
            return str.trim().length() == 0;
        }
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isBlanks(String str) {
        if (str == null) {
            return true;
        } else if (str.trim().length() == 0) {
            return true;
        } else {
            return "null".equals(str.trim());
        }
    }

    public static boolean isNotBlanks(String str) {
        return !isBlanks(str);
    }

    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        } else {
            String regEx = "<.+?>";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(html);
            String s = m.replaceAll("");
            return s;
        }
    }

    public static String abbr(String str, int length) {
        if (str == null) {
            return "";
        } else {
            try {
                StringBuilder sb = new StringBuilder();
                int currentLength = 0;
                char[] var7;
                int var6 = (var7 = replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()).length;

                for(int var5 = 0; var5 < var6; ++var5) {
                    char c = var7[var5];
                    currentLength += String.valueOf(c).getBytes("GBK").length;
                    if (currentLength > length - 3) {
                        sb.append("...");
                        break;
                    }

                    sb.append(c);
                }

                return sb.toString();
            } catch (UnsupportedEncodingException var8) {
                var8.printStackTrace();
                return "";
            }
        }
    }

    public static String UUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }

    public static String rabbr(String str, int length) {
        return abbr(replaceHtml(str), length);
    }

    public static Double toDouble(Object val) {
        if (val == null) {
            return 0.0D;
        } else {
            try {
                return Double.valueOf(trim(val.toString()));
            } catch (Exception var2) {
                return 0.0D;
            }
        }
    }

    public static Float toFloat(Object val) {
        return toDouble(val).floatValue();
    }

    public static Long toLong(Object val) {
        return toDouble(val).longValue();
    }

    public static Integer toInteger(Object val) {
        return toLong(val).intValue();
    }
}
