package com.example.qratten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn, btj;
    EditText a1, a2, a3, a4;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("asd", MODE_PRIVATE);
        a1 = findViewById(R.id.editText);
        a2 = findViewById(R.id.editText5);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (!(a1.getText().toString().isEmpty() || a2.getText().toString().isEmpty())) {
                    Toast.makeText(getApplicationContext(), "PLACE THE QR CODE TO SCAN", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", a1.getText().toString());
                    editor.putString("S_id", a2.getText().toString());
                    editor.apply();
                    Intent iot = new Intent(getApplicationContext(), Scannerlog.class);
                    startActivity(iot);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"EMPTY VALUES",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
