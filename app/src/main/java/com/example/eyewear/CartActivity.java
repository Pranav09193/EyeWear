package com.example.eyewear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.eyewear.adapters.CartAdapter;
import com.example.eyewear.adapters.MyAdapter;
import com.example.eyewear.model.CartData;
import com.example.eyewear.model.ImageData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    List<CartData> list;
    CartAdapter adapter;
    FirebaseFirestore db;
    FirebaseAuth auth;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        db=FirebaseFirestore.getInstance();
        recyclerView=findViewById(R.id.cartRecycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        adapter = new CartAdapter(list, this);
        recyclerView.setAdapter(adapter);
        auth=FirebaseAuth.getInstance();

        db.collection("AddToCart").document(auth.getCurrentUser().getUid()).collection("CurrentUser")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CartData cartData = document.toObject(CartData.class);
                                list.add(cartData);
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(CartActivity.this, "Error " + task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}