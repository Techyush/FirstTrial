package com.avd.firsttrial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FinalScreen extends AppCompatActivity {

    EditText edtSearch;
    TextView txtId, txtName, txtDesc, txtPrice, txtRating;
    Button btnSearchFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_screen);

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
                String _foodID = txtId.getText().toString();
                String _foodName = txtName.getText().toString();
                String _foodDesc = txtDesc.getText().toString();
                String _foodPrice = txtPrice.getText().toString();
                String _foodRating = txtRating.getText().toString();

                Query checkFood = FirebaseDatabase.getInstance().getReference("menu").child("id").child("chinese").equalTo(_foodID);
                System.out.println(checkFood);

                checkFood.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if(snapshot.exists()){

                            String showID = snapshot.child(_foodID).child("id").getValue(String.class);
                            txtId.setText(showID);
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(FinalScreen.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
