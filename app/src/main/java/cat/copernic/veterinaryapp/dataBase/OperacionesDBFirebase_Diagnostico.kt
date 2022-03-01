package cat.copernic.veterinaryapp.dataBase

import cat.copernic.veterinaryapp.modelos.Diagnostico
import com.google.firebase.firestore.FirebaseFirestore

class OperacionesDBFirebase_Diagnostico : OperacionesDBDiagnostico {
    private val db = FirebaseFirestore.getInstance()

    override fun guardar(diagnostico: Diagnostico): Boolean {
        //Sin probar
        db.collection("diagnostico").document(diagnostico.id).set(
            hashMapOf(
                "fecha" to diagnostico.fecha.toString(),
                "id" to diagnostico.id.toString(),
                "paciente" to diagnostico.paciente.toString(),
                "prospecto" to diagnostico.diagnostico.toString(), //Prospecto = diagnostico?
                "medicamento" to diagnostico.medicamento.toString(), //Campo añadido
                "prox_visita" to diagnostico.prox_visita.toString(),
                "veterinario" to diagnostico.veterinario.toString()
            )
        )
        return true //De momento meramente decorativo, pero lo pide la interfaz, por si hay que capturar exceptions.
    }

    override fun eliminar(diagnostico: Diagnostico): Boolean {
        TODO("Not yet implemented")
    }

    override fun modificar(diagnostico: Diagnostico): Boolean {
        TODO("Not yet implemented")
    }

    override fun buscar(diagnostico: Diagnostico): Boolean {
        TODO("Not yet implemented")
    }
}