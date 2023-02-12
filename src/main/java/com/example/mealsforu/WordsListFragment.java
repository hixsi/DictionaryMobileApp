package com.example.mealsforu;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class WordsListFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    MyDatabaseHelper myDatabaseHelper;
    ArrayList<String> wordIdList, wordList, meaningList;
    CustomAdapter customAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_words_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        myDatabaseHelper = new MyDatabaseHelper(getActivity());
        wordIdList = new ArrayList<>();
        wordList = new ArrayList<>();
        meaningList = new ArrayList<>();
        storeDataFromDB();

        customAdapter = new CustomAdapter(requireActivity(),wordList,meaningList);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }

    void storeDataFromDB(){
        Cursor cursor = myDatabaseHelper.readAllData();
        if(cursor.getCount() == 0){}
        else{
            while(cursor.moveToNext()){
              //  wordIdList.add(cursor.getString(0));
                wordList.add(cursor.getString(1));
                meaningList.add(cursor.getString(2));
            }
        }
    }
}