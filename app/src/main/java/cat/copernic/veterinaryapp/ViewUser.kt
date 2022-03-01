package cat.copernic.veterinaryapp


import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cat.copernic.veterinaryapp.databinding.FragmentViewUserBinding
import cat.copernic.veterinaryapp.modelos.Perfil
import com.google.firebase.firestore.FirebaseFirestore

val texto: String = "JoseLog"

class ViewUser : Fragment() {
    private lateinit var binding: FragmentViewUserBinding
    private val db = FirebaseFirestore.getInstance()
    lateinit var perfil : Perfil


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Recupera los datos del usuario logueado
        val email = recuperarDatosPreferences().toString()
        //Envia el mail para recuperar los datos del perfil
        Log.e("Jose",email.toString())
        genUI(email)

        binding.buttonSave.setOnClickListener {
            Log.d(texto, "Click")
            //al hacer click
            //Guarda los datos actuales de los label, etc
            guardaDatos()
        }

        binding.buttonChngPass.setOnClickListener{
            findNavController().navigate(R.id.action_viewUser_to_change)
        }
    }

    /**
     * Recupera los datos de la base de datos y envia a cada campo correspondiente del fragment para mostrar los datos
     */
    fun genUI(email: String) {
        db.collection("Perfil").document(email).get().addOnSuccessListener {
            //llenar los campos correspondientes del fragment con los datos recuperados de la db
            binding.lvlRol.setText(it.get("rol") as String?)
            binding.EditTextUsuari.setText(it.get("usuario") as String?)
            binding.EditTextNom.setText(it.get("nombre") as String?)
            binding.EditTextCognoms.setText(it.get("apellidos") as String?)
            binding.EditTextDataN.setText(it.get("fecha_nacimiento") as String?)
            binding.EditTextTel.setText(it.get("telefono") as String?)
            binding.EditTextDir.setText(it.get("direccion") as String?)
            binding.EditTextDni.setText(it.get("dni") as String?)
        }
    }

    /**
     * Guardar datos editados
     */
    fun guardaDatos(){
        var perfilMod: Perfil = Perfil()
        val comprueba: Comprobaciones = Comprobaciones()
        var campoErroneo = false

        var nombre = binding.EditTextNom.text.toString()
        var direccion = binding.EditTextDir.text.toString()
        var usr = binding.EditTextUsuari.text.toString()
        var suRol = binding.lvlRol.text.toString()
        var apellido = binding.EditTextCognoms.text.toString()
        var dni = binding.EditTextDni.text.toString()
        var fechaN = binding.EditTextDataN.text.toString()
        var telefono = binding.EditTextTel.text.toString()


        if(!comprueba.contieneTexto(nombre)){
            campoErroneo = true
        }else if(!comprueba.contieneTexto(direccion)){
            campoErroneo = true
        } else if (!comprueba.contieneTexto(usr)){
            campoErroneo = true
        } else if (!comprueba.contieneTexto(suRol)){
            campoErroneo = true
        } else if (!comprueba.contieneTexto(apellido)){
            campoErroneo = true
        } else if (!comprueba.contieneTexto(dni)){
            //Por hacer metodo valida dni
            campoErroneo = true
        } else if (!comprueba.validaFecha(fechaN)){
            campoErroneo = true
        } else if (!comprueba.validarMovil(telefono)){
            campoErroneo = true
        }else{
            //No hay error llenar datos de la clase
            perfilMod.apellidos = apellido
            perfilMod.direccion = direccion
            perfilMod.dni = dni
            perfilMod.fecha_nac = fechaN
            perfilMod.nombre = nombre
            perfilMod.telefono = telefono
            perfilMod.rol = suRol
            perfilMod.usuario = usr

            val opdb: OperacionesDBFirebase_Perfil = OperacionesDBFirebase_Perfil()
            //Guardar las modificaciones
            opdb.guardar(perfilMod)
        }
        //Si hay algun campo erroneo
        if (campoErroneo){
            missatgeEmergent("Error","Omple tots els camps correctament")
            campoErroneo = false //No es necesario, pero por si acaso
        }

    }
    /**
     * Recuperar los datos del cliente
     *
     */
    fun recuperarDatosPreferences(): String? {
        val preferencias: SharedPreferences? =
            this.activity?.getSharedPreferences("credenciales", Context.MODE_PRIVATE)
        val emailSP: String? = preferencias?.getString("email","Sin datos")
        return emailSP
    }

    /**
     * Mensaje emergente
     * Pasar titulo y mensaje a mostrar
     */
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