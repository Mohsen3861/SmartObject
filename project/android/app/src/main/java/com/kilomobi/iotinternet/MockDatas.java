/*
 * Copyright (c) KiloMobi - 2017.
 */

package com.kilomobi.iotinternet;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by fabrice on 22/02/2017.
 */

public class MockDatas {

    private OnTaskCompleted listener;

    public MockDatas(OnTaskCompleted listener) {
        this.listener = listener;
    }

    public void generateFakeDatas() {
        // turn your data into Entry objects
        List<Entry> localData = new ArrayList<>();
        List<Entry> externalData = new ArrayList<>();

        List<List<Entry>> myDatas = new ArrayList<>();

        myDatas.add(localData);
        myDatas.add(externalData);

        for (int i = 0; i < 23; i++) {
            Entry data = new Entry(i, getRandomNumberInRange(0,42));
            myDatas.get(0).add(data);
        }
        for (int i = 0; i < 23; i++) {
            Entry data = new Entry(i, getRandomNumberInRange(0,42));
            myDatas.get(1).add(data);
        }
        listener.onTaskCompleted(myDatas);
    }

    private int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private String getRandomStringInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return String.valueOf(r.nextInt((max - min) + 1) + min);
    }

    private List<List<Entry>> jsonToDataSet(List<Entry> entries) {
        List<ILineDataSet> dataSets;
        return null;
    }
}
