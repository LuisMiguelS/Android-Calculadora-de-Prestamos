package com.example.luismigueldesign.proyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AcercaDe extends AppCompatActivity implements View.OnClickListener
{
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
        getSupportActionBar().setTitle("Acerca de...");

        boton = (Button) findViewById(R.id.volver);

        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v == boton)
        {
            finish();
        }
    }
}
