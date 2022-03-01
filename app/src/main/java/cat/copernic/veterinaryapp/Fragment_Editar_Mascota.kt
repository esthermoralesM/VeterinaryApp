package cat.copernic.veterinaryapp

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import cat.copernic.veterinaryapp.dataBase.OperaciondBDfirebase_mascota

import cat.copernic.veterinaryapp.databinding.FragmentEditarMascotaBinding
import cat.copernic.veterinaryapp.modelos.Mascota
import com.google.firebase.firestore.FirebaseFirestore
import cat.copernic.veterinaryapp.Fragment_Editar_MascotaArgs
import cat.copernic.veterinaryapp.modelos.Perfil


/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_Editar_Mascota.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_Editar_Mascota : Fragment() {
    val args : Fragment_Editar_MascotaArgs by navArgs()
    private lateinit var binding: FragmentEditarMascotaBinding
    private val db = FirebaseFirestore.getInstance()
    lateinit var mascota: Mascota



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditarMascotaBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Recupera el chip enviado por safeargs
        var chip = args.chipMascota
        genui(chip)

        binding.btnModificarMascota.setOnClickListener{
            Log.e("JoseEDM","Click")
            guardaDatos()
        }


    }

    /**
     * Muestra en las vistas los datos de la mascota recuperados de la db
     * pasandole la id (el chip)
     */
    private fun genui(chip: String) {
        db.collection("Mascota").document(chip).get().addOnSuccessListener {
            binding.nombreMascota.setText(it.get("nom") as String?)
            binding.chipMascota.setText(it.get("numero_chip") as String?)
            binding.editTextFechaNacMascota.setText(it.get("data_naixement") as String?)
            binding.editTextNumberSigned.setText(it.get("pes") as String?)
            binding.editTextRaza.setText(it.get("rassa") as String?)
        }
    }

    private fun guardaDatos(){
        var mascotaMod = Mascota()
        val comprueba = Comprobaciones()
        var campoErroneo = false

        var nombre = binding.nombreMascota.text.toString()
        var fechaNac = binding.editTextFechaNacMascota.text.toString()
        //El chip no se modifica
        var peso = binding.editTextNumberSigned.text.toString()
        var raza = binding.editTextRaza.text.toString()
        var chip = binding.chipMascota.text.toString()



        if(!comprueba.contieneTexto(nombre)){
            campoErroneo = true
        }else if(!comprueba.validaFecha(fechaNac)){
            campoErroneo = true
        } else if (!comprueba.contieneTexto(peso)){ //Por modificar, peso valido
            campoErroneo = true
        } else if (!comprueba.contieneTexto(raza)){
            campoErroneo = true
        }else{
            //No hay error llenar datos de la clase
            mascotaMod.nom = nombre
            mascotaMod.pes = peso
            mascotaMod.dataNaixement = fechaNac
            mascotaMod.rassa = raza
            mascotaMod.numCgip = chip

            Log.e("JoseEDM", mascotaMod.numCgip.toString())
            Log.e("JoseEDM", mascotaMod.nom.toString())
            Log.e("JoseEDM", mascotaMod.pes)
            Log.e("JoseEDM", mascotaMod.dataNaixement.toString())
            Log.e("JoseEDM", mascotaMod.rassa.toString())
            val opdb: OperaciondBDfirebase_mascota = OperaciondBDfirebase_mascota()
            //Guardar las modificaciones
            opdb.modificar(mascotaMod)
        }
        //Si hay algun campo erroneo
        if (campoErroneo){
            missatgeEmergent("Error","Omple tots els camps correctament")
            campoErroneo = false //No es necesario, pero por si acaso
        }
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