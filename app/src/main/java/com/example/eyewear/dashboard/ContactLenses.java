package com.example.eyewear.dashboard;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eyewear.R;
import com.example.eyewear.recycler.RecyclerViewer;

public class ContactLenses extends AppCompatActivity {

    RecyclerView recycler;
    RecyclerViewer recyclerViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_lenses);

        recycler = findViewById(R.id.ContactLensesRecycler);
        recyclerViewer= new RecyclerViewer(this,recycler,"ContactLens");
        recyclerViewer.recyclerViewer();
    }
}