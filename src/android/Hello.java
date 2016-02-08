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
import java.net.URL;

public class Hello extends CordovaPlugin {
    private WifiManager wifiManager;
    private CallbackContext callbackContext;
    
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.wifiManager = (WifiManager) cordova.getActivity().getSystemService(Context.WIFI_SERVICE);
    }
    
    private String downloadUrl(String myurl) throws IOException 
    {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 );
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("log", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

        // Makes sure that the InputStream is closed after the app is
        // finished using it.
        } finally {
            if (is != null) {
                is.close();
            } 
        }
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
