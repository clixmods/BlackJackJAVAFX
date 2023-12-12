package com.example.blackjackjavafx.Application.lib.request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public abstract class Request {
    protected abstract URL url() throws MalformedURLException;

    private static String mapToQueryString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder queryString = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (queryString.length() > 0) {
                queryString.append("&");
            }
            queryString.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            queryString.append("=");
            queryString.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return queryString.toString();
    }


    public String RequestMethod(String methodName, Map<String, String> postData) {
        try {
            // URL of the PHP script
            URL url = new URL(url() + "?method=" + methodName);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST or GET, depending on your PHP script
            connection.setRequestMethod("POST");

            // Enable input/output streams
            connection.setDoOutput(true);

            // If you are sending data to the PHP script, you can do it like this:
            String queryString = mapToQueryString(postData);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = queryString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            System.out.println("Query to PHP script: " + queryString);

            StringBuilder response = new StringBuilder();
            // Get the response from the PHP script
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                System.out.println("Response from PHP script: " + response.toString());

            }

            // Close the connection
            connection.disconnect();
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String Request( String requestMethod ,Map<String, String> postData) {
        try {




            // If you are sending data to the PHP script, you can do it like this:
            String queryString = mapToQueryString(postData);

            byte[] input = queryString.getBytes("utf-8");

            System.out.println("Query to PHP script: " + queryString);

            // URL of the PHP script
            URL url = new URL(url() +"?"+queryString) ;
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Enable input/output streams
            connection.setDoOutput(true);

            StringBuilder response = new StringBuilder();
            // Get the response from the PHP script
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                System.out.println("Response from PHP script: " + response.toString());

            }

            // Close the connection
            connection.disconnect();
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}