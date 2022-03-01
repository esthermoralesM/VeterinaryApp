package cat.copernic.veterinaryapp.administrador.LlistaUsers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class ReposUserData {
    fun getUserData() : LiveData<MutableList<UserView>>{
        val mutableData = MutableLiveData<MutableList<UserView>>()
        FirebaseFirestore.getInstance().collection("Perfil").get().addOnSuccessListener {doc ->
            val listData = mutableListOf<UserView>()
            for (document in doc){
                val nombre = document.getString("nombre")
                val usuario = document.getString("usuario")
                val rol = document.getString("rol")
                val user = UserView(nombre!!, usuario!!, rol!!)
                listData.add(user)
            }
            mutableData.value = listData
        }
        return mutableData
    }
}