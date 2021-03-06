package com.example.educationapp.game;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.educationapp.GameActivity;
import com.example.educationapp.R;

import java.util.Locale;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */

public class ShakerFragment extends Fragment implements SensorEventListener {
    private final static int SHAKERTHRESHOLD = 10;
    private int count;
    private TextView fillScore;
    private SensorManager sensorManager;

    public ShakerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) Objects.requireNonNull(getContext()).getSystemService(Context.SENSOR_SERVICE);
        assert sensorManager != null;
        Sensor shakeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, shakeSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shaker, container, false);

        fillScore = view.findViewById(R.id.fillTotal);

        return view;
    }

    //OnSensorChanged takes the sensor and checks if the Accelerometer is available, then takes the
    // x,y,z movement and stores then then calculates the acceleration, and compares it against a
    //constant threshold.
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            double accel = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));

            if (accel > SHAKERTHRESHOLD) {
                count++;
                fillScore.setText(String.format(Locale.getDefault(), "%d", count));
                if (count == 5) {
                    Toast toast = Toast.makeText(getActivity(), "Keep Shaking!", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (count >= 20) {
                    count = 0;
                    GameActivity.gameActivity.endGame();
                }

            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
