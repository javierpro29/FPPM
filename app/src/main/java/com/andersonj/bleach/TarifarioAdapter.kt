package com.andersonj.bleach

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class TarifarioAdapter(private val tarifaList : ArrayList<Tarifa>) : RecyclerView.Adapter<TarifarioAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_tarifa, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = tarifaList[position]

        holder.nombre.text = currentitem.nombre
        holder.ley.text = currentitem.ley
        holder.monto.text = currentitem.monto
    }

    override fun getItemCount(): Int {
        return tarifaList.size
    }



    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nombre : TextView = itemView.findViewById(R.id.NombreTarifa)
        val ley : TextView = itemView.findViewById(R.id.LeyTarifa)
        val monto : TextView = itemView.findViewById(R.id.MontoTarifa)
    }

}