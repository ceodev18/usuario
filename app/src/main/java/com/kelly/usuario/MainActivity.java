package com.kelly.usuario;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kelly.usuario.Interfaz.RegisterActivity;

public class MainActivity extends AppCompatActivity {
    private Button b_ingresar,b_registrar;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setElements();
        setActions();
    }
    private void setElements(){
        b_ingresar= (Button) findViewById(R.id.b_ingresar);
        b_registrar= (Button) findViewById(R.id.b_registrar);
    }
    private void setActions(){
        b_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        b_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);

            }
        });

    }
}
