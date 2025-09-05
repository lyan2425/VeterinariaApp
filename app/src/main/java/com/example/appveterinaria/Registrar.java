package com.example.appveterinaria;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Registrar extends AppCompatActivity {

    EditText edtNombre,edtTipo,edtRaza,edtGenero,edtPeso, edtColor;
    Button btnRegistrar;
    RequestQueue requestQueue;
    private final String URL="http://10.0.2.2:3000/animales";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            //Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            //v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loadUI();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();;
            }
        });
    }
    private void sendData(){
        requestQueue= Volley.newRequestQueue(this);
        JSONObject jsonObject= new JSONObject();

        try {
            jsonObject.put("nombre",edtNombre.getText().toString());
            jsonObject.put("tipo",edtTipo.getText().toString());
            jsonObject.put("raza",edtRaza.getText().toString());
            jsonObject.put("genero",edtGenero.getText().toString());
            jsonObject.put("peso",edtPeso.getText().toString());
            jsonObject.put("color",edtColor.getText().toString());

        }catch (Exception error){
            Log.e("error en Json",error.toString());
        }

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(
                Request.Method.POST,
                URL,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            String id= jsonObject.getString("id");
                            Toast.makeText(getApplicationContext(),id, Toast.LENGTH_SHORT).show();
                        }catch (Exception error){
                            Log.e("Error en JSON", error.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("Error en el servicio",volleyError.toString());
                    }
                }

        );
        requestQueue.add(jsonObjectRequest);
    }

    private void loadUI(){
        edtNombre=findViewById(R.id.edtnombre);
        edtTipo=findViewById(R.id.edttipo);
        edtRaza=findViewById(R.id.edtraza);
        edtGenero=findViewById(R.id.edtgenero);
        edtPeso=findViewById(R.id.edtpeso);
        edtColor=findViewById(R.id.edtcolor);
        btnRegistrar=findViewById(R.id.btnRegistrar);

    }
}