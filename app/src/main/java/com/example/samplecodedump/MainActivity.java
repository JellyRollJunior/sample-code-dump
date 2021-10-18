package com.example.samplecodedump;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SampleCodeDump";     // TAG used to show where log error messages originate from
    private static final String APP_PREFERENCES = "App Preferences";    // shared preferences
    public static final String NUM_REJECTED_APPLICATIONS = "Num Rejected Applications";
    private int sampleTwoCount = -1;                        // Can't declare in the scope of onCreate or else problems arise
    private final ArrayList<StringHolder> myStringHolderSampleTen = new ArrayList<>();
    private final ArrayList<StringHolder> myStringHolderSampleEleven = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     // chooses which activity layout is shown on create

        // SAMPLE 1: Create/set Button with Toast
        sampleOneBasicButtonCreation();

        // SAMPLE 2: TextViews and Strings.xml, Wire button with TextView
        sampleTwoBasicButtonWiring();

        // Sample 3: Switching activities, killing activities
        sampleThreeButtonActivitySwitching();

        // Sample 4: Create/wire floating action button, material design layout, String Extras
        sampleFourFloatingActionButton();

        // Sample 5: Constraints
            /*
                1. drag an object into activity
                2. hover over object
                3. drag dots to other objects or activity to apply constraints
                4. use Guidelines -> can choose from left, right, or percentage ~!¡¡of screen
                    a. LEFT: layout_constraintGuide_begin = "250dp"
                    b. RIGHT: layout_constraintGuide_end = "250dp"
                    c. PERCENT: layout_constraintGuide_percent = "0.25"
                5. right click -> show baseline -> allow UI elements to be on same horizontal plane
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
        sampleSevenImageButton();

        // Sample 8: Resources by name
        sampleEightResourceByName();

        // Sample 9: List view
        sampleNineListViewLegacy();

        // Sample 10: Listview advanced -> custom layout -> override adapter class
        sampleTenListViewCustomLayout();

        // Sample 11: recycler views (new listview!)
        sampleElevenRecyclerView();

        // Sample 12: actionbar / toolbar: add icon, add up button
            /*
                1. create menu layout (create menu directory if not present -> new menu resource file)
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
        // override code below

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
        sampleFourteenReturningDataFromActivity();

        // Sample 15: dynamic buttons
            /*
                1. Create table layout (delete the rows)
                2. Populate buttons onCreate
            */
            /*
                Notes:
                    >stretchColumn * makes all columns equal size
            */

        // Sample 16: Maps (getting api key, getting map running)
        sampleSixteenMapsBasics();

        // Sample 17: Map markers + camera movement
            /*
                1. create location
                2. create marker for location
                3. move camera to location (optional: control zoom)
                    a. zoom goes from 0 to 21 (zero zoom to most zoom)
            */
            /*
                Notes:
                    >manipulate map size by putting the fragment in a layout
                    >can add buttons on top of the map
                    >moveCamera vs animateCamera -> move automatically moves camera, animate pans camera
            */

        // Sample 18: Internationalization
            /*
                1. create resource folder values-(country code)
                2. add in translated strings.xml
            */
            /*
                Notes:
                    >
            */

        // Sample 19: Radio buttons from resource file
        sampleNineteenRadioButtons();

        // Sample 20: Saving data between app executions with shared preferences
            /*
                1. get shared preferences
                2. feed in string tag + values to shared preferences editor
                3. create static method to access the values
            */
            /*
                Notes:
                    >if creating a settings activity
                        >create a refresh screen function and get value
                            >MainActivity.sampleTwentyGetNumRejectedApplications(this)
                        >override onResume so main activity responds to settings change immediately
            */

        // Sample 21: Alert dialogue
        sampleTwentyOneAlertDialog();

        // Sample 22: Pie graph - MPAndroid chart
        sampleTwentyTwoCharts();

        // Sample 23: Up button in-depth
            /*
                1. make sure activity extends appCompatActivity
                2. make sure to choose noActionBar theme so activity doesn't use native action bar
                3. add toolbar / action bar / app bar
                    a. (optional) inflate own action bar layout (onCreateOptionsMenu)
                    b. program functionality (onOptionsItemSelected)
                4. set support action bar
                5. access as action bar to enable up button
            */
            /*
                Notes:
                    >
            */

        // Sample 24: JUnit4 introduction
            /*
                1. hover over class to test
                2. command + n to generate a test class
                3. run tests using run section on bottom bar
            */
            /*
                Notes:
                    >get familiar with all the asserts
                    >life cycle of test objects
                        >for every test, the test runner re-instantiates the class object
                            >every object deleted after test and then called again for next test
            */

        // Sample 25: JUnit4 continued
            /*
                1.
            */
            /*
                Notes:
                    >Throw exceptions
                    >Set timeout, global timeouts
                    >@Before: before every test, do this setup code (redone for every test)
                    >@BeforeClass: before the tests run, do this setup (persistent)
                    >@AfterClass: do after tests complete (file closing, etc.)
                    >@Ignore: ignore test
                    >run with coverage: see code coverage of tests - green bar = covered by testing
                    >can set deltas (margin for errors) in asserts (useful when working with floats)
                    >fail() command fails test
            */

        // Sample 26: JUnit5
            /*
                1. create JUnit5 test
                2. file -> project structure -> dependencies -> junit-jupiter -> select version
            */
            /*
                Notes:
                    >put tests in (test), (androidTest) is for android UI tests
                    >disabled("message") : functionally the same as ignore but with different label (i think)
                    >Assert types: true, assertFalse, assertNotNull, assertThrows
                        >assertThrows(yourException.class, () -> myObject.methodThatThrowsException)
                            > () -> is a lamda function
                    >JUnit5 New Additions
                        >assertAll
                            >assertAll("test header",
                                    () -> assert( ... ),
                                    () -> assert( ... ),
                                    ...
                                    () -> assert( ... )
                             );
                            >JUnit4: in a test with >1 asserts, fail on first error and end test
                            >JUnit5 assertAll: runs every test in assert all even if the test fails
                                               to give more context to the failure
                        >timing
                            >assertTimeoutPreemptively(
                                     Duration.ofSeconds(myDuration,
                                     () -> assert( ... )
                             );
                                >kill test after specified time has passed
                            >assertTimeout()
                                >test fails if specified time has elapsed
            */

        // Sample 27: Singleton
            /*
                1. create private static instance of class
                2. create public static synchronized method to return and create this instance
            */
            /*
                Notes:
                    >code in StringHolder
                    >synchronized keyword only allows one call at any time so >1 instance never created
                        >else multiple threads may create multiple instances
            */

        // Sample 28: Linear Layouts + Color class
        sampleTwentyEightLinearLayoutColor();


        /*
        video notes TODO
                    >change app icon in manifest file android:icon
                    >fb -> findViewByID
                    >listview adapter -> can pass in preset layouts (ex: android.R.layout.simple_list_item_1)
                    >spinner -> create spinner -> create spinner adapter (can pass preset layouts too
                        >(ex: android.R.layout.simple_spinner_dropdown_item)
                        >to do something for each item -> override setOnItemSelectedListener
                        >spinner.getSelectedItem().toString() -> can use this if set values from XML instead of from java
                            >android:entries="arrayInStringsXMLOrOther" (useful to do this way for static entries)
                    >styles.xml -> define theme of app
                    >can create layout file for landscape and portrait for each activity
                        >orientation for preview button (rotate screen icon top left) -> create landscape variation
                        >can also create night / various other modes
                    >trademark layout
                    >material design.io -> using material design app themes
                    >can also change style of buttons + other components by referencing material design
                    >android:backgroundTint vs app:backgroundTint (border on components) vs app:rippleColor (color change once clicking on a component)
                    >Snackbar.make(parent, "text shown in snackbar", Snackbar.timetoshow)
                        >.setAction("action text", new onClickListener (or whatever you want))
                        >.setActionTextColor(getResources().getColor(R.color.whateverColor))
                        >.setTextColor(Color.YELLOW)
                        >.show()
                    >card view (material.io)
                        >card corner radius
                        >car elevation
                    >RV (2) part 1
                        >notifyDataSetChanged()
                    >card view (native androidx dependency)
                    >glide external library : load images from the internet
                        >Glide.with(context)
                        >    .asBitmap()
                        >    .load(image URL ending with jpg or png)
                        >    .into(image view / image holder)
                    >requesting permissions
                        >may not be able to see permissions because once you update manifest file, must
                        >uninstall and reinstall app for everything to work properly
                    >external fonts
                        >new resource directory -> choose font type directory
                        >android:fontFamily="reference your font"
                        >downloadable font (use online font (not good)) / add font to project
                        >create you own -> right click font -> create new font resource file
        */
    }

    private void sampleTwentyEightLinearLayoutColor() {
        /*
            1. set layout weights for elements to take percentage of space desired
        */
        /*
            Notes:
                >Color class provides preset colors if youre too lazy to choose your own
        */
        TextView tvLeft = findViewById(R.id.tvLeftSample28);
        tvLeft.setBackgroundColor(Color.GREEN);
        TextView tvRight = findViewById(R.id.tvRightSample28);
        tvRight.setBackgroundColor(Color.RED);
    }

    private void sampleTwentyTwoCharts() {
        /*
            1. take care of dependencies
            2. create pie chart in xml
            3. populate list of pie entries
            4. shove pie entries into pie data set
            5. create chart using data
        */
        /*
            Notes:
                >PUT MAVEN REPO IN SETTINGS.GRADLE
                >PUT IMPLEMENTATION IN BUILD.GRADLE
        */

        Button button = findViewById(R.id.btnSampleTwentyTwo);
        button.setOnClickListener(view -> {
            Intent intent = ChartActivity.makeIntent(MainActivity.this);
            startActivity(intent);
        });
    }

    private void sampleTwentyOneAlertDialog() {
        /*
            1. create alert dialogue message layout (new layout resource file)
            2. create message fragment (class)
                a. extend AppCompatDialogFragment
                b. override onCreateDialog
                    I. create the view to show
                    II. create button listener
                    II. build alert dialog
            3. display alert dialog in chosen activity
        */
        /*
            Notes:
                >
        */
        Button btnSampleTwentyOne = findViewById(R.id.btnSampleTwentyOne);

        // start alert dialog on button click
        btnSampleTwentyOne.setOnClickListener(view -> {
            FragmentManager manager = getSupportFragmentManager();
            MessageFragment dialog = new MessageFragment();
            dialog.show(manager, "MessageDialogue");
        });
    }

    public static int sampleTwentyGetNumRejectedApplications(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);

        int defaultRejectedApplications = context.getResources().getInteger(R.integer.default_applications_rejected);
        return preferences.getInt(NUM_REJECTED_APPLICATIONS, defaultRejectedApplications);
    }


    private void sampleTwentySharedPreferences(int numRejectedApplications) {
        SharedPreferences preferences = this.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(NUM_REJECTED_APPLICATIONS, numRejectedApplications);
        editor.apply();
    }

    private void sampleNineteenRadioButtons() {
        /*
            1. put values in resource file
            2. create radio group
            3. populate radio group
            4. get id of currently selected button using group
        */
        /*
            Notes:
                >strings.xml formatting
                    > %1 : use first arg given to getString
                    > $d : this value is an integer
        */
        RadioGroup group = findViewById(R.id.sampleNineteenRadioGroup);
        int[] numRejected = getResources().getIntArray(R.array.num_applications_rejected);

        // populate radio group with buttons
        for (int numRejectedApplications : numRejected) {
            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.sample_nineteen_applications_rejected, numRejectedApplications));

            // on radio button click
            button.setOnClickListener(view -> {
                Toast.makeText(this, getString(R.string.sample_nineteen_applications_rejected,
                        numRejectedApplications), Toast.LENGTH_SHORT).show();

                // SAMPLE TWENTY - SHARED PREFERENCES
                sampleTwentySharedPreferences(numRejectedApplications);
            });

            group.addView(button);

            // SAMPLE TWENTY - save radio button click state
            if (numRejectedApplications == sampleTwentyGetNumRejectedApplications(this)) {
                button.setChecked(true);
            }
        }

        // make button which confirms checked result
        Button btnSampleEighteen = findViewById(R.id.btnSampleNineteen);
        btnSampleEighteen.setOnClickListener(view -> {
            int idOfSelected = group.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(idOfSelected);
            if (radioButton != null) {
                String message = radioButton.getText().toString();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sampleSixteenMapsBasics() {
        /*
            1. java folder -> new -> google -> maps activity
            2. res -> values -> google_maps_api.xml -> enter api key
        */
        /*
            Notes:
                >make sure emulator has google play enabled in target (supports google play)
                >check if you have play SDK downloaded
                    >tools -> sdk manager -> show package details -> check if has google play support
                >make sure maps api enabled in google API key settings (maps sdk for android)
        */

        Button btnSampleFifteen = findViewById(R.id.btnSampleFifteen);
        btnSampleFifteen.setOnClickListener(view -> {
            Intent intent = MapsActivity.makeIntent(MainActivity.this);
            startActivity(intent);
        });
    }

    private void sampleFourteenReturningDataFromActivity() {
        /*
            1. main activity -> create an ActivityResultLaunch for destination activity
                a. can lambda some operations -> i chose to leave them in for learning purposes
            2. pass data back from second activity
                a. create a static function in destination activity to get string extra
            3. code what is to be done with data in ActivityResultLauncher
        */
        /*
            Notes:
                >startActivityForResult DEPRECIATED!!! -> use ActivityResultLauncher
                    >usually you'd implement outside method or in own file
        */

        ActivityResultLauncher<Intent> secondActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();

                            // do stuff with data
                            if (data != null) {
                                String secondActivityMessage = SecondActivity.getResultMessageCodeSampleFourteen(data);
                                Toast.makeText(MainActivity.this, "from main: " + secondActivityMessage, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );

        Button btnSampleFourteen = findViewById(R.id.btnSampleFourteen);
        btnSampleFourteen.setOnClickListener(view -> {
            Intent intent = SecondActivity.makeIntent(MainActivity.this);
            secondActivityResultLauncher.launch(intent);
        });
    }

    // sample 12 code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate action bar
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sampleElevenRecyclerView() {
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
        //      LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false)
        // if passing linear -> telling android to display out items in a linear fashion
        // useful: GridLayoutManager(this, numCols,
        rvSampleEleven.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void sampleTenListViewCustomLayout() {
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
                >usually you'd implement adapter in new file or outside onCreate
        */

        // private class
        class myListAdapterSampleTen extends ArrayAdapter<StringHolder> {
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

        // populate StringHolder list
        myStringHolderSampleTen.add(new StringHolder("Sample 10", "10", "10"));
        myStringHolderSampleTen.add(new StringHolder("boom", "bam", "bop"));

        // populate listview
        ArrayAdapter<StringHolder> adapterSampleTen = new myListAdapterSampleTen();
        ListView lvSampleTen = findViewById(R.id.lvSampleTen);
        lvSampleTen.setAdapter(adapterSampleTen);
    }

    private void sampleNineListViewLegacy() {
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
    }

    private void sampleEightResourceByName() {
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
    }

    private void sampleSevenImageButton() {
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
    }

    private void sampleFourFloatingActionButton() {
        /*
            1. import images: right - click -> new -> import vector/image assets
        */
        /*
            Notes:
                >use app:tint=:"@null" to make fab icon the correct color
                >extras can be accessed by intents
                >floating action button stays in place even if screen is scrolled!
        */
        FloatingActionButton fabSampleFour = findViewById(R.id.fabSampleFour);
        fabSampleFour.setOnClickListener( view -> {
            Intent intent = SecondActivity.makeIntentSampleFour(MainActivity.this, "hello world!");
            startActivity(intent);
        });
    }

    private void sampleThreeButtonActivitySwitching() {
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
//            Intent intent = new Intent(MainActivity.this, SampleThreeActivity.class);
//            startActivity(intent);

            // better method
            Intent intent = SecondActivity.makeIntent(MainActivity.this);
            startActivity(intent);
        });
    }

    private void sampleTwoBasicButtonWiring() {
        /*
            1. make a TextView
            2. Enter attributes + text
            3. In code view, option + enter to extract string to resource folder
                a. benefit: can just swap string resource files for different app language versions
            4. wire to OnClickListener
            5. set sample text on tv -> right click on tv -> set sample data
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
    }

    private void sampleOneBasicButtonCreation() {
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
    }

}