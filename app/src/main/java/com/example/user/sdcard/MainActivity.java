package com.example.user.sdcard;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    EditText FILENAME;
    EditText fileName;
    EditText text;
    Button baca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baca = findViewById(R.id.baca);
        fileName = findViewById(R.id.namafile);
        text = findViewById(R.id.isifile);

        baca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, read_file.class);
                startActivity(i);
            }
        });

    }


//        FILENAME = findViewById(R.id.namafile);
//    }

    public boolean isExternalStorageWritable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Log.i("State", "yes, it is writable!");
            return true;
        } else {
            return false;
        }

    }

    public void writeFile(View v) {
        if (isExternalStorageWritable() || checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            File textfile = new File(Environment.getExternalStorageDirectory(), fileName.getText().toString());
            try {
                FileOutputStream fos = new FileOutputStream(textfile);
                fos.write(text.getText().toString().getBytes());
                fos.close();
                Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Tidak bisa mengakses sd card", Toast.LENGTH_LONG).show();
        }
    }


    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

}
