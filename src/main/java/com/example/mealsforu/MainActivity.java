package com.example.mealsforu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button  filesPdfBtn,dictionaryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this,MyService.class );

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            startForegroundService(intent);
        }
        else{
            startService(intent);
        }

        dictionaryBtn = findViewById(R.id.btnDictionary);
        filesPdfBtn = findViewById(R.id.btnWordList);
        Fragment fragment1 = new DictionaryFragment();
        Fragment fragment2 = new WordsListFragment();


        dictionaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFragment(fragment1);

            }
        });


        filesPdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFragment(fragment2);
            }
        });
    }


    private void openFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}