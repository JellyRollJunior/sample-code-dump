package com.example.samplecodedump;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SampleCodeDump";     // TAG used to show where log error messages originate from
    private int sampleTwoCount = -1;                        // Can't declare in the scope of onCreate or else problems arise
    private final ArrayList<StringHolder> myStringHolderSampleTen = new ArrayList<>();
    private final ArrayList<StringHolder> myStringHolderSampleEleven = new ArrayList<>();

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
        Button btnSampleOne = findViewById(R.id.btnSampleOne);
        btnSampleOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "this is a magic log message");
                Toast.makeText(getApplicationContext(), "it's magic!", Toast.LENGTH_SHORT)
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
        Button btnSampleTwo = findViewById(R.id.btnSampleTwo);
        btnSampleTwo.setOnClickListener(view -> {
            sampleTwoCount++;
            TextView textViewSampleTwo = findViewById(R.id.tvSampleTwo);

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
        Button btnSampleThree = findViewById(R.id.btnSampleThree);
        btnSampleThree.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "welcome to the second activity", Toast.LENGTH_SHORT)
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
        FloatingActionButton fabSampleFour = findViewById(R.id.fabSampleFour);
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

        // Sample 7: image buttons
        /*
            1. drag image button onto activity
            2. choose starting image
            3. wire new image with ibtn.setImageResource
        */
        /*
            Notes:
                >if you want image to appear before click, src -> select image NOT srcCompat -> select image
        */

        ImageButton ibtnSampleSeven = findViewById(R.id.ibtnSampleSeven);
        ibtnSampleSeven.setOnClickListener( view -> {
            ibtnSampleSeven.setImageResource(R.drawable.ic_android_white_24dp);
        });

        // Sample 8: Resources by name
        /*
            1. create string with image file name
            2. get int resource id using file name
            3. pass resource id to function displaying resource
        */
        /*
            Notes:
                >resource ids are just integers so we can just pass these ints to get our resource
        */

        ImageButton ibtnSampleEight = findViewById(R.id.ibtnSampleEight);
        ibtnSampleEight.setOnClickListener( view -> {
            String fileName = "animal_crossing_leaf";
            int id = getResources().getIdentifier(fileName, "drawable", MainActivity.this.getPackageName());
            ImageView ivSampleEight = findViewById(R.id.ivSampleEight);
            ivSampleEight.setImageResource(id);
        });

        // Sample 9: List view
        /*
            1. array of options -> array adapter -> populate list view
            2. list views are a set of views (create separate views in layouts folder)
                a. new -> layout resource file
        */
        /*
            Notes:
                >listview is legacy but probably still good to know
                >list.setOnClickListener -> clicking the entire listview
                >list.setOnItemClickListener -> clicking an item on the list
        */

        // Create list of items
        String[] itemsSampleNine = {"Sample 9", "Blue", "Red", "Green", "White"};

        // Build adapter (context, layout file, items)
        ArrayAdapter<String> adapterSampleNine = new ArrayAdapter<>(
                this,
                R.layout.layout_sample_nine,
                itemsSampleNine);

        // Configure list view
        ListView listSampleNine = findViewById(R.id.lvSampleNine);
        listSampleNine.setAdapter(adapterSampleNine);

        // Configure listview item on click
        listSampleNine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // can be shortened to lambda
                TextView textView = (TextView) view;
                String message = "You clicked: " + position
                        + ", which is " + textView.getText().toString();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT)
                        .show();
            }
        });

        // Sample 10: Listview advanced
        /*
            1. make an array of objects to be displayed
            2. populate listview using array adapter
                a. for complex listviews, implement your own adapter
                    I. override constructor to let adapter know which layout to use
                    II. override getView to populate each space with layout of your choice
                        1. remember to check if view is null and create a view accordingly if null
        */
        /*
            Notes:
                >
        */

        // populate StringHolder list
        myStringHolderSampleTen.add(new StringHolder("Sample 10", "10", "10"));
        myStringHolderSampleTen.add(new StringHolder("boom", "bam", "bop"));

        // populate listview
        ArrayAdapter<StringHolder> adapterSampleTen = new myListAdapterSampleTen();
        ListView lvSampleTen = findViewById(R.id.lvSampleTen);
        lvSampleTen.setAdapter(adapterSampleTen);

        // Sample 11: recycler views (new listview!)
        /*
            1. make layout
            2. build adapter
            3. link recyclerView to Adapter
        */
        /*
            Notes:
                >pay attention to layout sizes -> wrap_content is best or set sizes
                >remember to populate ViewHolder with all items in layout including the layout
        */

        // populate StringHolder
        myStringHolderSampleEleven.add(new StringHolder("Sample 11", "11", "11"));
        myStringHolderSampleEleven.add(new StringHolder("hello", "world", "!!!"));

        // link recyclerview to adapter
        RecyclerView rvSampleEleven = findViewById(R.id.rvSampleEleven);
        RecyclerViewAdapter rvAdapter = new RecyclerViewAdapter(MainActivity.this, myStringHolderSampleEleven);
        rvSampleEleven.setAdapter(rvAdapter);

        // LinearLayoutManager allows setting of vertical/horizontal/etc recycler views
        rvSampleEleven.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        // Sample 12: actionbar / toolbar: add icon, add up button
        /*
            1. create menu layout
            2. override onCreateOptionsMenu -> inflate menu layout
            3. override onOptionItemSelected -> control what items do when clicked
            4. enable back button by accessing action bar (code on activity second)
                a. tell it where to go by using
                    I. manifest (go to parent)
                    II. code (if you don't want it to go to parent everytime, handle it here)
                        1. using this overrides what's written in manifest. (this has priority)
        */
        /*
            Notes:
                >import    import androidx.appcompat.widget.Toolbar;
                            import androidx.appcompat.app.AppCompatActivity;
                 instead of       android.widget.Toolbar
                 otherwise setSupportActionBar won't work
                >UpButton starts new parent activity and deletes all previous on stack instead of going up stack
        */

        // Sample 13: Passing data to activity -> naive object passing
        /*
            1. on second activity, set makeIntent function to accept primitive types
            2. intent.putExtra these primitives
                a. create a unique tag for each extra (package name.activity name - name)
            3. extract extra into local variable
        */
        /*
            Notes:
                >passing objects is more complicated -> search singleton pattern
                >naive object passing is basically just passing in values of object to next activity
                >refer to sample four for code on putting extras
        */

        // Sample 14: returning data from activity
        /*
            1. main activity -> start second activity with intent of getting result back
                a. extract request code into a constant
            2. second activity -> extract data from UI
            3. pass back data + result code to main activity
                a. set & extract a result key
            4. override callback from second activity in main to receive data -> onActivityResult
        */
        /*
            Notes:
                >startActivityForResult DEPRECIATED!!! -> use someActivityResultLauncher
        */

        ActivityResultLauncher<Intent> mainActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Toast.makeText(MainActivity.this, "do i even exist", Toast.LENGTH_SHORT).show();
                            Intent data = result.getData();

                            // do stuff with
                            String test1 = data.getStringExtra(SecondActivity.getResultMessageCodeSampleFourteen(data));
                            Log.d("back in main activity", "im back kids!");
                            Toast.makeText(MainActivity.this, test1, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        Button btnSampleFourteen = findViewById(R.id.btnSampleFourteen);
        btnSampleFourteen.setOnClickListener(view -> {
            Intent intent = SecondActivity.makeIntent(MainActivity.this);
            startActivity(intent);
        });

        // Sample
        /*
            1.
        */
        /*
            Notes:
                >
        */

    }






    // sample 10 code
    private class myListAdapterSampleTen extends ArrayAdapter<StringHolder> {
        public myListAdapterSampleTen() {
            super(MainActivity.this, R.layout.layout_sample_ten, myStringHolderSampleTen);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.layout_sample_ten, parent, false);
            }

            // get current StringHolder
            StringHolder currentStringHolder = myStringHolderSampleTen.get(position);

            // fill view
            TextView tvTop = itemView.findViewById(R.id.tvTopSampleTen);
            tvTop.setText(currentStringHolder.getFirst());
            TextView tvMiddle = itemView.findViewById(R.id.tvMidSampleTen);
            tvMiddle.setText(currentStringHolder.getSecond());
            TextView tvBot = itemView.findViewById(R.id.tvBotSampleTen);
            tvBot.setText(currentStringHolder.getThird());

            return itemView;
        }
    }

    // sample 12 code
    // inflate menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    // write menu functions
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_run_fast_sample_twelve:
                Toast.makeText(this, "Now running!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings_awesome_stuff:
                Toast.makeText(this, "Awesome!", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}