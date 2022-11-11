package com.example.sim_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sim_02.databinding.ActivityAddProductoBinding;
import com.example.sim_02.modelos.ProductoModel;

public class AddProductoActivity extends AppCompatActivity {

    private ActivityAddProductoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCrearProductoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = binding.txtNombreProductoAdd.getText().toString();
                String cantidadS = binding.txtCantidadProductoAdd.getText().toString();
                String precioS = binding.txtPrecioProductoAdd.getText().toString();

                if (!nombre.isEmpty() && !cantidadS.isEmpty() && !precioS.isEmpty()){
                    ProductoModel p = new ProductoModel(nombre, Integer.parseInt(cantidadS), Float.parseFloat(precioS));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PROD", p);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else{
                    Toast.makeText(AddProductoActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}