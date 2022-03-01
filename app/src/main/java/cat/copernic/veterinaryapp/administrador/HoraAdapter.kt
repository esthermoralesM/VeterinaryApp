package cat.copernic.veterinaryapp.administrador

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.veterinaryapp.R
import cat.copernic.veterinaryapp.databinding.ContentItemHoraBinding
import cat.copernic.veterinaryapp.vet_generardiagnosticos

class HoraAdapter(var list: ArrayList<String>): RecyclerView.Adapter<HoraAdapter.ViewHolder>() {

    class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        fun bindItems(hora: String){
            val elemento: TextView=itemView.findViewById(R.id.hora_disp)
            elemento.text = hora
            itemView.setOnClickListener{
                vet_generardiagnosticos.horaSelec = hora
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val elemento = LayoutInflater.from(parent.context).inflate(R.layout.content_item_hora, parent,false)
        return ViewHolder(elemento)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}