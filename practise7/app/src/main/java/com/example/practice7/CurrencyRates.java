package com.example.practice7;

import android.app.ListActivity;
import android.os.Bundle;

import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class CurrencyRates extends ListActivity {

    private final static String KEY_CHAR_CODE = "CharCode";
    private final static String KEY_VALUE = "Value";
    private final static String KEY_NOMINAL = "Nominal";
    private final static String KEY_NAME = "Name";

    private final static String KEY_BASE = "baseCurrency";
    private final static String KEY_CURR = "currency";
    private final static String KEY_RATE = "saleRateNB";
    private static final String KEY_EXCHANGE = "exchangeRate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populate();
    }

    private void populate() {
        ArrayList<Map<String, String>> data = getData();
        String[] from = { KEY_CHAR_CODE, KEY_VALUE,
                KEY_NOMINAL, KEY_NAME };
        int[] to = { R.id.charCodeView, R.id.valueView, R.id.nominalView,

                R.id.nameView };

        SimpleAdapter sa = new SimpleAdapter(this, data,
                R.layout.item_view,
                from, to);
        setListAdapter(sa);
    }

    private ArrayList<Map<String, String>> getData() {
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> m;

        try {
            URL url = new URL(getString(R.string.rates_url));
            String response;
            try (InputStream input = url.openStream()) {
                InputStreamReader isr = new InputStreamReader(input);
                BufferedReader reader = new BufferedReader(isr);
                StringBuilder json = new StringBuilder();
                int c;
                while ((c = reader.read()) != -1) {
                    json.append((char) c);
                }
                response = json.toString();
            }

            JSONObject json = new JSONObject(response);
            JSONArray rates = json.getJSONArray(KEY_EXCHANGE);

            for(int i = 0; i < rates.length(); i++){
                JSONObject rate = rates.getJSONObject(i);

                // Storing each json item in variable
                String base = rate.getString(KEY_BASE);
                String curr = rate.getString(KEY_CURR);
                String rt = rate.getString(KEY_RATE);

                m = new HashMap<String, String>();

                m.put(KEY_CHAR_CODE, Integer.toString(i));
                m.put(KEY_VALUE, rt);
                m.put(KEY_NOMINAL, Integer.toString(1));

                m.put(KEY_NAME, curr);
                list.add(m);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
