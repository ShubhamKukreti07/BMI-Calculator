package com.example.bmicalculator;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("users");
//
//        myRef.child("users").child("email").setValue("deepti");
//        myRef.child("users").child("passort").setValue("deepti");
//        UserInfo userinformation = new UserInfo("Shubh","18","shubh@gmail.com","123456");
//        myRef.child("2").setValue(userinformation);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
           // reload();
        }
        mAuth.createUserWithEmailAndPassword("shubh@gmail.com", "123456")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Message", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserInfo info= (UserInfo) user.getProviderData();
                           // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Message", "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });


        mAuth.signInWithEmailAndPassword("shubh@gmail.com", "123456")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("successMessage", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("successMessage", "signInWithEmail:failure", task.getException());
//                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });
    }
}