package br.fmu.app4an;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class CalculadoraDeGorjetaActivity extends AppCompatActivity {

    private EditText editTextValor;
    private TextView textViewValor;
    private TextView textViewPct;
    private TextView textViewGorjeta;
    private TextView textViewTotal;
    private SeekBar seekBar;
    private double valor = 0;
    private double porcentagem = 0.1;
    private double gorjeta = 0;
    private double total = 0;
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private NumberFormat pctFormat = NumberFormat.getPercentInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_de_gorjeta);
        editTextValor = findViewById(R.id.editTextValor);
        textViewValor = findViewById(R.id.textViewValor);
        textViewPct = findViewById(R.id.textViewPct);
        textViewGorjeta = findViewById(R.id.textViewGorjeta);
        textViewTotal = findViewById(R.id.textViewTotal);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(30);
        seekBar.setProgress(10);
        editTextValor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int valorInt;
                try {
                    valorInt = Integer.parseInt(editTextValor.getText().toString());
                } catch ( NumberFormatException e ) {
                    valorInt = 0;
                }
                valor = valorInt/100.0;
                recalcular();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                porcentagem = i/100.0;
                recalcular();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        recalcular();
    }

    private void recalcular() {
        gorjeta = valor * porcentagem;
        total = valor + gorjeta;
        textViewValor.setText(currencyFormat.format(valor));
        textViewGorjeta.setText(currencyFormat.format(gorjeta));
        textViewTotal.setText(currencyFormat.format(total));
        textViewPct.setText(pctFormat.format(porcentagem));
    }
}