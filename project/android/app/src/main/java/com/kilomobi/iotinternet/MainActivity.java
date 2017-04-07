/*
 * Copyright (c) KiloMobi - 2017.
 */

package com.kilomobi.iotinternet;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

interface OnTaskCompleted {
    void onTaskCompleted(List<List<Entry>> result);
}

public class MainActivity extends AppCompatActivity implements OnTaskCompleted {

    OnTaskCompleted listener;
    boolean repeat;
    boolean isDebug;
    TextView tvPingState;
    TextView tvLocalState;
    TextView tvExternalState;
    LineChart chart;
    String url = "https://api.mlab.com/api/1/databases/smartobject/collections/internet?apiKey=z98ehgf31t5H6LIasfsSWdvL4sASQ16b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.kilomobi.iotinternet.R.layout.activity_main);

        isDebug = false;
        chart = (LineChart) findViewById(R.id.activity_chart);

        tvPingState = (TextView) findViewById(R.id.activity_tv_networkState);
        tvLocalState = (TextView) findViewById(R.id.activity_tv_localState);
        tvExternalState = (TextView) findViewById(R.id.activity_tv_externalState);
        Button btnUpdate = (Button) findViewById(com.kilomobi.iotinternet.R.id.btnUpdate);
        Switch swAutoRefresh = (Switch) findViewById(com.kilomobi.iotinternet.R.id.activity_sw_autoRefresh);

        listener = this;

        // -----------------------------------------------------
        // Debug content, generate fake datas
        // -----------------------------------------------------
        if (isDebug) {

            final MockDatas mockDatas = new MockDatas(listener);

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mockDatas.generateFakeDatas();
                }
            });

            swAutoRefresh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    repeat = isChecked;
                    if (isChecked)
                        mockDatas.generateFakeDatas();
                }
            });

            mockDatas.generateFakeDatas();
        }
        // -----------------------------------------------------
        // Real datas, through API access
        // -----------------------------------------------------
        else {
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    httpGetValues();
                }
            });

            swAutoRefresh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    repeat = isChecked;
                    if (isChecked)
                        httpGetValues();
                }
            });

            httpGetValues();
        }

    }

    private void generateData(List<List<Entry>> entries) {
        LineDataSet externalDataSet = new LineDataSet(entries.get(0), "External Network"); // add entries to dataset
        externalDataSet.setColor(Color.BLUE);
        externalDataSet.setValueTextColor(Color.BLACK); // styling, ...

        LineDataSet localDataSet = new LineDataSet(entries.get(1), "Local Network"); // add entries to dataset
        localDataSet.setColor(Color.RED);
        localDataSet.setValueTextColor(Color.BLACK); // styling, ...

        // use the interface ILineDataSet
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(externalDataSet);
        dataSets.add(localDataSet);

        chart.animateX(2500);
        Description description = new Description();
        description.setText("netges");
        chart.setDescription(description);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTextSize(11f);
        l.setTextColor(Color.BLACK);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setYOffset(11f);

        XAxis xAxis = chart.getXAxis();
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);

        chart.getAxisRight().setEnabled(false); // no right axis
        chart.setDragEnabled(true);
        chart.setDoubleTapToZoomEnabled(true);
        chart.setPinchZoom(false);

        LineData data = new LineData(dataSets);
        chart.setData(data);
        chart.invalidate(); // refresh
    }

    private void handlePing(double localPing, double externalPing) {
        tvLocalState.setText("Local : " + Math.round(localPing) + " ms");
        tvExternalState.setText("External : " + Math.round(externalPing) + " ms");
        tvPingState.setText("Global : " + Math.round((localPing+externalPing)/2) + " ms");
    }

    private void httpGetValues () {
        new GetValuesAsync(listener).execute(url);

        if (this.repeat) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    httpGetValues();
                }
            }, 5000);
        }
    }

    @Override
    public void onTaskCompleted(List<List<Entry>> result) {

        if (result != null) {
            if (isDebug) {
                generateData(result);
            }
            else {
                generateData(result);
            }
            if (result.size() != 0)
            {
                double local, external;
                local = result.get(0).get(result.get(0).size()-1).getY();
                external = result.get(1).get(result.get(1).size()-1).getY();
                handlePing(local, external);
                Toast.makeText(MainActivity.this, "Mis à jour avec succès", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, "Aucune donnée disponible", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Erreur lors de la mis à jour", Toast.LENGTH_SHORT).show();
        }

    }
    // modifier la couleur
    // block l'orientation
    // en fonction de la température, modifier la couleur
    // transformer l'axe des abscisses

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
}
