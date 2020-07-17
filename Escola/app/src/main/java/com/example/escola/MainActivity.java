package com.example.escola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtN1, edtN2;
    TextView txtM, txtSit;
    ImageView imgSit;
    LinearLayout layResult;
    InputMethodManager imn; //metodo para esconder teclado
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtN1 = findViewById(R.id.edtN1);
        edtN2 = findViewById(R.id.edtN2);
        txtM = findViewById(R.id.txtMedia);
        txtSit = findViewById(R.id.txtSituacao);
        imgSit = findViewById(R.id.imgSituacao);
        layResult = findViewById(R.id.layResult);
        imn = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);//setando o teclado no imn

        //tornando o layout invisivel
        layResult.setVisibility(View.INVISIBLE);

    }

    public void calcular(View view) {
        //validacao dos dados
        boolean ok = true;
        if (edtN1.getText().toString().trim().isEmpty()){
            ok = false;
            edtN1.setError(getString(R.string.msgErro));
        }
        if (edtN2.getText().toString().trim().isEmpty()){
            ok = false;
            edtN2.setError(getString(R.string.msgErro));
        }
        if (ok) {
            imn.hideSoftInputFromWindow(edtN1.getWindowToken(),0); // Fazendo o teclado sumir quando (ok)
            layResult.setVisibility(View.VISIBLE);
            //calculando a média
            float n1 = Float.parseFloat(edtN1.getText().toString());
            float n2 = Float.parseFloat(edtN2.getText().toString());
            float m = (n1 + n2) / 2;
            //Resultado da Situação
            txtM.setText(String.format("%.1f", m));
            if (m < 5) { //reprovado
                txtSit.setText(getString(R.string.strSitReprovado));
                txtSit.setTextColor(getResources().getColor(R.color.corReprovado));
                //criando um toast
                Toast.makeText(getApplicationContext(),getString(R.string.strMsgReprovado),Toast.LENGTH_SHORT).show();
                //mudando o emoji
                imgSit.setImageResource(R.drawable.emojireprovado);
            } else if (m < 7) { //Recuperação
                txtSit.setText(getString(R.string.strSitRedcperacao));
                txtSit.setTextColor(getResources().getColor(R.color.corRecuperacao));
                Toast.makeText(getApplicationContext(),getString(R.string.strMsgRecuperacao),Toast.LENGTH_SHORT).show();
                imgSit.setImageResource(R.drawable.emojirecuperacao);
            } else { // Aprovado
                txtSit.setText(getString(R.string.strSitAprovado));
                txtSit.setTextColor(getResources().getColor(R.color.corAprovado));
                Toast.makeText(getApplicationContext(),getString(R.string.strMsgAprovado),Toast.LENGTH_SHORT).show();
                imgSit.setImageResource(R.drawable.emojiaprovado);
            }
        }
    }
}