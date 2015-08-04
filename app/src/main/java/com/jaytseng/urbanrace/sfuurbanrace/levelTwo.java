package com.jaytseng.urbanrace.sfuurbanrace;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class levelTwo extends ActionBarActivity implements View.OnClickListener, SensorEventListener{
    private SensorManager mySensorManager=null;
    Sensor myCompass = null;
    float[] sensorVal = new float[3];
    TextView puzzleHeading, puzzleSubHeading, puzzleBody;
    TextView directionOne, directionTwo, directionThree;
    EditText puzzleSolution;
    Button saveOne, saveTwo, saveThree;
    Button hintButton, solveButton;

    boolean button1Clk, button1state, button2Clk, button2state, button3Clk, button3state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two);

        //Creating Puzzle Headers
        puzzleHeading = (TextView)findViewById(R.id.puzzleHeading);
        puzzleSubHeading = (TextView)findViewById(R.id.puzzleSubheading);
        puzzleBody = (TextView)findViewById(R.id.puzzleBody);

        //Creating Solution
        directionOne = (TextView)findViewById(R.id.directionOne);
        directionTwo = (TextView)findViewById(R.id.directionTwo);
        directionThree = (TextView)findViewById(R.id.directionThree);

        saveOne = (Button)findViewById(R.id.saveOne);
        saveTwo = (Button)findViewById(R.id.saveTwo);
        saveThree= (Button)findViewById(R.id.saveThree);
        saveOne.setOnClickListener(this);
        saveTwo.setOnClickListener(this);
        saveThree.setOnClickListener(this);
        saveTwo.setEnabled(false);
        saveThree.setEnabled(false);

        //Attach Sensors
        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        myCompass = mySensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);


        //Solve buttons
        hintButton = (Button)findViewById(R.id.hintButton);
        solveButton = (Button) findViewById(R.id.solveButton);
        hintButton.setOnClickListener(this);
        solveButton.setOnClickListener(this);
        solveButton.setEnabled(false);

        puzzleHeading.setText("Find the hidden pass code.");
        puzzleSubHeading.setText("Stand under the great wooden finger. Search for the following locations.\n1. Safety\n2. Trove of Knowledge\n3. Source of Energy");
        puzzleBody.setText("Point your at phone at the three locations, one at a time and hit save to generate the passcode for the computer.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mySensorManager.registerListener(this, myCompass, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d("SensorVals", "SensorRegis");
    }

    @Override
    protected void onPause() {
        mySensorManager.unregisterListener(this);
        Log.d("SensorVals", "unResgis");
        super.onPause();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_four, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveOne:
                button1Clk = true;
                button1state = true;
                saveTwo.setEnabled(true);
                break;
            case R.id.saveTwo:
                button2Clk = true;
                button2state = true;
                saveThree.setEnabled(true);
                break;
            case R.id.saveThree:
                button3Clk = true;
                button3state = true;
                solveButton.setEnabled(true);
                break;
            case R.id.hintButton:
                Toast.makeText(this, "Look around you.", Toast.LENGTH_LONG).show();
                break;
            case R.id.solveButton:
                float enteredSoln1 = Float.parseFloat(directionOne.getText().toString());
                float enteredSoln2 = Float.parseFloat(directionTwo.getText().toString());
                float enteredSoln3 = Float.parseFloat(directionThree.getText().toString());

                float solution1LB = 0.01f;
                float solution1UB = 0.2f;
                if (enteredSoln1>=solution1LB ||enteredSoln1<=solution1UB){
                    Toast.makeText(this, "Correct", Toast.LENGTH_LONG).show();
                    Intent j = new Intent(this, RaceListHome.class);
                    j.putExtra("Result", "correct");
                    startActivity(j);
                } else {
                    Toast.makeText(this, "Incorrect", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        int type = event.sensor.getType();
        Log.d("SensorVals", ""+type);

        if (type == Sensor.TYPE_ROTATION_VECTOR){
            sensorVal = event.values.clone();
        }
        Log.d("SensorVals", "" + sensorVal[0] + " " + sensorVal[1] + " " + sensorVal[2]);
        //State of 1
        if (button1state && button1Clk) {
            directionOne.setText("" + sensorVal[0]);
            button1state = false;
        } else if(button1Clk && button1state == false) {

        } else {
            directionOne.setText("" + sensorVal[0]);
        }
        //State of 2
        if (button2state && button2Clk) {
            directionTwo.setText("" + sensorVal[0]);
            button2state = false;
        } else if(button2Clk && button2state == false) {

        } else {
            directionTwo.setText("" + sensorVal[0]);
        }
        //State of 3
        if (button3state && button3Clk) {
            directionThree.setText("" + sensorVal[0]);
            button3state = false;
        } else if(button3Clk && button3state == false) {

        } else {
            directionThree.setText("" + sensorVal[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
