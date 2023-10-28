package com.cedev.portaretratos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SliderActivity extends AppCompatActivity {

    private ImageView imageViewSlider;
    private int[] imageArray = {R.drawable.image1, R.drawable.image2, R.drawable.image3};
    private int currentIndex = 0;
    private Handler handler = new Handler();
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        imageViewSlider = findViewById(R.id.imageViewSlider);
        imageButton = findViewById(R.id.flechaImageButton);

        startSlider();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SliderActivity.this, MainActivity.class);
                // Iniciar la SecondActivity
                startActivity(intent);
            }
        });

    }

    private void startSlider() {
        // Runnable para cambiar las imágenes cada 3 segundos
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (currentIndex >= imageArray.length) {
                    currentIndex = 0;
                }
                imageViewSlider.setImageResource(imageArray[currentIndex]);
                currentIndex++;
                handler.postDelayed(this, 3000);
            }
        };
        handler.post(runnable);
    }

    public void changeImage(View view) {
        handler.removeCallbacksAndMessages(null); // Detiene el cambio automático de imágenes
        if (currentIndex >= imageArray.length) {
            currentIndex = 0;
        }
        imageViewSlider.setImageResource(imageArray[currentIndex]);
        currentIndex++;
    }

}