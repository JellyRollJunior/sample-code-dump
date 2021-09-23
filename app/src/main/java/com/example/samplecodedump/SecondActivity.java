package com.example.samplecodedump;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

    private static final String EXTRA_MESSAGE = "com.example.samplecodedump.SecondActivity - message";
    public static final String EXTRA_MESSAGE_SAMPLE_FOURTEEN = "com.example.samplecodedump.SecondActivity - nameSampleFourteen";

    // sample fifteen
    private static final int NUM_ROW = 2;
    private static final int NUM_COL = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Sample Three
        SampleThreeEndActivity();

        // Sample Four - do something with extra
        Intent i = getIntent();
        String messageSampleFour = i.getStringExtra(EXTRA_MESSAGE);
        // note: if coming from intent which doesn't supply extra (sample three button) -> string is empty
        Toast.makeText(SecondActivity.this, messageSampleFour, Toast.LENGTH_SHORT).show();

        // Sample Twelve - enable up button
        ActionBar ab = getSupportActionBar();
        Objects.requireNonNull(ab).setDisplayHomeAsUpEnabled(true);

        // Sample Fourteen
        Button btnSampleFourteen = findViewById(R.id.btnReturnSample14);
        btnSampleFourteen.setOnClickListener(view -> {
            EditText etSampleFour = findViewById(R.id.editTextTextPersonName);
            String messageSampleFourteen = etSampleFour.getText().toString();

            // pass data back
            Intent intent = new Intent();
            intent.putExtra(EXTRA_MESSAGE_SAMPLE_FOURTEEN, messageSampleFourteen);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

        // Sample Fifteen
        TableLayout dynamicButtonLayout = findViewById(R.id.layoutButtonSampleFifteen);
        for (int row = 0; row < NUM_ROW; row++ ) {
            TableRow tableRow = new TableRow(this);
            dynamicButtonLayout.addView(tableRow);
            for (int col = 0; col < NUM_COL; col++) {
                Button button = new Button(this);
                tableRow.addView(button);
            }
        }

    }

    private void SampleThreeEndActivity() {
        Button btnSampleThree = findViewById(R.id.btnSampleThreeEnd);
        btnSampleThree.setOnClickListener(view -> finish());
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, SecondActivity.class);
    }

    public static Intent makeIntentSampleFour(Context context, String message) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        return intent;
    }

    // sample 12
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(SecondActivity.this, "Yup, going UP!", Toast.LENGTH_SHORT).show();

                // tell up button where to go from here
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // sample 14 - allow other activities to access string extra without knowing the TAG
    public static String getResultMessageCodeSampleFourteen(Intent intent) {
        return intent.getStringExtra(EXTRA_MESSAGE_SAMPLE_FOURTEEN);
    }
}