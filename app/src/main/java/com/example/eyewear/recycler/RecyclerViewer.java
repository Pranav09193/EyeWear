package com.example.eyewear.recycler;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eyewear.adapters.FrameAdapter;
import com.example.eyewear.model.FrameData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewer {

    RecyclerView recycler;
    List<FrameData> list;
    FrameAdapter adapter;
    FirebaseFirestore db;
    Context context;
    String collectionName;

    public RecyclerViewer() {
    }

    public RecyclerViewer(Context context, RecyclerView recycler, String collectionName) {
        this.recycler = recycler;
        this.context = context;
        this.collectionName = collectionName;
    }

    public void recyclerViewer() {
        db = FirebaseFirestore.getInstance();
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new GridLayoutManager(context,2));
        list = new ArrayList<>();
        adapter = new FrameAdapter(context, list);
        recycler.setAdapter(adapter);

        db.collection(collectionName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                FrameData frameData = document.toObject(FrameData.class);
                                list.add(frameData);
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(context, "Error " + task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
