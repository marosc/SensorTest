package sk.mpage.androidsample.sensorlog;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private SensorManager mSensorManager;


    private ListView listView;
    private ArrayAdapter<String> mSensorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        final List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        String text = "";
        List<String> sensorList = new ArrayList<>();
        for (Sensor s : deviceSensors){

            text+=s.getName()+" -- "+ s.getPower() +"mA\n";
            sensorList.add(s.getType()+". "+s.getName()+" -- "+ s.getPower() +"mA");
        }


        mSensorAdapter =
                new ArrayAdapter<String>(
                        this, // The current context (this activity)
                        android.R.layout.simple_list_item_1, // The name of the layout ID.
                        android.R.id.text1, // The ID of the textview to populate.
                        sensorList);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.sensorlist);
        listView.setAdapter(mSensorAdapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                Intent intent = new Intent(MainActivity.this,SensorActivity.class);
                intent.putExtra("type",deviceSensors.get(position).getType());
                startActivity(intent);
            }

        });

    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
