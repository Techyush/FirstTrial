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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button buttonLogOut, buttonSend;
    TextView textViewDetails;
    FirebaseUser user;
    String uid, user_email;
    EditText edtSendName,edtSendSurname;
    DatabaseReference personDBDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        edtSendName = findViewById(R.id.edtName);
        edtSendSurname = findViewById(R.id.edtSurname);
        buttonSend = findViewById(R.id.btnSubmit);
        buttonLogOut = findViewById(R.id.btnLogOut);
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

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDetails();
            }
        });


        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void insertDetails(){
        String mail = user_email.toString();
        String name = edtSendName.getText().toString();
        String surname = edtSendSurname.getText().toString();
        String id = uid.toString();

        Details details = new Details(id,mail,name,surname);

        personDBDetails.child(id).child("data").setValue(details);
        Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
    }
}