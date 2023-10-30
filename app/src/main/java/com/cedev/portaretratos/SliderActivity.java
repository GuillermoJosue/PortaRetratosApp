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

    //Para desaparecer boton
    private static final long DELAY = 3000; // 3 segundos, ajusta según tus necesidades

    ImageButton imageButton;

    //Para desaparecer boton
    private Runnable hideArrowRunnable = new Runnable() {
        @Override
        public void run() {
            imageButton.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        imageViewSlider = findViewById(R.id.imageViewSlider);
        imageButton = findViewById(R.id.flechaImageButton);

        startSlider();
        //Para desaparecer boton
        handler.postDelayed(hideArrowRunnable, DELAY);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SliderActivity.this, MainActivity.class);
                // Iniciar la SecondActivity
                startActivity(intent);
            }
        });

        imageViewSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Muestra el ícono de flecha
                imageButton.setVisibility(View.VISIBLE);

                // Luego de mostrarlo, programa su ocultamiento después de un tiempo
                handler.removeCallbacks(hideArrowRunnable); // Asegúrate de eliminar cualquier callback anterior
                handler.postDelayed(hideArrowRunnable, DELAY);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(hideArrowRunnable); // Es importante limpiar el callback para evitar memory leaks
    }

}