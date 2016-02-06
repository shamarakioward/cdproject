package com.example.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class Hello extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("greet")) {

            ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

if (mWifi.isConnected()) {
    callbackContext.success(message);
}

            

            return true;

        } else {
            
            return false;

        }
    }
}
