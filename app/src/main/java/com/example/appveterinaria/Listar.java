package com.example.appveterinaria;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Listar extends AppCompatActivity {
    ListView lstMascotas;
    RequestQueue requestQueue;
    private final String URL="http://10.0.2.2:3000/animales";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            //Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            //v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loadUI();
        getData();
    }// on create

    /**
     * Obtiene los datos del web service
     */
    private void  getData(){
        //1.Habilitar un canal de comunicación
        requestQueue= Volley.newRequestQueue(this);
        //2. Preparar los datos del servicio. ¿Qué datos enviara el servicio? Rpta: arreglo de objetos Json
        JsonArrayRequest jsonArrayRequest;
        jsonArrayRequest = new JsonArrayRequest(
                // se importa la clase Request
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        renderData(jsonArray);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // se importa la clase Log
                        Log.e("Error en el servicio", volleyError.toString());
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    private void renderData(JSONArray jsonArray){
        try{
            
            ArrayAdapter<String> adapter; // Mostrar datos en el ListView
            ArrayList<String> listamascotas=new ArrayList<>();//contenedor de datos

            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                listamascotas.add("ID: "+jsonObject.getString("id")+" - "+jsonObject.getString("nombre")+" - "+jsonObject.getString("tipo")+" - "+jsonObject.getString("raza")+" - "+jsonObject.getString("genero")+" - "+jsonObject.getString("peso")+" - "+jsonObject.getString("color"));
            }
            //contexto
            adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listamascotas);
            lstMascotas.setAdapter(adapter);
        }catch (Exception error){
            Log.e("Error renderizado",error.toString());
        }
    }
    private void  loadUI(){
        lstMascotas=findViewById(R.id.lstMascotas);
    }
}