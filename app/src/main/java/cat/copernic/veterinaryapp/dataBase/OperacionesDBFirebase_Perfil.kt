package cat.copernic.veterinaryapp.dataBase

import OperacionesDBPerfil
import android.util.Log
import cat.copernic.veterinaryapp.modelos.Perfil
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.lang.Exception

/**
 * Aqui esta el codigo para añadir a la db Firebase los perfiles
 *
 * Podemos utilizar esta clase para añadir
 */


class OperacionesDBFirebase_Perfil : OperacionesDBPerfil {
    private val db = FirebaseFirestore.getInstance()
    override fun crear(perfil: Perfil): Boolean {
        db.collection("Perfil").document(perfil.usuario.toString()).set(
            hashMapOf(
                "rol" to perfil.rol.toString(),
                "apellidos" to perfil.apellidos.toString(),
                "direccion" to perfil.direccion.toString(),
                "dni" to perfil.dni.toString(),
                "fecha_nacimiento" to perfil.fecha_nac.toString(),
                "nombre" to perfil.nombre.toString(),
                "telefono" to perfil.telefono.toString(),
                "usuario" to perfil.usuario.toString(),
                "rol" to perfil.rol.toString()
            )
        )
        return true
    }


    //Guarda en la base de datos en la tabla Perfil
    /*
        perfil.usuario almacena el email, y el hashamap tiene el resto de datos, de momento la imagen no esta...
        en formulario se deveria revisar mas adelante que no se pueda modificar usuario para que no pueda guardar ese campo con otro mail, porque eso crearia otro registro
     */
    override fun guardar(perfil: Perfil): Boolean {
        if (perfil.rol.equals("")) perfil.rol = "Client"
        db.collection("Perfil").document(perfil.usuario.toString()).set(
            hashMapOf(
                "rol" to perfil.rol.toString(),
                "apellidos" to perfil.apellidos.toString(),
                "direccion" to perfil.direccion.toString(),
                "dni" to perfil.dni.toString(),
                "fecha_nacimiento" to perfil.fecha_nac.toString(),
                "nombre" to perfil.nombre.toString(),
                "telefono" to perfil.telefono.toString(),
                "usuario" to perfil.usuario.toString(),
                "rol" to perfil.rol.toString()
            ), SetOptions.merge()
        ).isComplete
            return true //De momento meramente decorativo, pero lo pide la interfaz, por si hay que capturar exceptions.
    }

    override fun eliminar(perfil: Perfil): Boolean {
        TODO("Not yet implemented")
    }

    override fun modificar(perfil: Perfil): Boolean {
        TODO("Modificar seria lo mismo que guardar pasando el Perfil de la db, El correo user en clase Perfil")
    }

    /**
     * Se envia un perfil que minimo tenga el email 'usuario'
     *
     */
    override fun buscar(perfil: Perfil): Perfil {
        val email: String = perfil.usuario.toString() //almaceno el mail del usuario
        var perfilObtenido: Perfil =
            Perfil() //inicializo el perfil que almacenara el resultado, que sera unico
        db.collection("Perfil").document(email.toString()).get().addOnSuccessListener {
            if (it != null) {//Si existe el documento
                perfilObtenido.telefono = it.get("telefono") as String
                perfilObtenido.nombre = it.get("nombre") as String
                perfilObtenido.usuario = email.toString()
                perfilObtenido.apellidos = it.get("apellidos") as String
                //perfilObtenido.foto = it.get("foto") as String
                perfilObtenido.rol = it.get("rol") as String
                //perfilObtenido.fecha_nac = it.get("") as String
                perfilObtenido.dni = it.get("dni") as String
                perfilObtenido.direccion = it.get("direccion") as String

            } else {
                throw Exception("no existe")
            }

        }
        //perfilObtenido.nombre = "Jose"
        return perfilObtenido
    }

    fun crearPerfilUsuario(email: String, rol: String?){
        var elRol: String = ""

        if (rol != null){ //El campo rol tiene contenido
            //Si tiene asignado correctamente el rol se mantiene
            if (rol.equals("cliente") || rol.equals("veterinario") || rol.equals("admin"))
                elRol = rol
            else
                elRol = "cliente" //Si no se le da el rango mas bajo de serie por seguridad
        }else{
            elRol = "cliente"
        }

        val set = db.collection("Perfil").document(email).set(
            hashMapOf(
                "usuario" to email.toString(),
                "apellidos" to "",
                "direccion" to "",
                "dni" to "",
                "fecha_nacimiento" to "",
                "nombre" to "",
                "telefono" to "",
                "usuario" to "",
                "rol" to elRol.toString()
            )
        )
    }
}