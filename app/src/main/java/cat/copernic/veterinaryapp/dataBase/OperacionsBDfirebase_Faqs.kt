package cat.copernic.veterinaryapp.dataBase

import cat.copernic.veterinaryapp.modelos.FAQS
import cat.copernic.veterinaryapp.modelos.Mascota
import com.google.firebase.firestore.FirebaseFirestore

class OperacionsBDfirebase_Faqs : OperacionsDBFAQS {

    private val db = FirebaseFirestore.getInstance()

    override fun guardar(faqs: FAQS): Boolean {
        db.collection("FAQS").document(faqs.pregunta).set(
            hashMapOf(
                // "id"  to mascota.idMascota.toString() ,
                "pregunta" to faqs.pregunta,
                "resposta" to faqs.resposta,
            )
        )
        return true
    }

    override fun eliminar(faqs: FAQS): Boolean {
        TODO("Not yet implemented")
    }

    override fun modificar(faqs: FAQS): Boolean {
        TODO("Not yet implemented")
    }

    override fun buscar(faqs: FAQS): Boolean {
        TODO("Not yet implemented")
    }
}





