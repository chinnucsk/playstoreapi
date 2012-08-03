package com.github.pbuda.playstoreapi;

import android.util.*;
import com.github.pbuda.playstoreapi.mode.PlayStoreProtos.*;
import com.google.protobuf.*;

import java.io.*;
import java.net.*;
import java.util.zip.*;

/**
 * .
 */
public class MultipleDetailsRequestExecutor extends PlayStoreRequest {

    private String authToken;

    public Message execute(Message message) throws IOException {
        HttpURLConnection cnx = configureConnection();

        String request64 = Base64.encodeToString(message.toByteArray(), Base64.URL_SAFE);
        String requestData = "version=2&request=" + request64;


        cnx.setFixedLengthStreamingMode(requestData.getBytes("UTF-8").length);
        OutputStream os = cnx.getOutputStream();
        os.write(requestData.getBytes());
        os.close();

        if (cnx.getResponseCode() >= 400) {
            throw new RuntimeException("Response code = " + cnx.getResponseCode() +
                    ", msg = " + cnx.getResponseMessage());
        }

        InputStream is = cnx.getInputStream();
        GZIPInputStream gzIs = new GZIPInputStream(is);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        while (true) {
            int nb = gzIs.read(buff);
            if (nb < 0)
                break;
            bos.write(buff, 0, nb);
        }
        is.close();
        cnx.disconnect();

        return MultipleDetailsResponse.parseFrom(bos.toByteArray());
    }

    private HttpURLConnection configureConnection() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(getUrl()).openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Cookie", "ANDROID=" + authToken);
        connection.setRequestProperty("User-Agent", "Android-Market/2 (sapphire PLAT-RC33); gzip");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
        return connection;
    }
}
