package com.katushkahey.avitomap.utils;

import android.content.Context;

import com.katushkahey.avitomap.Pin;
import com.katushkahey.avitomap.Pins;
import com.katushkahey.avitomap.R;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private List<Pin> totalList = new ArrayList<>();
    private List<String> services = new ArrayList<>();
    private Pins PINS = new Pins();

    public void parsingJSON(Context context) {
        try {
            String jsonText = readText(context, R.raw.pins);
            Moshi moshi = new Moshi.Builder().build();
            JsonAdapter<Pins> jsonAdapter = moshi.adapter(Pins.class);
            PINS = jsonAdapter.fromJson(jsonText);
            services = PINS.getServices();
            totalList = PINS.getPins();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readText(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String s = null;
        while ((s = br.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    public List<Pin> getTotalList() {
        return totalList;
    }

    public List<String> getServices() {
        return services;
    }
}
