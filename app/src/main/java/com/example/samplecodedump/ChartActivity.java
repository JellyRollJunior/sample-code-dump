package com.example.samplecodedump;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {
    float[] articles = {10.0f, 20.0f, 30.0f, 5.0f, 15.0f, 25.0f};
    String[] articleNames = {"Pants", "T-Shirts", "Jackets", "Shoes", "Socks", "Hats"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        // Sample Twenty Two
        setupPieChart();
    }

    private void setupPieChart() {
        // populate list of pie entries
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < articles.length; i++) {
            pieEntries.add(new PieEntry(articles[i], articleNames[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, getString(R.string.chart_title_my_clothing));
        // Color Template is the provided default template (can create your own)
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        // get the chart
        PieChart chart = (PieChart) findViewById(R.id.chartSampleTwentyTwo);
        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate();     // make chart redraw
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, ChartActivity.class);
    }
}
