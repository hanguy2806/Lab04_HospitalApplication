package com.example.lab04_hospitalapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab04_hospitalapplication.Models.Test;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {
    private List<Test> tests = new ArrayList<>();

    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_item, parent, false);
        return new TestHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position) {
        Test currrentTest = tests.get(position);
        holder.textViewBPH.setText(String.valueOf(currrentTest.getBPH()));
        holder.textViewBPL.setText(String.valueOf(currrentTest.getBPL()));
        holder.textViewTemperature.setText(String.valueOf(currrentTest.getTemperatre()));
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }
    public void setTests(List<Test> tests){
        this.tests=tests;
        notifyDataSetChanged();
    }

    class TestHolder extends RecyclerView.ViewHolder {
        private TextView textViewBPH;
        private TextView textViewBPL;
        private TextView textViewTemperature;

        public TestHolder(View itemView) {
            super(itemView);
            textViewBPH = itemView.findViewById(R.id.text_view_test_bph);
            textViewBPL = itemView.findViewById(R.id.text_view_test_bpl);
            textViewTemperature = itemView.findViewById(R.id.text_view_test_temperature);
        }
    }
}
