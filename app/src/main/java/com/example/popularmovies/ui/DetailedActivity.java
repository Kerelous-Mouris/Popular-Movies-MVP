package com.example.popularmovies.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popularmovies.R;
import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private Intent action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();
        action = new Intent(this, MainActivity.class);

        String imagePath = intent.getStringExtra("ImageSource");
        String regexTarget = "\\bw500\\b";
        String editedImagePath = imagePath.replaceAll(regexTarget, "/w780");
        System.out.println(editedImagePath);


        textView.setText(intent.getStringExtra("description"));
        Picasso.get().load(editedImagePath).into(imageView);
    }
}