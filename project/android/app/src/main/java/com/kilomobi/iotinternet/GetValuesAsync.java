/*
 * Copyright (c) KiloMobi - 2017.
 */

package com.kilomobi.iotinternet;

import android.os.AsyncTask;
import android.util.Log;

import com.github.mikephil.charting.data.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by soulierantoine on 21/07/16.
 */


public class GetValuesAsync extends AsyncTask<String, Void, String> {
    private OnTaskCompleted listener;

    public GetValuesAsync(OnTaskCompleted listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection = null;
        String response = "";

        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                response = readStream(urlConnection.getInputStream());
            } else {
                response = "Error";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

        @Override
        protected void onPostExecute(String result) {

            List<JsonModel> jsonModels = null;
            try {
                jsonModels = new ArrayList<>();
                JSONArray jsonArray = new JSONArray(result);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    jsonModels.add(new JsonModel(jsonObject.getString("idObject"),jsonObject.getDouble("localNetwork"), jsonObject.getDouble("externalNetwork"), jsonObject.getString("timestamp")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            listener.onTaskCompleted(jsonModelToListListEntry(jsonModels));

            super.onPostExecute(result);
        }

        private List<List<Entry>> jsonModelToListListEntry(List<JsonModel> datas) {
            if (datas.size() == 0)
                return null;
            List<Entry> localData = new ArrayList<>();
            List<Entry> externalData = new ArrayList<>();

            List<List<Entry>> myEntries = new ArrayList<>();

            for (JsonModel data :
                    datas) {
                localData.add(new Entry(getHourFromDate(data.getTimestamp()), (float)data.getLocalNetwork()));
                externalData.add(new Entry(getHourFromDate(data.getTimestamp()), (float)data.getExternalNetwork()));
            }

            myEntries.add(localData);
            myEntries.add(externalData);

            return myEntries;
        }

//        private List<Entry> removeDuplicate(List<Entry> listData) {
//            float x = -1;
//            for (Entry data :
//                    listData) {
//                if (x == data.getX())
//                    listData.remove(data);
//
//            }
//        }

    private int getHourFromDate(String timestamp){
        Long tp = Long.parseLong(timestamp);
        Date date;
        if (timestamp.length() == 10)
            date = new Date(Long.valueOf(timestamp)*1000);
        else
            date = new Date(Long.valueOf(timestamp));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

        private String readStream(InputStream in) {
            BufferedReader reader = null;
            StringBuffer response = new StringBuffer();
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return response.toString();
        }
}
