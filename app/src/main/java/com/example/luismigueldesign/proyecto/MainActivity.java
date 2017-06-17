package com.example.luismigueldesign.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button prestamo, acerca, salir;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*INSTANCIAS DE LOS BOTONES DE LA PANTALLA PRINCIPAL*/
        prestamo = (Button) findViewById(R.id.bCalcularPrestamo);
        acerca = (Button) findViewById(R.id.bAcercaDe);
        salir = (Button) findViewById(R.id.bSalir);

        /*AQUI SE LLAMA LA ACTIVITY SEGUN EL BOTON PRESIONADO*/
        prestamo.setOnClickListener(this);
        acerca.setOnClickListener(this);
        salir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v == prestamo)
        {
            Intent intent = new Intent(MainActivity.this, CalcularPrestamo.class);
            startActivity(intent);
        }

        if(v == acerca)
        {
            Intent intent = new Intent(MainActivity.this, AcercaDe.class);
            startActivity(intent);
        }

        if(v == salir)
        {
            finish();
        }
    }
}
