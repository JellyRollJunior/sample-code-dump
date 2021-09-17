package com.example.samplecodedump;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SampleCodeDump";     // TAG used to show where log error messages originate from
    private int sampleTwoCount = -1;                        // Can't declare in the scope of onCreate or else problems arise

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     // chooses which activity layout is shown on create

        // SAMPLE 1: Create/set Button with Toast
        /*
            1. make a button in activity
            2. set id of button
            3. get button in java
                a. R is a class that can gets id's
            4. wire button to do stuff
         */
         /*
            NOTES:
                >log.i is used as error messages for developers
                >Toast makes a text message appear at bottom on screen
                    >Toast.LENGTH_LONG or Toast.LENGTH_SHORT
                >.show() is on a diff line to make it clear the Toast is shown
         */
        Button btnSampleOne = (Button) findViewById(R.id.btnSampleOne);
        btnSampleOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "this is a magic log message");
                Toast.makeText(getApplicationContext(), "it's magic!", Toast.LENGTH_LONG)
                        .show();
            }
        });


        // Sample 2: TextViews and Strings.xml, Wire button with TextView
        /*
            1. make a TextView
            2. Enter attributes + text
            3. In code view, option + enter to extract string to resource folder
                a. benefit: can just swap string resource files for different app language versions
            4. wire to OnClickListener
        */
        /*
            Notes:
                >use lambda to clean code when making anonymous objects
                >get strings from strings.xml with R.string.stringName
        */
        Button btnSampleTwo = (Button) findViewById(R.id.btnSampleTwo);
        btnSampleTwo.setOnClickListener(view -> {
            sampleTwoCount++;
            TextView textViewSampleTwo = (TextView) findViewById(R.id.tvSampleTwo);

            String messageSampleTwo = getString(R.string.tvSampleTwoCount) + sampleTwoCount;
            textViewSampleTwo.setText(messageSampleTwo);
        });


        // Sample 3: Switching activities, killing activities
        /*
            1. create a button (or other widget)
            2. wire button
            3. create an intent
                a. move complexity of this to the new activity
            4. start activity with intent
            5. end activity with finish();
        */
        /*
            Notes:
                >move complexity of starting "new activity" to "new activity" by using static factory
        */
        Button btnSampleThree = (Button) findViewById(R.id.btnSampleThree);
        btnSampleThree.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "welcome to the second activity", Toast.LENGTH_LONG)
                    .show();

            // launch second activity (naive method)
//            Intent intent = new Intent(MainActivity.this,SampleThreeActivity.class);
//            startActivity(intent);

            // better method
            Intent intent = SecondActivity.makeIntent(MainActivity.this);
            startActivity(intent);
        });

        // Sample 4: Create/wire floating action button, material design layout, String Extras
        /*
            import images
                1. right - click -> new -> import vector/image assets
    */
        /*
            Notes:
                >use app:tint=:"@null" to make fab icon the correct color
                >extras can be accessed by intents
        */
        FloatingActionButton fabSampleFour = (FloatingActionButton) findViewById(R.id.fabSampleFour);
        fabSampleFour.setOnClickListener( view -> {
            Intent intent = SecondActivity.makeIntentSampleFour(MainActivity.this, "hello world!");
            startActivity(intent);
        });

        // Sample 5: Constraints
        /*
            1. drag an object into activity
            2. hover over object
            3. drag dots to other objects or activity to apply constraints
            4. use Guidelines -> can choose from left, right, or percentage ~!¡¡of screen
                a. LEFT: layout_constraintGuide_begin = "250dp"
                b. RIGHT: layout_constraintGuide_end = "250dp"
                c. PERCENT: layout_constraintGuide_percent = "0.25"
        */
        /*
            Notes:
                >do not hard code width or length -> use constraints
                >object location can be set to certain dpi from other objects
                >use horizontal bias to move stuff left and right
                >wrap to contents is ugly -> use wrap to constraints
                >centre an widget on an anchor by lining up top and bottom constraints on anchor
        */

        // Sample 6: locking orientation
        /*
            1. AndroidManifest
            2. find activity
            3. android:screenOrientation="Landscape / portrait / reversePortrait / reverseLandscape"
        */
        /*
            Notes:
                >on orientation change, android restarts current activity. can lead to problems
                    >solution: android:configChanges="keyboard | keyboardHidden | orientation | screenSize"
        */

        // Sample
        /*
            1.
        */
        /*
            Notes:
                >
        */
    }
}