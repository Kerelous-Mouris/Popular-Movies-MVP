package com.example.popularmovies.pojo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public final class HttpRequest {

    public static String request(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {


            InputStream in = urlConnection.getInputStream();
            Scanner s = new Scanner(in);
            s.useDelimiter("\\A");

            boolean hasInput = s.hasNext();
            if (hasInput)
            {
                return s.next();
            }
            else
            {
                return null;
            }

        }
        finally {
            urlConnection.disconnect();
        }

    }
}
