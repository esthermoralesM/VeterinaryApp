package cat.copernic.veterinaryapp.modelos

import java.util.*

/**
 * Clase para almacenar los datos de diagnostico
 */
data class Diagnostico(
    var fecha: String? = null,
    var id: String = "",
    var paciente: String = "",
    var diagnostico: String = "",
    var medicamento: String = "",
    var prox_visita: String?= null,
    var veterinario: String = ""
)