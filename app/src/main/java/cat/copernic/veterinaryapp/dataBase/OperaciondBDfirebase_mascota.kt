package cat.copernic.veterinaryapp.dataBase

import android.util.Log
import cat.copernic.veterinaryapp.modelos.Mascota
import com.google.firebase.firestore.FirebaseFirestore

class OperaciondBDfirebase_mascota: OperacionsBDMascota {

    private val db = FirebaseFirestore.getInstance()
    override fun guardar(mascota: Mascota): Boolean {
        db.collection("Mascota").document(mascota.numCgip).set(
            hashMapOf(
                // "id"  to mascota.idMascota.toString() ,
                "nom" to mascota.nom.toString(),
                "rassa" to mascota.rassa.toString(),
                "data_naixement" to mascota.dataNaixement.toString(),
                "numero_chip" to mascota.numCgip.toString(),
                "pes" to mascota.pes.toString(),
                "duenyo" to mascota.duenyo.toString()
            )
        )
        return true
    }

    override fun eliminar(mascota: Mascota): Boolean {
        TODO("Not yet implemented")
    }

    override fun modificar(mascota: Mascota): Boolean {
        Log.e("JoseDB", mascota.dataNaixement.toString())
        Log.e("JoseDB", mascota.nom.toString())
        Log.e("JoseDB", mascota.pes.toString())
        Log.e("JoseDB", mascota.rassa.toString())
        db.collection("Mascota").document(mascota.numCgip).update(
            mapOf(
                "nom" to mascota.nom.toString(),
                "pes" to mascota.pes.toString(),
                "rassa" to mascota.rassa.toString(),
                "data_naixement" to  mascota.dataNaixement.toString()
            )
        )
        return true //De momento meramente decorativo, pero lo pide la interfaz, por si hay que capturar exceptions.    }
    }

    override fun buscar(mascota: Mascota): Boolean {
        TODO("Not yet implemented")
    }
}