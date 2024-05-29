package com.example.eyewear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.eyewear.adapters.FrameAdapter;
import com.example.eyewear.model.FrameData;
import com.example.eyewear.model.ImageData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class FrameDetailsActivity extends AppCompatActivity {

    TextView price,name,rating,description;
    ImageView frameImage;
    Button addToCart;

    FrameData frameData;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_details);

        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        final Object obj=getIntent().getSerializableExtra("detail");
        if(obj instanceof FrameData)
        {
            frameData=(FrameData) obj;

        }
        name=findViewById(R.id.detailName);
        price=findViewById(R.id.detailPrice);
        rating=findViewById(R.id.detailRating);
        description=findViewById(R.id.detailDescription);
        frameImage=findViewById(R.id.detailImage);

        addToCart=findViewById(R.id.detailAddToCart);

        if(frameData!=null){
            Glide.with(getApplicationContext()).load(frameData.getImageUrl()).into(frameImage);
            name.setText(frameData.getName());
            price.setText(frameData.getPrice());
            rating.setText(frameData.getRating());
            description.setText(frameData.getDescription());
        }

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addedTocart();
                Toast.makeText(FrameDetailsActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addedTocart() {
        String saveCurrentDate,saveCurrentTime;
        Calendar calendar=Calendar.getInstance();

        SimpleDateFormat currentDate=new SimpleDateFormat("dd/MM/yyyy");
        saveCurrentDate =currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a ");
        saveCurrentTime =currentTime.format(calendar.getTime());

        final HashMap<String,Object> cartMap=new HashMap<>();
        cartMap.put("name",frameData.getName());
        cartMap.put("imageUrl",frameData.getImageUrl());
        cartMap.put("price",price.getText());
        cartMap.put("currentDate",saveCurrentDate);
       cartMap.put("currentTime",saveCurrentTime);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(FrameDetailsActivity.this, "Added to the cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}