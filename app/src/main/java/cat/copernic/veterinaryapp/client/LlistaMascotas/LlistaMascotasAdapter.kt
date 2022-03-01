package cat.copernic.veterinaryapp.client.LlistaMascotas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.veterinaryapp.R

class LlistaMascotasAdapter(private val clickListener: LlistaMascotasAdapter.OnUserClic) :
    RecyclerView.Adapter<LlistaMascotasAdapter.LlistMascotaViewHolder>() {

    private var dataList = mutableListOf<MascotaView>()

    fun setListData(data: MutableList<MascotaView>) {
        dataList = data
    }

    interface OnUserClic {
        fun onUserClickAction(mascota: MascotaView)
    }

    inner class LlistMascotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(element: MascotaView) {
            val nomMascota: TextView = itemView.findViewById(R.id.mascota_nom)
            val rassaMascota: TextView = itemView.findViewById(R.id.mascota_rassa)

            nomMascota.text = element.nombre
            rassaMascota.text = element.rassa

            itemView.setOnClickListener { clickListener.onUserClickAction(element) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LlistMascotaViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.view_mascota, parent, false)
        return LlistMascotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: LlistMascotaViewHolder, position: Int) {
        val user = dataList[position]
        holder.bindView(user)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}