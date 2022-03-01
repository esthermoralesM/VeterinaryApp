package cat.copernic.veterinaryapp.administrador.LlistaUsers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.veterinaryapp.R

class LlistaUsersAdapter(private val clickListener: OnUserClic) :
    RecyclerView.Adapter<LlistaUsersAdapter.LlistUserViewHolder>() {

    private var dataList = mutableListOf<UserView>()

    fun setListData(data : MutableList<UserView>){
        dataList = data
    }

    interface OnUserClic{
        fun onUserClickAction(usuari : UserView)
    }

    inner class LlistUserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindView(element: UserView){
            val nomUser: TextView = itemView.findViewById(R.id.usuari_nom)
            val rolUser: TextView = itemView.findViewById(R.id.usuari_rol)

            nomUser.text = element.nombre
            rolUser.text = element.rol

            itemView.setOnClickListener { clickListener.onUserClickAction(element) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LlistUserViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_users, parent, false)
        return LlistUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: LlistUserViewHolder, position: Int) {
        val user = dataList[position]
        holder.bindView(user)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}