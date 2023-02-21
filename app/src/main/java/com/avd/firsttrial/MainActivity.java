package com.avd.firsttrial;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button buttonLogOut, buttonSend, buttonNext;
    TextView textViewDetails;
    FirebaseUser user;
    String uid, user_email;
    EditText edtSendId, edtSendName,edtSendDesc, edtSendPrice, edtSendRating ;
    DatabaseReference personDBDetails;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        edtSendId = findViewById(R.id.edtId);
        edtSendName = findViewById(R.id.edtName);
        edtSendDesc = findViewById(R.id.edtDesc);
        edtSendPrice = findViewById(R.id.edtPrice);
        edtSendRating = findViewById(R.id.edtRating);
        buttonSend = findViewById(R.id.btnSubmit);
        buttonLogOut = findViewById(R.id.btnLogOut);
        buttonNext = findViewById(R.id.btnNext);
        textViewDetails = findViewById(R.id.txtUserDetails);
        user = auth.getCurrentUser();
        assert user != null;
        uid = user.getUid();
        user_email = user.getEmail();


        personDBDetails = FirebaseDatabase.getInstance().getReference().child("Details");

        if(user==null){
            Intent intent = new Intent(getApplicationContext(), login.class);
            startActivity(intent);
            finish();
        }
        else {
            textViewDetails.setText(user.getEmail());
        }

        buttonSend.setOnClickListener(view -> insertDetails());

        buttonNext.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), FinalScreen.class);
            startActivity(intent);
            finish();
        });

        buttonLogOut.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), login.class);
            startActivity(intent);
            finish();
        });
    }

    private void insertDetails(){
        String f_id = edtSendId.getText().toString();
        String mail = user_email;
        String name = edtSendName.getText().toString();
        String desc = edtSendDesc.getText().toString();
        String price = edtSendPrice.getText().toString();
        String rating = edtSendRating.getText().toString();

        Details details = new Details(f_id,name,desc,price,rating,mail);

        personDBDetails.child("menu").child(f_id).setValue(details);
        Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
    }
}