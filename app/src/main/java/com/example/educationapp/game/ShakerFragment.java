package com.example.educationapp.game;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.educationapp.R;

import org.w3c.dom.Text;

import java.util.Objects;

import static android.view.KeyCharacterMap.ALPHA;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShakerFragment extends Fragment implements SensorEventListener {
TextView fillScore;
SensorManager sensorManager = null;
    Sensor shakeSensor;
    Float[] gravity;
    int count;
    public ShakerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sensorManager = (SensorManager) Objects.requireNonNull(getActivity()).getSystemService(Context.SENSOR_SERVICE);
        assert sensorManager != null;
        shakeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        View view = inflater.inflate(R.layout.fragment_shaker,container,false);
        fillScore = view.findViewById(R.id.fillTotal);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shaker, container, false);
    }
    public void register(){
        sensorManager.registerListener(this,shakeSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        float maxAccel = calcAcceleration(event);
        Log.d("SwA","Max Acc ["+maxAccel+"]");
        if (maxAccel >= 12){
            count++;
            if (count == 5){
                Toast toast = Toast.makeText(getActivity(),"Keep Shaking!",Toast.LENGTH_SHORT);
                toast.show();
            }else if (count == 20){
                endGame();
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private float calcAcceleration(SensorEvent event){
        gravity[0] = calcGravity(event.values[0], 0);
        gravity[1] = calcGravity(event.values[1], 1);
        gravity[2] = calcGravity(event.values[2], 2);

        float accelX = event.values[0] - gravity[0];
        float accelY = event.values[1] - gravity[1];
        float accelZ = event.values[2] - gravity[2];

        float maxi = Math.max(accelX,accelY);
        return Math.max(maxi,accelZ);
    }
    private float calcGravity(float currValue, int index){
        return ALPHA * gravity[index] + (1 - ALPHA) * currValue;
    }
    public void  endGame(){

    }
}
