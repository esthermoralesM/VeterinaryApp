package cat.copernic.veterinaryapp

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.veterinaryapp.modelos.FAQS
import android.view.LayoutInflater

class LlistaFaqsAdapter():
    RecyclerView.Adapter<LlistaFaqsAdapter.LlistFaqsViewHolder>() {
    private var dataList = mutableListOf<FAQS>()

    fun setListData(data : MutableList<FAQS>){
        dataList = data
    }

    inner class LlistFaqsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindView(element: FAQS){
            val preguntaFaqs: TextView = itemView.findViewById(R.id.pregunta)
            val respostaFaqs: TextView = itemView.findViewById(R.id.resposta)

            preguntaFaqs.text = element.pregunta
            respostaFaqs.text = element.resposta
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LlistFaqsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_faqs, parent, false)
        return LlistFaqsViewHolder(view)
    }

    override fun onBindViewHolder(holder: LlistFaqsViewHolder, position: Int) {
        val faqs = dataList[position]
        holder.bindView(faqs)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}