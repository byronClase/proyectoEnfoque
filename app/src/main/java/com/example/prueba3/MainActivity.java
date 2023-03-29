package com.example.prueba3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.JsonArrayRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private TextView idtv;
    private TextView nombretv;
    private TextView preciotv;
    private TextView unidadestv;
    private TextView ivatv;

    private RequestQueue mQueue;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.textViewPrincipal);
//        idtv = findViewById(R.id.textViewId);
//        nombretv = findViewById(R.id.textViewNombre);
//        preciotv = findViewById(R.id.textViewPrecio);
//        unidadestv = findViewById(R.id.textViewUnidades);
//        ivatv = findViewById(R.id.textViewIva);
        mQueue = Volley.newRequestQueue(this);

        String url = "http://192.168.0.2/byron/componentes_tienda.json";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int id = jsonObject.getInt("id");
                            String nombre = jsonObject.getString("nombre");
                            float precio = jsonObject.getInt("precio");
                            int unidades = jsonObject.getInt("unidades_disponibles");
                            double iva = jsonObject.getDouble("iva");


                            String result =
                                    "ID: " + id + "\n" +
                                            "Nombre: " + nombre + "\n" +
                                            "Precio: " + precio + "\n" +
                                            "Unidades: " + unidades + "\n" +
                                            "IVA: " + iva + "\n\n";

                            mTextViewResult.append(result);
//                            idtv.setText(String.valueOf(id));
//                            nombretv.append(nombre);
//                            preciotv.setText(String.valueOf(precio));
//                            unidadestv.setText(String.valueOf(unidades));
//                            ivatv.setText(String.valueOf(iva));

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> error.printStackTrace()
        );

        mQueue.add(jsonArrayRequest);
    }

}


