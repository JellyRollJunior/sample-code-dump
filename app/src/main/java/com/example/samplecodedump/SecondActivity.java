package com.example.samplecodedump;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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
    private static final int NUM_ROW = 5;
    private static final int NUM_COL = 6;
    Button[][] buttons = new Button[NUM_ROW][NUM_COL];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Sample Three
        SampleThreeEndActivity();

        // Sample Four - do something with extra
        sampleFourStringExtra();

        // Sample Twelve - enable up button
        sampleTwelveUpButton();

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

            // scale rows to fill layout
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            dynamicButtonLayout.addView(tableRow);

            for (int col = 0; col < NUM_COL; col++) {
                Button button = new Button(this);

                // button array numbering for debugging
                String buttonMessage = "" + col + ", " + row;
                button.setText(buttonMessage);

                // scale buttons to fill layout
                button.setPadding(0,0,0,0);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));

                // cannot use non-final in an inner class -> send out a final
                final int FINAL_COL = col;
                final int FINAL_ROW = row;
                button.setOnClickListener(view -> gridButtonClicked(FINAL_COL, FINAL_ROW));

                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }

    }

    // sample 12: up button
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

    private void sampleTwelveUpButton() {
        ActionBar ab = getSupportActionBar();
        Objects.requireNonNull(ab).setDisplayHomeAsUpEnabled(true);
    }

    private void sampleFourStringExtra() {
        Intent i = getIntent();
        String messageSampleFour = i.getStringExtra(EXTRA_MESSAGE);
        // note: if coming from intent which doesn't supply extra (sample three button) -> string is empty
        if (messageSampleFour != null) {
            Toast.makeText(SecondActivity.this, messageSampleFour, Toast.LENGTH_SHORT).show();
        }
    }

    public static Intent makeIntentSampleFour(Context context, String message) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        return intent;
    }

    private void gridButtonClicked(int col, int row) {
        Toast.makeText(this, "button clicked: " + col + ", " + row, Toast.LENGTH_SHORT).show();
        Button button = buttons[row][col];

        // otherwise they will change sizes on click when adding drawable image
        lockButtonSizes();

        // does not scale image to button size
//        button.setBackgroundResource(R.drawable.animal_crossing_leaf);

        // scale image size with button
        int netWidth = button.getWidth();
        int netHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.animal_crossing_leaf);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, netWidth, netHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));   // can also just go context.getResources()
    }

    private void lockButtonSizes() {
        for (int row = 0; row < NUM_ROW; row++) {
            for (int col = 0; col < NUM_COL; col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
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



    // sample 14 - allow other activities to access string extra without knowing the TAG
    public static String getResultMessageCodeSampleFourteen(Intent intent) {
        return intent.getStringExtra(EXTRA_MESSAGE_SAMPLE_FOURTEEN);
    }
}