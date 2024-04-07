package com.server.hotel.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiaoxu
 * @date 2022-11-19 21:52
 * crawlerJ:com.xiaoxu.crawler.utils.StringUtils
 */
public class StrUtils {
    private static final Pattern TPATTERN = Pattern.compile("[A-Z0-9]");

    public static String camelToUnderline(String str) {
        Matcher matcher = TPATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


}
