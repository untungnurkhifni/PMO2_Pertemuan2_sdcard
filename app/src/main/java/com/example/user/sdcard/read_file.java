package com.example.user.sdcard;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class read_file extends AppCompatActivity {

    EditText FILENAME;
    EditText text;
    Button buatfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_file);

        text = findViewById(R.id.isifile);
        FILENAME = findViewById(R.id.namafile);
        buatfile = findViewById(R.id.buatfile);

        buatfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(read_file.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    public void readFile(View v){
        if(isExternalStorageReadable()) {
            StringBuilder sb = new StringBuilder();
            try {
                File textFile = new File(Environment.getExternalStorageDirectory(), FILENAME.getText().toString());
                FileInputStream fis = new FileInputStream(textFile);

                if (fis != null) {
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader buff = new BufferedReader(isr);

                    String line = null;
                    while ((line = buff.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    fis.close();
                }
                text.setText(sb);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(this, "Tidak ada file", Toast.LENGTH_LONG ).show();
        }
    }

    public boolean isExternalStorageReadable(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())){
            Log.i("State", "yes, it is Readable!");
            return true;
        }
        else{
            return false;
        }

    }
}
