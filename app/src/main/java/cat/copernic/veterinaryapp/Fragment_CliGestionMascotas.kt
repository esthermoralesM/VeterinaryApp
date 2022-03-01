package cat.copernic.veterinaryapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import cat.copernic.veterinaryapp.dataBase.OperaciondBDfirebase_mascota
import cat.copernic.veterinaryapp.databinding.FragmentCliGestionMascotasBinding
import cat.copernic.veterinaryapp.modelos.Mascota
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_CliGestionMascotas.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_CliGestionMascotas : Fragment() {
    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    lateinit var mascota : Mascota
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding= DataBindingUtil.inflate<FragmentCliGestionMascotasBinding>(layoutInflater, R.layout.fragment__cli_gestion_mascotas, container, false)
        // Inflate the layout for this fragment
        mascota = Mascota()
        binding.mascota=mascota
        binding.butonGuardarBtnnn.setOnClickListener(){

            if(binding.txtNombreMascota.equals("")||binding.txtRazaMascota.equals("")||binding.txtNacimientoMascota.equals("")||binding.editChipNumber.equals("")||
                binding.editTextPeso.equals("")){
                //validacion()

                if (binding.txtNombreMascota.equals("")) {
                    binding.txtNombreMascota.setError("Required")
                } else if (binding.txtRazaMascota.equals("")) {
                    binding.txtRazaMascota.setError("Required")
                } else if (binding.txtNacimientoMascota.equals("")) {
                    binding.txtNacimientoMascota.setError("Required")
                } else if (binding.editChipNumber.equals("")) {
                    binding.editChipNumber.setError("Required")
                }else if (binding.editTextPeso.equals("")){
                    binding.editTextPeso.setError("Required")
                }

            } else {
                mascota.nom=binding.txtNombreMascota.text.toString()
                mascota.rassa=binding.txtRazaMascota.text.toString()
                mascota.dataNaixement=binding.txtNacimientoMascota.text.toString()
                mascota.numCgip=binding.editChipNumber.text.toString()
                mascota.pes=binding.editTextPeso.text.toString()
                mascota.duenyo = recuperarDatosPreferences().toString()

                binding.txtNombreMascota.setText("")
                binding.txtRazaMascota.setText("")
                binding.txtNacimientoMascota.setText("")
                binding.editChipNumber.setText("")
                binding.editTextPeso.setText("")

            }

            val opdiag = OperaciondBDfirebase_mascota()
            opdiag.guardar(mascota)

        }
        return binding.root


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


}