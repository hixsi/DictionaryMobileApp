package com.example.mealsforu;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DictionaryFragment extends Fragment {
    TextView word,meaning,example,origin;
    View view;
    EditText searchEditText;
    ImageButton searchButton;
    FloatingActionButton addToDBBtn;

    RequestQueue requestQueue;




    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_dictionary, container, false);

        searchEditText = (EditText) view.findViewById(R.id.edit_text);
        searchButton = (ImageButton) view.findViewById(R.id.button);


        word = view.findViewById(R.id.word_id);
        meaning = view.findViewById(R.id.meaning_id);
        example = view.findViewById(R.id.example_id);
        origin = view.findViewById(R.id.origin_id);
        addToDBBtn = view.findViewById(R.id.add_button);


        requestQueue = Volley.newRequestQueue(this.getActivity());



        addToDBBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(getActivity());
                myDatabaseHelper.addWord(word.getText().toString().trim(),meaning.getText().toString().trim());
            }
        });



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchWord = searchEditText.getText().toString().trim();
                searchingForWord(searchWord);
            }
        });

        return view;
    }





    private void searchingForWord(String word1){

        String URL = "https://api.dictionaryapi.dev/api/v2/entries/en/ " + word1;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    String txt_box_word = jsonObject.getString("word");
                    String txt_box_origin = jsonObject.getString("origin");

                    word.setText(txt_box_word);
                    origin.setText(txt_box_origin);

                    JSONArray  jsonArrayMeanings = jsonObject.getJSONArray("meanings");
                    JSONObject jsonObject1 = jsonArrayMeanings.getJSONObject(0);
                    JSONArray  jsonArrayDefinitions = jsonObject1.getJSONArray("definitions");
                    JSONObject jsonObject2 = jsonArrayDefinitions.getJSONObject(0);
                    String txt_box_meaning = jsonObject2.getString("definition");
                    String txt_box_example = jsonObject2.getString("example");

                    meaning.setText(txt_box_meaning);
                    example.setText(txt_box_example);



                }

                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonArrayRequest);
    }


}


//    private void goBackToFilesActivity(){
//        Intent intent = new Intent(getActivity().getBaseContext(),MainActivity.class);
//        getActivity().startActivity(intent);
//    }