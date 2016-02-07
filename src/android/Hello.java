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

public class Hello extends CordovaPlugin {
    private WifiManager wifiManager;
    private CallbackContext callbackContext;
    
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.wifiManager = (WifiManager) cordova.getActivity().getSystemService(Context.WIFI_SERVICE);
    }

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("greet")) {

            if(wifiManager.isWifiEnabled()){
                
                WifiInfo info = wifiManager.getConnectionInfo();
                String rssi = info.getRssi();
                callbackContext.success(rssi);
            
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
