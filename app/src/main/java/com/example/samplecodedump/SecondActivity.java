package com.example.samplecodedump;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private static final String EXTRA_MESSAGE = "Extra - message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Sample Three
        SampleThreeEndActivity();

        // Sample Four - do something with extra
        Intent i = getIntent();
        String messageSampleFour = i.getStringExtra(EXTRA_MESSAGE);
        Toast.makeText(SecondActivity.this, messageSampleFour, Toast.LENGTH_LONG).show();

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void SampleThreeEndActivity() {
        Button btnSampleThree = (Button) findViewById(R.id.btnSampleThreeEnd);
        btnSampleThree.setOnClickListener(view -> {
            finish();
        });
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, SecondActivity.class);
    }

    public static Intent makeIntentSampleFour(Context context, String message) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        return intent;
    }
}