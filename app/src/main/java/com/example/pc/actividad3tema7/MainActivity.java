package com.example.pc.actividad3tema7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pc.actividad3tema7.Logic.LogicSeccion;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button btnGestionProductos = findViewById(R.id.btnGestionProductos);
        btnGestionProductos.setEnabled(LogicSeccion.totalSecciones(this) > 0);
    }

    public void clicSecciones(View view) {
        startActivity(new Intent(this, SeccionActivity.class));
    }

    public void clicProductos(View view) {
        startActivity(new Intent(this, ProductoActivity.class));
    }

}
