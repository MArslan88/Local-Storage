package com.mhdarslan.localstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText et,et2;
    TextView tv;
    Button load_btn, save_btn;
    String FILENAME = "readFile.txt";
    static final int READ_SIZE = 100;
    StringBuilder str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=findViewById(R.id.et);
        et2=findViewById(R.id.et2);
        tv=findViewById(R.id.tv);
        load_btn=findViewById(R.id.load_btn);
        save_btn=findViewById(R.id.save_btn);


        load_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData(view);
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData(view);
            }
        });


    }

    public void loadData(View view){
        try {
            FileInputStream fileInputStream = openFileInput(FILENAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            char[] ch = new char[READ_SIZE];
            str = new StringBuilder();
            int c;

            while ((c = inputStreamReader.read(ch)) > 0 ){
                String readStr = String.copyValueOf(ch,0,c);
                str.append(readStr);
                ch = new char[READ_SIZE];
            }

            tv.setText(str);
            Toast.makeText(this, "File loaded successfully", Toast.LENGTH_SHORT).show();


            // logic to Intent according to required condition
            String newString = str.toString();
            switch (newString){
                case "en":
                    Intent secondIntent = new Intent(MainActivity.this,SecondActivity.class);
                    startActivity(secondIntent);
                    break;
                case "fr":
                    Intent thirdIntent = new Intent(MainActivity.this,ThirdActivity.class);
                    startActivity(thirdIntent);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void saveData(View view){
        String data = "";

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("fname",et.getText().toString());
        hashMap.put("lname",et2.getText().toString());

        data = hashMap.toString();

        try {
            FileOutputStream fileOutputStream = openFileOutput(FILENAME,MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(data);

            outputStreamWriter.flush();
            outputStreamWriter.close();
            et.setText("");
            Toast.makeText(this, "File saved successfully!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        loadData(view);

    }


}