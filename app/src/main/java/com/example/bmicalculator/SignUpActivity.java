package com.example.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText edtname;
    EditText edtemail;
    EditText edtpassword;
    Button signup;
    LinearLayout llLogin;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtname=findViewById(R.id.edtname);
        edtemail=findViewById(R.id.edtemail);
        edtpassword=findViewById(R.id.edtpassword);
        signup=findViewById(R.id.btnSignup);
        llLogin=findViewById(R.id.llLogin);

        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(isValidate()){
                   setDataInFirebase();
               }

            }
        });

        llLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }

    private void setDataInFirebase() {

        mAuth.createUserWithEmailAndPassword(edtemail.getText().toString(), edtpassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            /***add data in realtime firebase database*/
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("users");
                            UserInfo userinformation = new UserInfo(edtname.getText().toString(),"",edtemail.getText().toString(),edtpassword.getText().toString());
                            myRef.child(edtemail.getText().toString()).setValue(userinformation);
                            // Sign in success
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserInfo info= (UserInfo) user.getProviderData();
                            Log.d("Message", "createUserWithEmail:success");
                            Toast.makeText(SignUpActivity.this,"User Registered Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Message", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this,task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });


    }

    public boolean isValidate(){
        if(edtname.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter name",Toast.LENGTH_LONG).show();
            return false;
        }
       else if(edtemail.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return false;
        }else if(edtpassword.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return false;
        }else if(edtpassword.getText().toString().length()<6){
            Toast.makeText(this,"Password must be 6 character long",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}