package sk.mpage.androidsample.sensorlog;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {


    private TextView sensorTextView;

    private SensorManager mSensorManager;
    private  Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_main);

        sensorTextView = (TextView) findViewById(R.id.sensor_text);
        int type = this.getIntent()!=null ? this.getIntent().getIntExtra("type",-1) : -1;

        sensor = null;
        if (type>=0){
            mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
            sensor = mSensorManager.getDefaultSensor(type);
        }

        TextView sensorInfo = (TextView) findViewById(R.id.sensor_info);
        sensorInfo.setText(sensor.getName()+" - "+sensor.getPower()+"mA\n");

    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String text = "";

        for (int i=0; i<event.values.length; i++){
            text+="["+i+"] "+event.values[i]+" \n";
        }
        sensorTextView.setText(text);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
