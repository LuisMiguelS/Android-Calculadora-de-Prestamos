package com.example.luismigueldesign.proyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CuotasPrestamo extends AppCompatActivity
{
    TableRow row;
    TextView tvSolicitante;
    ArrayList listado;
    int x, aux_dia, cuota, tasa, dia, mes, year;
    Double intereses, amortizacion, pago, capital, total_amortizacion = 0.0, total_intereses = 0.0, total_pago = 0.0;
    StringBuilder fecha_pago;
    String solicitante;
    DecimalFormat formato_decimal = new DecimalFormat("###,###.##");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuotas_prestamo);
        getSupportActionBar().setTitle("Cuotas mensuales");

        /*INSTANCIANDO LA TABLA*/
        TableLayout table = (TableLayout) this.findViewById(R.id.tabla);

        /*INSTANCIADO ALGUNOS ELEMENTOS DEL LAYOUT*/
        tvSolicitante = (TextView) findViewById(R.id.tvSolicitante);

        /*-------RECIBIENDO LOS DATOS QUE VIENEN DEL ACTIVITY DEL FORMULARIO-------*/
        Bundle datos = getIntent().getExtras();
        solicitante = datos.getString("solicitante");

            /*CONVIRTIENDO A ENTERO LOS DATOS DE LA FECHA, LA CUOTA Y EL MONTO*/
        listado = new ArrayList();
        cuota = Integer.parseInt(datos.getString("cuota"));
        dia = Integer.parseInt(datos.getString("dia"));
        mes = Integer.parseInt(datos.getString("mes"));
        year = Integer.parseInt(datos.getString("year"));
        capital = Double.parseDouble(datos.getString("monto"));
        tasa = Integer.parseInt(datos.getString("tasa"));
        aux_dia = dia;
        amortizacion = capital / cuota;
        /*---------------------//////////---------------------*/

        tvSolicitante.setText("¡" + solicitante + "!" + " Las cuotas de tu préstamo son las siguientes:");

        /*GENERANDO LAS FECHAS PARA LOS PAGOS DEL PRESTAMOS X CUOTAS*/
        for(x = 1; x <= cuota; x++)
        {
            fecha_pago = new StringBuilder();

            if(x == 1)
            {
                row = (TableRow) LayoutInflater.from(this).inflate(R.layout.filas_pares, null);
                ((TextView)row.findViewById(R.id.tvCuota)).setText("0");
                ((TextView)row.findViewById(R.id.tvCapital)).setText(String.valueOf(formato_decimal.format(capital)));

                table.addView(row);
                table.requestLayout();
            }

            if(x == 1 && dia == 31)
            {
                dia = 30;
                aux_dia = dia;
            }

            if(dia > 28 && mes == 2)
            {
                aux_dia = dia;
                dia = 28;
            }

            if(mes > 12)
            {
                mes = 1;
                year++;
            }

            fecha_pago.append(dia + "/" + mes + "/" + year);
            intereses = (capital * tasa) / 100;
            capital =  capital - amortizacion;
            pago = amortizacion + intereses;

            total_amortizacion = total_amortizacion + amortizacion;
            total_intereses = total_intereses + intereses;
            total_pago = total_pago + pago;

            if(x%2 != 0)
            {
                row = (TableRow) LayoutInflater.from(this).inflate(R.layout.filas_impares, null);
                ((TextView)row.findViewById(R.id.tvCuota)).setText(String.valueOf(x));
                ((TextView)row.findViewById(R.id.tvCapital)).setText(String.valueOf(formato_decimal.format(capital)));
                ((TextView)row.findViewById(R.id.tvAmortizacion)).setText(String.valueOf(formato_decimal.format(amortizacion)));
                ((TextView)row.findViewById(R.id.tvIntereses)).setText(String.valueOf(formato_decimal.format(intereses)));
                ((TextView)row.findViewById(R.id.tvPago)).setText(String.valueOf(formato_decimal.format(pago)));
                ((TextView)row.findViewById(R.id.tvFecha)).setText(String.valueOf(fecha_pago));
            }

            else
            {
                row = (TableRow) LayoutInflater.from(this).inflate(R.layout.filas_pares, null);
                ((TextView)row.findViewById(R.id.tvCuota)).setText(String.valueOf(x));
                ((TextView)row.findViewById(R.id.tvCapital)).setText(String.valueOf(formato_decimal.format(capital)));
                ((TextView)row.findViewById(R.id.tvAmortizacion)).setText(String.valueOf(formato_decimal.format(amortizacion)));
                ((TextView)row.findViewById(R.id.tvIntereses)).setText(String.valueOf(formato_decimal.format(intereses)));
                ((TextView)row.findViewById(R.id.tvPago)).setText(String.valueOf(formato_decimal.format(pago)));
                ((TextView)row.findViewById(R.id.tvFecha)).setText(String.valueOf(fecha_pago));
            }

            table.addView(row);
            table.requestLayout();

            dia = aux_dia;
            mes++;
        }

        row = (TableRow) LayoutInflater.from(this).inflate(R.layout.fila_final, null);
        ((TextView)row.findViewById(R.id.tvCapital)).setText("Total");
        ((TextView)row.findViewById(R.id.tvAmortizacion)).setText(String.valueOf(formato_decimal.format(total_amortizacion)));
        ((TextView)row.findViewById(R.id.tvIntereses)).setText(String.valueOf(formato_decimal.format(total_intereses)));
        ((TextView)row.findViewById(R.id.tvPago)).setText(String.valueOf(formato_decimal.format(total_pago)));

        table.addView(row);
        table.requestLayout();
    }
}