package com.example.blackjackjavafx.Application.lib;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class Password extends PhpRequest {

    private String url = "https://webinfo.iutmontp.univ-montp2.fr/~garciac/blackjackserver/src/lib/Password.php";

    public boolean isSecure(String password)
    {
        if(password == null)
        {
            return false;
        }
        Map<String, String> password1 = Map.of("password", password);
        String result = Request("IsSecurePassword",password1);
        boolean boolValue = Boolean.parseBoolean(result);
        return boolValue;
    }

    @Override
    protected URL url() throws MalformedURLException {
        return new URL(url);
    }
}
