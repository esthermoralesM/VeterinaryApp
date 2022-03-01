package cat.copernic.veterinaryapp.LlistaCites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.veterinaryapp.Objects.Cita
import cat.copernic.veterinaryapp.R

class LlistaCitesAdapter(private val clickListener: OnCitaClic) :
    RecyclerView.Adapter<LlistaCitesAdapter.LlistCitesHolder>() {

    private var dataList = mutableListOf<Cita>()

    fun setListData(data : MutableList<Cita>){
        dataList = data
    }

    interface OnCitaClic{
        fun onCitaClickAction(citaSelect : Cita)
    }

    inner class LlistCitesHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindView(element: Cita){
            val client: TextView = itemView.findViewById(R.id.campoCliente)
            val hora: TextView = itemView.findViewById(R.id.campoHora)

            client.text = element.client
            hora.text = element.hora

            itemView.setOnClickListener { clickListener.onCitaClickAction(element) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LlistCitesHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_cita, parent, false)
        return LlistCitesHolder(view)
    }

    override fun onBindViewHolder(holder: LlistCitesHolder, position: Int) {
        val cita = dataList[position]
        holder.bindView(cita)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}