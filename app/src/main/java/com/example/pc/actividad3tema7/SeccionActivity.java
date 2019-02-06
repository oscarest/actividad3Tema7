package com.example.pc.actividad3tema7;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.actividad3tema7.Logic.LogicSeccion;
import com.example.pc.actividad3tema7.Model.Seccion;

import java.util.List;

public class SeccionActivity extends Activity {

    private EditText txtDescripcion;
    private EditText txtMinStock;
    private EditText txtMaxStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seccion);

        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtMinStock = findViewById(R.id.txtMinStock);
        txtMaxStock = findViewById(R.id.txtMaxStock);

    }

    public void insertarSeccion(View view) {
        String descripcion = txtDescripcion.getText().toString();
        String minStock = txtMinStock.getText().toString();
        String maxStock = txtMaxStock.getText().toString();

        if (descripcion.equals("") || minStock.equals("") || maxStock.equals("")) {
            mostrarMensaje("Todos los campos son obligatorios.");
        } else {
            Seccion s = new Seccion(descripcion, Integer.parseInt(minStock), Integer.parseInt(maxStock));
            LogicSeccion.insertarSeccion(this, s);
            mostrarMensaje("La sección " + descripcion + " ha sido insertada.");
            limpiarCuadrosTexto();
        }

    }

    public void eliminarSeccion(View view) {
        String descripcion = txtDescripcion.getText().toString();

        if (descripcion.equals("")) {
            mostrarMensaje("Debe indicar la sección a eliminar");
        } else {
            LogicSeccion.eliminarSeccion(this, new Seccion(descripcion, null, null));
            mostrarMensaje("Se ha eliminado la sección: " + descripcion);
            limpiarCuadrosTexto();
        }

    }

    public void modificarSeccion(View view) {
        String descripcion = txtDescripcion.getText().toString();
        String minStock = txtMinStock.getText().toString();
        String maxStock = txtMaxStock.getText().toString();

        if (descripcion.equals("") || minStock.equals("") || maxStock.equals("")) {
            mostrarMensaje("Todos los campos son obligatorios.");
        } else {
            Seccion s = new Seccion(descripcion, Integer.parseInt(minStock), Integer.parseInt(maxStock));
            LogicSeccion.editarSeccion(this, s);
            mostrarMensaje("Se ha modificado la sección: " + descripcion);
            limpiarCuadrosTexto();
        }

    }

    public void buscarSeccion(View view) {
        String descripcion = txtDescripcion.getText().toString();

        if (descripcion.equals("")) {
            mostrarMensaje("Debe indicar la sección a buscar");
        } else {
            Seccion s = LogicSeccion.buscarSeccion(this, new Seccion(descripcion, null, null));
            if (s == null) {
                mostrarMensaje("La sección " + descripcion + " no existe.");
            } else {
                mostrarMensaje("RECUPERADO: " + s.toString());
                limpiarCuadrosTexto();
            }
        }
    }

    public void listadoSecciones(View view) {
        TextView txtListadoSecciones = findViewById(R.id.txtListadoSecciones);
        txtListadoSecciones.setMovementMethod(new ScrollingMovementMethod());
        txtListadoSecciones.setText("");

        List<Seccion> lst = LogicSeccion.listaSecciones(this);
        if (lst == null) {
            mostrarMensaje("No existen secciones.");
        } else {
            StringBuilder msg = new StringBuilder();
            for (Seccion s : lst) {
                msg.append(s).append("\n");
            }
            txtListadoSecciones.append(msg.toString());
        }
    }

    private void mostrarMensaje(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void limpiarCuadrosTexto() {
        txtDescripcion.setText("");
        txtMinStock.setText("");
        txtMaxStock.setText("");
    }

}
