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
