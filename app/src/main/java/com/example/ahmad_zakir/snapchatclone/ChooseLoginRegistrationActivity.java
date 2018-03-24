package com.example.ahmad_zakir.snapchatclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseLoginRegistrationActivity extends AppCompatActivity {
      Button mlogin1 , mregistration1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login_registration);

         mlogin1 = (Button)findViewById(R.id.login1);
         mregistration1 = (Button)findViewById(R.id.registration1);

        mlogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), LoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });


        mregistration1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), RegistrationActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}
