package cat.copernic.veterinaryapp.administrador

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cat.copernic.veterinaryapp.Comprobaciones
import cat.copernic.veterinaryapp.OperacionesDBFirebase_Perfil
import cat.copernic.veterinaryapp.databinding.FragmentCrearPerfilBinding
import cat.copernic.veterinaryapp.modelos.Perfil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class crear_perfil : Fragment() {


    private lateinit var binding: FragmentCrearPerfilBinding
    private lateinit var mAuth: FirebaseAuth
    val formFecha1: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    lateinit var perfil : Perfil
    val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCrearPerfilBinding.inflate(inflater, container, false)
        mAuth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        perfil = Perfil()

        binding.botReg.setOnClickListener {
            val comprobar = Comprobaciones()
            var mensage = ""
            val nounu = "finYyata"

            perfil.rol = binding.roles.selectedItem.toString()

            if (comprobar.contieneTexto(binding.regNom.text.toString())) {
                perfil.nombre = binding.regNom.text.toString()
                Log.d(nounu, "Nombre Correcto")

            }else{
                mensage = "Nombre incorrecto\n"
            }
            if ((comprobar.validaFecha(binding.regFecha.text.toString()))){
                perfil.fecha_nac = binding.regFecha.text.toString()
                Log.d(nounu, "Fecha correcta")
            }else
                mensage = "Fecha incorrecto"

            if (comprobar.validaCorreo(binding.regCorreo.text.toString())) {
                perfil.usuario = binding.regCorreo.text.toString()
                Log.d(nounu, "Correo Correcto")
            } else
                mensage = "Correo incorrecto\n"

            if (comprobar.validaClave(binding.regPas.text.toString())) {
                if (binding.regPas.text.toString().equals(binding.regRepass.text.toString())){
                    Log.d(nounu, "Contraseñas Correctas\n ")
                } else
                    mensage = "Las contraseñas no coinciden\n "
            } else
                mensage = "Correo incorrecto\n"

            perfil.dni = binding.regDni.text.toString()

            if (comprobar.contieneTexto(binding.regDir.text.toString())){
                perfil.direccion = binding.regDir.text.toString()
                Log.d(nounu, "DNI Bien")
            } else
                mensage = "Tiene que rellenar el campo direccion\n"

            if (comprobar.contieneTexto(binding.regApe.text.toString())){ //Comprueba que tiene texto
                perfil.apellidos = binding.regApe.text.toString()
                Log.d(nounu, "Apellidos Bien")
            } else
                mensage = "Tiene que rellenar el campo apellidos\n"

            //perfil.foto //clase foto por hacer
            if (comprobar.validarMovil(binding.regTel.text.toString())){
                perfil.telefono = binding.regTel.text.toString()
                Log.d(nounu, "Telefono Bien")
            }
            else
                mensage = "Telefono incorrecto\n"


            if (mensage.equals("")){
                mAuth.createUserWithEmailAndPassword(binding.regCorreo.text.toString(), binding.regPas.text.toString())
                val opdiag = OperacionesDBFirebase_Perfil()
                opdiag.crear(perfil)
                missatgeEmergent("Informació", "Usuari registrat")
                updateUI()
            }else{
                missatgeEmergent("Error", mensage)
                mensage = ""
            }

        }
    }

    override fun onStart() {
        super.onStart()
        updateUI()
    }

    private fun updateUI() {
        binding.regCorreo.setText("")
        binding.regPas.setText("")
        binding.regRepass.setText("")
        binding.regDni.setText("")
        binding.regNom.setText("")
        binding.regApe.setText("")
        binding.regFecha.setText("")
        binding.regTel.setText("")
        binding.regDir.setText("")
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