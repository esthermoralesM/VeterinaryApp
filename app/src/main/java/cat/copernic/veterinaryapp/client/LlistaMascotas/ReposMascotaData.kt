package cat.copernic.veterinaryapp.client.LlistaMascotas

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ReposMascotaData {
    fun getMascotaData() : LiveData<MutableList<MascotaView>> {
        val mutableData = MutableLiveData<MutableList<MascotaView>>()
        val user = FirebaseAuth.getInstance().currentUser?.email
        Log.e("TestLog", user.toString())
        FirebaseFirestore.getInstance().collection("Mascota").whereEqualTo("duenyo",user.toString()).get().addOnSuccessListener { doc ->
            val listData = mutableListOf<MascotaView>()
            for (document in doc){
                val nom = document.getString("nom")
                Log.e("TestLog", nom.toString())
                val rassa = document.getString("rassa")
                Log.e("TestLog", rassa.toString())
                val chip = document.getString("numero_chip")
                Log.e("TestLog", chip.toString())
                val mascota = MascotaView(nom!!,chip!!,rassa!!)
                listData.add(mascota)
            }
            mutableData.value = listData
        }
        return mutableData
    }



}