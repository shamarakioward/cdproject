package com.example.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class Hello extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("greet")) {

            
wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

    callbackContext.success("Yayyyy");


            

            return true;

        } else {
            
            return false;

        }
    }
}
