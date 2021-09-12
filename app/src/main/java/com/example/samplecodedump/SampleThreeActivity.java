package com.example.samplecodedump;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SampleThreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_three);

        SampleThreeEndActivity();
    }

    private void SampleThreeEndActivity() {
        Button btnSampleThree = (Button) findViewById(R.id.btnSampleThreeEnd);
        btnSampleThree.setOnClickListener(view -> {
            finish();
        });
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, SampleThreeActivity.class);
    }

}