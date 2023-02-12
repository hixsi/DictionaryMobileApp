package com.example.mealsforu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList  wordList, meaningList;
    CustomAdapter(Context context, ArrayList wordList, ArrayList meaningList){
        this.context= context;
        this.meaningList = meaningList;
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
       View view =  layoutInflater.inflate(R.layout.word_row,parent,false);
       return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.word.setText(String.valueOf(wordList.get(position)));
        holder.meaning.setText(String.valueOf(meaningList.get(position)));
        System.out.printf("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + meaningList);




    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  word,meaning;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.word_tv);
            meaning = itemView.findViewById(R.id.meaning_tv);
        }
    }
}
