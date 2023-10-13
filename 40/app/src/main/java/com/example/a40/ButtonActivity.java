package com.example.a40;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class ButtonActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button button,airboxbtn, ubikebtn, coursebtn;
    FirebaseUser user;
    TextView textView;
    ImageView btn_scan;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        btn_scan = findViewById(R.id.btn_scan);
        auth = FirebaseAuth .getInstance();
        button = findViewById(R.id.outbtn) ;
        textView = findViewById(R.id.user_details);
        airboxbtn = findViewById(R.id.airboxbtn);
        ubikebtn = findViewById(R.id.ubikebtn);
        coursebtn = findViewById(R.id.coursebtn);
        user = auth.getCurrentUser();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct != null)
        {
            String personEmail = acct.getEmail();
            textView.setText(personEmail);
        }
        if(user == null)
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            textView.setText(user.getEmail());
        }
        btn_scan.setOnClickListener(v->
                {
                    scanCode();
                }
        );

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                signOut();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        airboxbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAirboxActivity();

            }
        });
        ubikebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                openUbikeActivity();
            }
        });
        coursebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                openTimetableActivity();
            }
        });
    }

    private void scanCode()
    {
        ScanOptions options = new ScanOptions();
        options.setPrompt("音量上鍵打開閃光燈");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(options);
    }

    private void signOut()
    {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task)
            {
                finish();
                startActivity(new Intent(ButtonActivity.this, MainActivity.class));
            }
        });
    }
    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result ->
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ButtonActivity.this);
        builder.setTitle("結果");
        builder.setMessage(result.getContents());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
            }
        }).show();

    });
    public void openAirboxActivity(){
        Intent intent = new Intent(this, AirboxActivity.class);
        startActivity(intent);
    }
    public void  openUbikeActivity(){
        Intent intent = new Intent(this, UbikeActivity.class);
        startActivity(intent);
    }
    public void  openTimetableActivity(){
        Intent intent = new Intent(this, TimetableActivity.class);
        startActivity(intent);
    }

}