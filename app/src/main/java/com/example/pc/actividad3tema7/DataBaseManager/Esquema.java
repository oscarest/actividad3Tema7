package com.example.pc.actividad3tema7.DataBaseManager;

import android.provider.BaseColumns;

public class Esquema {

    public Esquema() {
    }

    public static abstract class Seccion implements BaseColumns {
        public static final String TABLE_NAME = "SECCION";

        public static final String COLUMN_NAME_ID = "idseccion";
        public static final String COLUMN_NAME_DESCRIPCION = "descripcion";
        public static final String COLUMN_NAME_MINSTOCK = "minstock";
        public static final String COLUMN_NAME_MAXSTOCK = "maxstock";

        public static final String COLUMN_TYPE_ID = "INTEGER";
        public static final String COLUMN_TYPE_DESCRIPCION = "TEXT";
        public static final String COLUMN_TYPE_MINSTOCK = "INTEGER";
        public static final String COLUMN_TYPE_MAXSTOCK = "INTEGER";

    }

    public static abstract class Producto implements BaseColumns {
        public static final String TABLE_NAME = "PRODUCTO";

        public static final String COLUMN_NAME_ID = "idproducto";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_CANTIDAD = "cantidad";
        public static final String COLUMN_NAME_IDSECCION = "idseccion";

        public static final String COLUMN_TYPE_ID = "INTEGER";
        public static final String COLUMN_TYPE_NOMBRE = "TEXT";
        public static final String COLUMN_TYPE_CANTIDAD = "INTEGER";
        public static final String COLUMN_TYPE_IDSECCION = "INTEGER";

    }

}