package com.andersonj.bleach

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MultaAdapter(private val multaList : ArrayList<Multa>) : RecyclerView.Adapter<MultaAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_multa, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = multaList[position]

        holder.cedula.text = currentitem.cedula
        holder.placa.text = currentitem.placa
        holder.motivo.text = currentitem.motivo
        holder.comentario.text = currentitem.comentario
        holder.latitud.text = currentitem.latitud
        holder.longitud.text = currentitem.longitud
        holder.fecha.text = currentitem.fecha
        holder.hora.text = currentitem.hora

        Picasso.get().load(currentitem.foto).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return multaList.size
    }



    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cedula : TextView = itemView.findViewById(R.id.cedula)
        val placa : TextView = itemView.findViewById(R.id.placa)
        val motivo : TextView = itemView.findViewById(R.id.motivo)
        val comentario : TextView = itemView.findViewById(R.id.comentario)
        val latitud : TextView = itemView.findViewById(R.id.latitud)
        val longitud : TextView = itemView.findViewById(R.id.longitud)
        val fecha : TextView = itemView.findViewById(R.id.fecha)
        val hora : TextView = itemView.findViewById(R.id.hora)
        val imageView : ImageView = itemView.findViewById(R.id.foto)


    }

}