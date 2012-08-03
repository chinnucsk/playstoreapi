package com.github.pbuda.playstoreapi;

import java.util.*;

/**
 * .
 */
public class PlayStoreRequest {

    public PlayStoreRequest() {
        this.url = "https://android.clients.google.com/fdfe/";
        configureHeaders();
    }

    private void configureHeaders() {
        headers = new HashMap<String, String>();
        headers.put("X-DFE-Device-Id", ""); //apparently this is the AndroidID (http://blog.codepainters.com/2012/01/17/how-to-obtain-gtalk-android-id/)
        headers.put("Accept-Language", "");
        headers.put("User-Agent", ""); //user agent
        headers.put("X-DFE-SmallestScreenWidthDp", ""); //
        headers.put("X-DFE-Filter-Level", ""); //
        //optional stuff
        headers.put("X-DFE-MCCMNC", "");
        headers.put("X-DFE-Client-Id", "");
        headers.put("X-DFE-Logging-Id", "");
    }

    private Map<String, String> headers;

    public Map<String, String> getHeaders() {
        return headers;
    }

    private String url;

    public String getUrl() {
        return url;
    }
}
