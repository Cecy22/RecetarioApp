package com.example.garey.recetario;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolderRecycler> implements ItemClickListener {

    private final Context context;
    private List<DtoReceta> datos;

    public Adaptador(Context context, List<DtoReceta> datos) {
        this.context = context;
        this.datos = datos;
    }

    @Override
    public ViewHolderRecycler onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.receta_individual, parent, false);
        return new ViewHolderRecycler(v, this);

    }

    @Override
    public void onBindViewHolder(ViewHolderRecycler holder, int position) {
        DtoReceta dtoReceta = datos.get(position);
        holder.titulo.setText(dtoReceta.getNombre());
        //holder.Valor.setText(dtoReceta.getValoracion());
        holder.val.setRating(Float.parseFloat(dtoReceta.getValoracion()));
        holder.Tipo.setText(dtoReceta.getTipo());
        if (dtoReceta.checkImage())
            holder.imagen.setImageDrawable(
                    ContextCompat.getDrawable(context,
                            dtoReceta.getImagen()));
        else
            Picasso.with(context).load(dtoReceta.getPathImg())
                    .into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    @Override
    public void onItemClick(View v, int pos) {
        VerReceta.createInstance((Activity) context, datos.get(pos));

    }

    public static class ViewHolderRecycler extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        @BindView(R.id.txtnombre)
        TextView titulo;

        @BindView(R.id.txtTipo)
        TextView Tipo;

        @BindView(R.id.imgReceta)
        ImageView imagen;

        @BindView(R.id.ratingBar)
        RatingBar val;


        public ItemClickListener click;

        public ViewHolderRecycler(View v, ItemClickListener listener) {
            super(v);
            ButterKnife.bind(this, v);
            this.click = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            click.onItemClick(view, getAdapterPosition());
        }
    }
}

interface ItemClickListener {
    void onItemClick(View v, int pos);
}
