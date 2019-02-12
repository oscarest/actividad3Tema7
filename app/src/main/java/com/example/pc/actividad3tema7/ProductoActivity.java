package com.example.pc.actividad3tema7;

import android.app.Activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.actividad3tema7.Logic.LogicSeccion;
import com.example.pc.actividad3tema7.Logic.LogicProducto;
import com.example.pc.actividad3tema7.Model.Producto;
import com.example.pc.actividad3tema7.Model.Seccion;

import java.util.ArrayList;
import java.util.List;

public class ProductoActivity extends Activity {

    private EditText txtNombre;
    private EditText txtCantidad;
    private Spinner lstSeccion;

    private List <Seccion> listaSecciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        //txtNombre = findViewById(R.id.txtNombre);
        txtCantidad = findViewById(R.id.txtCantidad);
        lstSeccion = findViewById(R.id.lstSeccion);

        listaSecciones = LogicSeccion.listaSecciones(this);
        List lst = new ArrayList<>();
        for(Seccion s: listaSecciones){
            lst.add(s.getDescripcion());
        }
        lstSeccion.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lst));
    }

    public void insertarProducto(View view) {
        String nombre = txtNombre.getText().toString();
        String cantidad = txtCantidad.getText().toString();
        Long seccion = listaSecciones.get(lstSeccion.getSelectedItemPosition()).getId();

        if (nombre.equals("") || cantidad.equals("")) {
            mostrarMensaje("Todos los campos son obligatorios.");
        } else {
            Producto p = new Producto(nombre, Integer.parseInt(cantidad), seccion);
            LogicProducto.insertarProducto(this, p);
            mostrarMensaje("El producto " + nombre + " ha sido insertado.");
            limpiarCuadrosTexto();
        }

    }

    public void eliminarProducto(View view) {
        String nombre = txtNombre.getText().toString();

        if (nombre.equals("")) {
            mostrarMensaje("Debe indicar el producto a eliminar");
        } else {
            LogicProducto.eliminarProducto(this, new Producto(nombre, null, null));
            mostrarMensaje("Se ha eliminado el producto: " + nombre);
            limpiarCuadrosTexto();
        }

    }

    public void modificarProducto(View view) {
        String nombre = txtNombre.getText().toString();
        String cantidad = txtCantidad.getText().toString();
        Long seccion = listaSecciones.get(lstSeccion.getSelectedItemPosition()).getId();

        if (nombre.equals("") || cantidad.equals("")) {
            mostrarMensaje("Todos los campos son obligatorios.");
        } else {
            Producto p = new Producto(nombre, Integer.parseInt(cantidad), seccion);
            LogicProducto.editarProducto(this, p);
            mostrarMensaje("Se ha modificado el producto: " + nombre);
            limpiarCuadrosTexto();
        }

    }

    public void buscarProducto(View view) {
        String nombre = txtNombre.getText().toString();

        if (nombre.equals("")) {
            mostrarMensaje("Debe indicar el producto a buscar");
        } else {
            Producto p = LogicProducto.buscarProducto(this, new Producto(nombre, null, null));
            if (p == null) {
                mostrarMensaje("El Producto " + nombre + " no existe.");
            } else {
                mostrarMensaje("RECUPERADO: " + p.toString());
                limpiarCuadrosTexto();
            }
        }
    }

    public void listadoProductos(View view) {
        TextView txtListadoProductos = findViewById(R.id.txtListadoProductos);
        txtListadoProductos.setMovementMethod(new ScrollingMovementMethod());
        txtListadoProductos.setText("");

        List<Producto> lst = LogicProducto.listaProductos(this);
        if (lst == null) {
            mostrarMensaje("No existen productos.");
        } else {
            StringBuilder msg = new StringBuilder();
            for (Producto p : lst) {
                msg.append(p).append("\n");
            }
            txtListadoProductos.append(msg.toString());
        }
    }

    private void mostrarMensaje(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void limpiarCuadrosTexto() {
        txtNombre.setText("");
        txtCantidad.setText("");
    }

}
