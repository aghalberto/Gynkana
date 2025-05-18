package com.pmdm.gynkana;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

public class DialogActivity extends AppCompatActivity {

    EditText password = (EditText) findViewById(R.id.password);
    ImageView imageView = (ImageView) findViewById(R.id.imgDialog);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Toast.makeText(getApplicationContext(), "ANTES", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(getApplicationContext(), "TEXT CHANGED", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(getApplicationContext(), "AFTER", Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        imageView.setImageResource(R.drawable.aqualand);
    }

    /**
     * Cambia la imagen de la vista
     * @param idImagen
     */
    public void setImagen(int idImagen){
        imageView.setImageResource(idImagen);
    }

}
