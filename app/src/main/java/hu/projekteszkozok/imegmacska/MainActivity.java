package hu.projekteszkozok.imegmacska;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Firebase mRef;
    private Random rand;
    //private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    //private DatabaseReference databaseReference = firebaseDatabase.getReference();"https://imegmacska.firebaseio.com/"
    //private Firebase mRefChild = mRef.

    private ArrayList<String> links = new ArrayList<>();

    private Button mLike, mDislike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        rand = new Random();

        links.add("https://imegmacska.firebaseio.com/cat1");
        links.add("https://imegmacska.firebaseio.com/cat2");
        links.add("https://imegmacska.firebaseio.com/cat3");
        links.add("https://imegmacska.firebaseio.com/cat4");
        links.add("https://imegmacska.firebaseio.com/cat5");
        links.add("https://imegmacska.firebaseio.com/cat6");
        links.add("https://imegmacska.firebaseio.com/cat7");
        links.add("https://imegmacska.firebaseio.com/cat8");
        links.add("https://imegmacska.firebaseio.com/cat9");
        links.add("https://imegmacska.firebaseio.com/cat10");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);

        mRef = new Firebase(links.get(rand.nextInt(10)));

        mLike = findViewById(R.id.like);
        mDislike = findViewById(R.id.dislike);

        mDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef = new Firebase(links.get(rand.nextInt(10)));

                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Map<String, String> map = dataSnapshot.getValue(Map.class);
                        Picasso.get().load(map.get("url")).into(imageView);

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


            }
        });

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String, String> map = dataSnapshot.getValue(Map.class);
                Picasso.get().load(map.get("url")).into(imageView);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        imageView = findViewById(R.id.img_field);
    }




}
