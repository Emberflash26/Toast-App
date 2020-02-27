package com.asurivarun.toastapp;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import android.os.Bundle;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    TextView displayText;
    TextView display2Text;
    TextView display3Text;
    TextView display4Text;
    SeekBar scrollBar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    HashMap<String, Integer> hash;
    HashMap<String, TextView> hash2;
    Integer[] counts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayText = findViewById(R.id.textBox);
        display2Text = findViewById(R.id.text2Box);
        display3Text = findViewById(R.id.text3Box);
        display4Text = findViewById(R.id.text4Box);
        scrollBar = findViewById(R.id.seekBar);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
        hash = new HashMap<>();
        hash.put("textBox", 0);
        hash.put("text2Box", 1);
        hash.put("text3Box", 2);
        hash.put("text4Box", 3);
        hash2 = new HashMap<>();
        hash2.put("textBox", displayText);
        hash2.put("text2Box", display2Text);
        hash2.put("text3Box", display3Text);
        hash2.put("text4Box", display4Text);
        counts = new Integer[4];
        counts[0] = sharedPreferences.getInt("textBox", 0);
        counts[1] = sharedPreferences.getInt("text2Box", 0);
        counts[2] = sharedPreferences.getInt("text3Box", 0);
        counts[3] = sharedPreferences.getInt("text4Box", 0);
        displayText.setText(counts[0]+"");
        display2Text.setText(counts[1]+"");
        display3Text.setText(counts[2]+"");
        display4Text.setText(counts[3]+"");
        displayText.setTextSize(sharedPreferences.getInt("seek", 100));
        display2Text.setTextSize(sharedPreferences.getInt("seek", 100));
        display3Text.setTextSize(sharedPreferences.getInt("seek", 100));
        display4Text.setTextSize(sharedPreferences.getInt("seek", 100));
        scrollBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                displayText.setTextSize(progress);
                display2Text.setTextSize(progress);
                display3Text.setTextSize(progress);
                display4Text.setTextSize(progress);
                editor.putInt("seek", progress);
                editor.commit();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    public void clickResponse(View view) {
        String name = view.getResources().getResourceName(view.getId()).substring(27);
        TextView temp = hash2.get(name);
        counts[hash.get(name)]++;
        int n = counts[hash.get(name)];
        temp.setText(n+"");
        editor.putInt(name, n);
        Log.i("currVals", counts[0] + " " + counts[1] + " " + counts[2] + " " + counts[3]);
        editor.commit();
    }
}
