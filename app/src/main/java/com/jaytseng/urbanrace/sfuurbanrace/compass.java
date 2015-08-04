package com.jaytseng.urbanrace.sfuurbanrace;

import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class compass extends ActionBarActivity implements SensorEventListener, LocationListener{
    private SensorManager mySensorManager = null;
    private LocationManager myLocationManager = null;

    float[] angle = new float[3];

   // ImageView imageView;

    Sensor myCompass = null;

    TextView currHeading;
    TextView currDirection, currLocation;
    TextView currCoordinates;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        myCompass = mySensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);

        currHeading = (TextView)findViewById(R.id.currHeading);
        currDirection = (TextView) findViewById(R.id.currDirection);
        currLocation = (TextView) findViewById(R.id.currLocation);

        currCoordinates = (TextView) findViewById(R.id.currCoordinates);

//        imageView = (ImageView) findViewById(R.id.comapssView);
//        imageView.setImageResource(R.drawable.compass_960x960);
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
        getMenuInflater().inflate(R.menu.menu_compass, menu);
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
    public void onSensorChanged(SensorEvent event) {
        int type = event.sensor.getType();

        if (type == Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR){
            angle = event.values;
            currHeading.setText(""+angle[0]);
            currDirection.setText(""+angle[1]);
            currLocation.setText(""+angle[2]);
        }
//        Matrix matrix = new Matrix();
//        imageView.setScaleType(ImageView.ScaleType.MATRIX);   //required
//        matrix.postRotate(angle[0]);
//        imageView.setImageMatrix(matrix);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
