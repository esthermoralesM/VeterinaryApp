package cat.copernic.veterinaryapp.LlistaCites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cat.copernic.veterinaryapp.Objects.Cita
import com.google.firebase.firestore.FirebaseFirestore

class ReposCitesData {
    fun getUserData(diaSelec: String) : LiveData<MutableList<Cita>>{
        val mutableData = MutableLiveData<MutableList<Cita>>()
        FirebaseFirestore.getInstance().collection("Cita").get().addOnSuccessListener {doc ->
            val listData = mutableListOf<Cita>()
            for (document in doc){
                val dia = document.getString("Fecha")
                if(dia == diaSelec) {
                    val veterinari = document.getString("Veterinari")
                    val hora = document.getString("Hora")
                    val client = document.getString("Client")
                    val animal = document.getString("Animal")
                    val cita = Cita(veterinari, dia, hora!!, client!!, animal!!)
                    listData.add(cita)
                }
            }
            mutableData.value = listData
        }
        return mutableData
    }
}