package com.faan.mascotass5;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by FAAN on 05/02/2017.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {



    public MascotaAdaptador (ArrayList <Mascota> mascotas, Activity activity, Context context){
        this.mascotas=mascotas;
        this.activity= activity;
        this.context = context;

    }

    ArrayList <Mascota> mascotas;
    private Activity activity;
    private Context context;
    @Override
    //inflar el layout y lo pasa al viewholder para que obtenga los view
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    // asocia cada elemento de la lista con cada view
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFotoMascota());
        mascotaViewHolder.tvNombreCV.setText(mascota.getNombreMascota());
        mascotaViewHolder.tvRaitingCV.setText(String.valueOf(mascota.getRaitingMascota()));


        mascotaViewHolder.btnRaitear.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void  onClick(View v) {
                ContructorMascotas constructor = new ContructorMascotas(context);
                constructor.darLikeMascota(mascota);

//                holder.countLikes.setText(String.valueOf(constructor.obtenerLikesMascota(mascota)));
                mascotaViewHolder.tvRaitingCV.setText(String.valueOf(constructor.obtenerLikesMascota(mascota)));
                // mascota.puntuar();
               // mascotaViewHolder.tvRaitingCV.setText(String.valueOf(mascota.getRaitingMascota()));
                Toast.makeText(activity ,"Diste un Like a  " + mascota.getNombreMascota(), Toast.LENGTH_SHORT ).show();
            }
        });


    }

    @Override
    public int getItemCount() { //cantidad elementos de la lista
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgFoto;
        private TextView tvNombreCV;
        private TextView tvRaitingCV;
        private ImageButton btnRaitear;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto     = (ImageView)   itemView.findViewById(R.id.imgFoto);
            tvNombreCV  = (TextView)    itemView.findViewById(R.id.tvNombreCV);
            tvRaitingCV = (TextView)    itemView.findViewById(R.id.tvRaitingCV);
            btnRaitear  = (ImageButton) itemView.findViewById(R.id.btnRaitear);
        }
    }




}
