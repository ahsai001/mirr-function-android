package com.ahsailabs.mirrfunction;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        List<Double> cashFlows = new ArrayList<>();
        cashFlows.add(-1000.0);
        cashFlows.add(-5000.0);
        cashFlows.add(12000.0);
        cashFlows.add(8000.0);
        double mirrValue = mirr(cashFlows,10,12);
        Log.d("mirr", "mirr : "+mirrValue);

    }

    private double mirr(List<Double> cashFlows, double financeRate, double reinvestRate){
        double fv = 0;
        double pv = 0;
        int size = cashFlows.size();
        for (int i=0; i<size; i++){
            double value = cashFlows.get(i);
            if(value >= 0){
                //FV
                double result = value*Math.pow(1+(reinvestRate/100),size-1-i);
                Log.d("mirr", "fv-"+i+" : "+result);
                fv += result;
            } else {
                //PV
                double result = value/Math.pow(1+(financeRate/100),i);
                Log.d("mirr", "pv-"+i+" : "+result);
                pv += result;
            }
        }
        double mirrValue = (Math.pow(fv/(-1*pv),(double)1/(size-1))-1)*100;

        return mirrValue;
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
