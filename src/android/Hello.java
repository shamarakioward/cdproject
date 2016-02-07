package com.example.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiEnterpriseConfig;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.SupplicantState;
import android.content.Context;
import android.util.Log;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URL;

public class Hello extends CordovaPlugin {
    private WifiManager wifiManager;
    private HttpURLConnection connection; 
    private CallbackContext callbackContext;
    
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.wifiManager = (WifiManager) cordova.getActivity().getSystemService(Context.WIFI_SERVICE);
        this.connection = (HttpURLConnection) cordova.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("greet")) {

            if(wifiManager.isWifiEnabled()){
                
                WifiInfo info = wifiManager.getConnectionInfo();
                int rssi = info.getRssi();
                int usersig = wifiManager.calculateSignalLevel(rssi,5);
                JSONObject obj = new JSONObject();
                obj.put("rssi", Integer.toString(rssi));
                obj.put("user", Integer.toString(usersig));
                URL url = new URL("http://google.com");
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                
                int code = connection.getResponseCode();
                obj.put("accesscode", Integer.toString(code));
                callbackContext.success(obj);
            
            return true;
        }else{
            
            callbackContext.error("Wifi is disabled");
        }
    

    


            

            return true;

        } else {
            
            return false;

        }
    }
}
