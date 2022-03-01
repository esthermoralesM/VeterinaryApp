package cat.copernic.veterinaryapp

import OperacionesDBPerfil
import com.google.firebase.firestore.FirebaseFirestore
import cat.copernic.veterinaryapp.modelos.Perfil

/**
 * Aqui esta el codigo para añadir a la db Firebase los perfiles
 *
 * Podemos utilizar esta clase para añadir
 */


class OperacionesDBFirebase_Perfil: OperacionesDBPerfil {
    private val db = FirebaseFirestore.getInstance()


    //Guarda en la base de datos en la tabla Perfil
    /*
        perfil.usuario almacena el email, y el hashamap tiene el resto de datos, de momento la imagen no esta...
        en formulario se deveria revisar mas adelante que no se pueda modificar usuario para que no pueda guardar ese campo con otro mail, porque eso crearia otro registro

     */

    override fun crear(perfil: Perfil): Boolean {
        db.collection("Perfil").document(perfil.usuario).set(
            hashMapOf(
                "rol" to perfil.rol,
                "apellidos"  to perfil.apellidos.toString() ,
                "direccion" to perfil.direccion.toString(),
                "dni" to perfil.dni.toString(),
                "fecha_nacimiento" to perfil.fecha_nac.toString(),
                "nombre" to perfil.nombre.toString(),
                "telefono" to perfil.telefono.toString(),
                "usuario" to perfil.usuario.toString()

            )
        )
        return true //De momento meramente decorativo, pero lo pide la interfaz, por si hay que capturar exceptions.
    }

    override fun guardar(perfil: Perfil): Boolean {
        db.collection("Perfil").document(perfil.usuario).set(
            hashMapOf(
                "apellidos"  to perfil.apellidos.toString() ,
                "direccion" to perfil.direccion.toString(),
                "dni" to perfil.dni.toString(),
                "fecha_nacimiento" to perfil.fecha_nac.toString(),
                "nombre" to perfil.nombre.toString(),
                "rol" to perfil.rol,
                "telefono" to perfil.telefono.toString(),
                "usuario" to perfil.usuario.toString()
            )
        )
        return true //De momento meramente decorativo, pero lo pide la interfaz, por si hay que capturar exceptions.
    }

    override fun eliminar(perfil: Perfil): Boolean {
        TODO("Not yet implemented")
    }

    override fun modificar(perfil: Perfil): Boolean {
        TODO("Modificar seria lo mismo que guardar pasando el Perfil de la db, El correo user en clase Perfil")
    }

    override fun buscar(perfil: Perfil): Perfil {
        TODO("Not yet implemented")
    }

}