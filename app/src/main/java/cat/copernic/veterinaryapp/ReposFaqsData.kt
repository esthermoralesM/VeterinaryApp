package cat.copernic.veterinaryapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cat.copernic.veterinaryapp.administrador.LlistaUsers.UserView
import cat.copernic.veterinaryapp.modelos.FAQS
import com.google.firebase.firestore.FirebaseFirestore

class ReposFaqsData {
    fun getFaqsData() : LiveData<MutableList<FAQS>>{
        val mutableData = MutableLiveData<MutableList<FAQS>>()
        FirebaseFirestore.getInstance().collection("FAQS").get().addOnSuccessListener { doc ->
            val listData = mutableListOf<FAQS>()
            for (document in doc){
                val pregunta = document.getString("pregunra")
                val resposta = document.getString("resposta")
                val faqs = FAQS(pregunta!!, resposta!!)
                listData.add(faqs)
            }
            mutableData.value = listData
        }
        return mutableData
    }
}

