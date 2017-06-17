package com.example.luismigueldesign.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class CalcularPrestamo extends AppCompatActivity implements View.OnClickListener
{
    /*DECLARACION DE LAS VARIABLES A USAR EN ESTE ACTIVITY*/

    Button verCuotas, limpiar, seleccionarFecha, bFecha;
    EditText nombre, monto, tasa, fecha, cuota;
    int dia, mes, year;
    StringBuilder fechaFormat;
    DatePicker dFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_prestamo);
        getSupportActionBar().setTitle("Formulario para el préstamo");

        /*INSTANCIAS DE LOS BOTONES*/
        verCuotas = (Button) findViewById(R.id.bCalcular);
        limpiar = (Button) findViewById(R.id.bLimpiar);
        bFecha = (Button) findViewById(R.id.bFecha);
        seleccionarFecha = (Button) findViewById(R.id.bSeleccionarFecha);

        /*INSTANCIAS DE LOS EDIT_TEXT*/
        nombre = (EditText) findViewById(R.id.etNombre);
        monto = (EditText) findViewById(R.id.etMonto);
        tasa = (EditText) findViewById(R.id.etTasa);
        fecha = (EditText) findViewById(R.id.etFecha);
        cuota = (EditText) findViewById(R.id.etCuotas);

        /*INSTANCIA DEL DATE_PICKER*/
        dFecha = (DatePicker) findViewById(R.id.datePicker);

        /*MANEJANDO EL EVENTO OnClickListener EN LOS BOTONES*/
        verCuotas.setOnClickListener(this);
        limpiar.setOnClickListener(this);
        bFecha.setOnClickListener(this);
        seleccionarFecha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        /*PRESENTA LOS RESULTADOS DEL CALCULO DEL PRESTAMO*/
        if(v == verCuotas)
        {
            /*VALIDA QUE TODOS LOS CAMPOS ESTEN LLENOS PARA PODER ENVIARLOS*/
            if(nombre.getText().toString().isEmpty() || monto.getText().toString().isEmpty() || tasa.getText().toString().isEmpty() || fecha.getText().toString().isEmpty())
            {
                Toast.makeText(this, "Por favor, llene todos los campos del formulario para proseguir.", Toast.LENGTH_LONG).show();
            }

            else if(tasa.getText().toString().equals("0") || cuota.getText().toString().equals("0"))
            {
                Toast.makeText(this, "Verifica que los campos Tasa y Cuota, no tengan el valor 0.", Toast.LENGTH_LONG).show();
            }

            /*SI TODOS LOS CAMPOS ESTAN LLENOS, ENTONCES SE ENVIAN A LA VISTA DEL CALCULO*/
            else
            {
                /*MANDA LOS DATOS DE ESTE ACTIVITY A LA VISTA DEL CALCULO DEL PRESTAMO*/
                Intent intent = new Intent(CalcularPrestamo.this, CuotasPrestamo.class);
                intent.putExtra("dia", String.valueOf(dia)).toString();
                intent.putExtra("mes", String.valueOf(mes)).toString();
                intent.putExtra("year", String.valueOf(year)).toString();
                intent.putExtra("solicitante", String.valueOf(nombre.getText())).toString();
                intent.putExtra("tasa", String.valueOf(tasa.getText())).toString();
                intent.putExtra("monto", String.valueOf(monto.getText())).toString();
                intent.putExtra("cuota", String.valueOf(cuota.getText())).toString();
                startActivity(intent);
            }
        }

         /*LIMPIA LOS EDIT TEXT*/
        if(v == limpiar)
        {
            nombre.setText("");
            monto.setText("");
            tasa.setText("");
            fecha.setText("");
            cuota.setText("");
        }

        /*PONE DE MODO VISIBLE EL DATEPICKER*/
        if(v == bFecha)
        {
            dFecha.setVisibility(View.VISIBLE);
            seleccionarFecha.setVisibility(View.VISIBLE);
            bFecha.setVisibility(View.GONE);
        }

        /*OCULTA EL DATE_PICKER DESPUES DE HABER SELECCIONADO LA FECHA*/
        if(v == seleccionarFecha)
        {
            dFecha.setVisibility(View.GONE);
            seleccionarFecha.setVisibility(View.GONE);
            bFecha.setVisibility(View.VISIBLE);

            /*CAPTURANDO LOS DATOS POR SEPARADOS DE LA FECHA SELECCIONADA*/
            dia = dFecha.getDayOfMonth();
            mes = dFecha.getMonth() + 1;
            year = dFecha.getYear();

            /*DANDO FORMATO A LA FECHA CON LOS VALORES OBTENIDOS ANTERIOMENTE*/
            fechaFormat = new StringBuilder();
            fechaFormat.append(dia + "/" + mes + "/" + year);

            /*LLENANDO EL CAMPO FECHA DEL FORMULARIO DE PRESTAMO*/
            fecha.setText(fechaFormat);
        }
    }
}