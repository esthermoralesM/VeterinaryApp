package cat.copernic.veterinaryapp.modelos

import android.media.Image
import java.util.*

/**
 * data Class Kotlin del tipo cat.copernic.veterinaryapp.modelos.Perfil para almacenar los datos de perfil de usuarios de la aplicación
 * @nombre, Nombre del perfil
 * @apellidos, Apellidos del Perfil
 * @telefono, Telefono del perfil
 * @fecha_nac, fecha de nacimiento en formato Date puede ser null
 * @usuario, email con el registro del usuario, es el que se utiliza en Firebase
 * @dni, DNI del perfil
 * @rol, administrador, cliente o veterinario
 * @foto, imagen del usuario *por definir si es el formato correcto
 * @direccion, direccion del perfil
 *
 */
data class Perfil(
    var nombre: String = "",
    var apellidos: String = "",
    var telefono: String = "",
    var fecha_nac: String = "",
    var usuario: String = "",
    var dni: String = "",
    var rol: String = "Client", //default, el rango mas bajo
    var foto: Image? = null,
    var direccion: String = ""
)