package com.example.sim_02.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sim_02.R;
import com.example.sim_02.modelos.ProductoModel;

import java.text.NumberFormat;
import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductoVH>{

    private List<ProductoModel> objects;
    private int resource;
    private Context context;
    private NumberFormat nf;


    public ProductosAdapter(List<ProductoModel> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
        nf = NumberFormat.getCurrencyInstance();
    }

    @NonNull
    @Override
    public ProductoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productoCardView = LayoutInflater.from(context).inflate(resource, null);
        productoCardView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ProductoVH(productoCardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoVH holder, int position) {
        ProductoModel p = objects.get(position);
        holder.lblNombre.setText(p.getNombre());
        holder.lblCantidad.setText(String.valueOf(p.getCantidad()));
        holder.lblPrecio.setText(nf.format(p.getPrecio()));

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDelete(p, holder.getAdapterPosition()).show();
            }
        });

    }


    private AlertDialog confirmDelete(ProductoModel p, int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("SEGURO?");
        builder.setCancelable(false);
        builder.setNegativeButton("CANCELAR", null);
        builder.setPositiveButton("ELIMINAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                objects.remove(p);
                notifyItemRemoved(position);
            }
        });

        return builder.create();
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ProductoVH extends RecyclerView.ViewHolder{

        TextView lblNombre;
        TextView lblPrecio;
        TextView lblCantidad;
        ImageButton btnEliminar;

        public ProductoVH(@NonNull View itemView) {
            super(itemView);
            lblNombre = itemView.findViewById(R.id.lblProductoCard);
            lblPrecio = itemView.findViewById(R.id.lblPrecioProductoCard);
            lblCantidad = itemView.findViewById(R.id.lblCantidadCard);
            btnEliminar = itemView.findViewById(R.id.btnEliminarProductoCard);


        }

    }
}
