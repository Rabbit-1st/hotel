package com.server.hotel.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class ServletUtils {

    public static String getImageUrl(String imageName) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        StringBuffer requestURL = request.getRequestURL();
        String servletPath = request.getServletPath();
        int index = requestURL.indexOf(servletPath);

        // Add a random parameter to the URL to prevent caching
        String uniqueParam = "v=" + System.currentTimeMillis(); // Use a timestamp as the random parameter
        String imageUrl = requestURL.delete(index + 1, requestURL.length()) + "images/" + imageName;

        if (imageUrl.contains("?")) {
            imageUrl = imageUrl + "&" + uniqueParam;
        } else {
            imageUrl = imageUrl + "?" + uniqueParam;
        }

        return imageUrl;
    }
}