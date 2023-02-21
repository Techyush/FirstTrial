package com.avd.firsttrial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FinalScreen extends AppCompatActivity {

    EditText edtSearch;
    TextView txtId, txtName, txtDesc, txtPrice, txtRating;
    Button btnSearchFood;
    DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_screen);

        firebaseDatabase = FirebaseDatabase.getInstance();

        edtSearch = findViewById(R.id.edtFoodId);
        txtId = findViewById(R.id.txtFoodId);
        txtName = findViewById(R.id.txtFoodName);
        txtDesc = findViewById(R.id.txtFoodDesc);
        txtPrice = findViewById(R.id.txtFoodPrice);
        txtRating = findViewById(R.id.txtFoodRating);
        btnSearchFood = findViewById(R.id.btnSearch);

        btnSearchFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDatabase= FirebaseDatabase.getInstance().getReference("Details");

                Query checkID = mDatabase.child("menu").orderByChild("f_id").equalTo(edtSearch.getText().toString());

                checkID.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            String id = snapshot.child(edtSearch.getText().toString()).child("f_id").getValue(String.class);
                            String name = snapshot.child(edtSearch.getText().toString()).child("name").getValue(String.class);
                            String desc = snapshot.child(edtSearch.getText().toString()).child("desc").getValue(String.class);
                            String price = snapshot.child(edtSearch.getText().toString()).child("price").getValue(String.class);
                            String rating = snapshot.child(edtSearch.getText().toString()).child("rating").getValue(String.class);

                            txtId.setText("Food Item ID : " + id);
                            txtName.setText("Name : " + name);
                            txtDesc.setText("Description : " + desc);
                            txtPrice.setText("Price : "+price);
                            txtRating.setText("Rating : "+rating);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}
