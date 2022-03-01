package cat.copernic.veterinaryapp.administrador

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import cat.copernic.veterinaryapp.Comprobaciones
import cat.copernic.veterinaryapp.OperacionesDBFirebase_Perfil
import cat.copernic.veterinaryapp.databinding.FragmentEditPerfilBinding
import cat.copernic.veterinaryapp.modelos.Perfil
import com.google.firebase.firestore.FirebaseFirestore

class Editar_perfil : Fragment() {
    val args : edit_perfilArgs by navArgs()
    private val db = FirebaseFirestore.getInstance()
    private lateinit var binding: FragmentEditPerfilBinding
    lateinit var perfil : Perfil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = args.usuarioClave

        genUI(email)
        binding.buttonSave.setOnClickListener{
            guardaDatos()
        }
    }

    fun genUI(email: String) {

        db.collection("Perfil").document(email).get().addOnSuccessListener {
            val rol = it.get("rol") as String
            if (rol == "Administrador")
                binding.editRoles.setSelection(2)
            else if (rol == "Veterinari")
                binding.editRoles.setSelection(1)
            else if(rol == "Client")
                binding.editRoles.setSelection(0)

            binding.EditTextUsuari.setText(it.get("usuario") as String?)
            binding.EditTextNom.setText(it.get("nombre") as String?)
            binding.EditTextCognoms.setText(it.get("apellidos") as String?)
            binding.EditTextDataN.setText(it.get("fecha_nacimiento") as String?)
            binding.EditTextTel.setText(it.get("telefono") as String?)
            binding.EditTextDir.setText(it.get("direccion") as String?)
            binding.EditTextDni.setText(it.get("dni") as String?)
        }
    }

    fun guardaDatos(){
        perfil = Perfil()

        val comprobar = Comprobaciones()
        var mensage = ""
        val nounu = "finYyata"

        perfil.rol = binding.editRoles.selectedItem.toString()

        if (comprobar.contieneTexto(binding.EditTextNom.text.toString())) {
            perfil.nombre = binding.EditTextNom.text.toString()
            Log.d(nounu, "Nombre Correcto")

        }else{
            mensage = "Nombre incorrecto\n"
        }
        if (comprobar.validaFecha(binding.EditTextDataN.text.toString())){
            perfil.fecha_nac = binding.EditTextDataN.text.toString()
            Log.d(nounu, "Fecha correcta")
        }else
            mensage = "Fecha incorrecta"

        if (comprobar.validaCorreo(binding.EditTextUsuari.text.toString())) {
            perfil.usuario = binding.EditTextUsuari.text.toString()
            Log.d(nounu, "Correo Correcto")
        } else
            mensage = "Correo incorrecto\n"

        perfil.dni = binding.EditTextDni.text.toString() //El dni tampoco lo deberia modificar el usuario, unicamente recuperar la info

        if (comprobar.contieneTexto(binding.EditTextDir.text.toString())){ //Comprueba que tiene texto
            perfil.direccion = binding.EditTextDir.text.toString()
            Log.d(nounu, "DNI Bien")
        } else
            mensage = "Tiene que rellenar el campo direccion\n"

        if (comprobar.contieneTexto(binding.EditTextCognoms.text.toString())){ //Comprueba que tiene texto
            perfil.apellidos = binding.EditTextCognoms.text.toString()
            Log.d(nounu, "Apellidos Bien")
        } else
            mensage = "Tiene que rellenar el campo apellidos\n"

        //perfil.foto //clase foto por hacer
        if (comprobar.validarMovil(binding.EditTextTel.text.toString())){
            perfil.telefono = binding.EditTextTel.text.toString()
            Log.d(nounu, "Telefono Bien")
        }
        else
            mensage = "Telefono incorrecto\n"


        if (mensage.equals("")){
            val opdiag = OperacionesDBFirebase_Perfil()
            opdiag.crear(perfil)
            missatgeEmergent("Informació", "Usuari Modificat")
        }else{
            missatgeEmergent("Error", mensage)
        }
    }

    fun missatgeEmergent(titol: String, missatge: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(titol)
        builder.setMessage(missatge)
        builder.setPositiveButton("Aceptar") { dialog, which ->
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}