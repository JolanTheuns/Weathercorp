package com.example.wethercorp1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity{

    Spinner spinner;
    BodyDisplayGraph fragmentOne;
    BodyDisplaySingleGraph fragmentTwo;
    AboutFrame fragmentThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.headerSpinner);

        fragmentOne = new BodyDisplayGraph();
        fragmentTwo = new BodyDisplaySingleGraph();
        fragmentThree = new AboutFrame();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.headerSelection));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(position){
                    case 0:
                        setFragment(fragmentOne);
                        break;
                    case 1:
                        setFragment(fragmentTwo);
                        break;
                    case 2:
                        setFragment(fragmentThree);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void setFragment (Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentPlace,fragment);
        fragmentTransaction.commit();
    }
}
