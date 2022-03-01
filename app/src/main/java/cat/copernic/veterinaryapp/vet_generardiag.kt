package cat.copernic.veterinaryapp

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import cat.copernic.veterinaryapp.dataBase.OperacionesDBFirebase_Diagnostico
import cat.copernic.veterinaryapp.databinding.FragmentVetGenerardiagBinding
import cat.copernic.veterinaryapp.modelos.Diagnostico
import cat.copernic.veterinaryapp.modelos.Perfil


/**
 * Fragmento generar diagnostico
 *
 */
class vet_generardiag : Fragment() {
    private lateinit var binding: FragmentVetGenerardiagBinding
    val args : vet_generardiagArgs by navArgs()

    /**
     * Veterinario se recuperara de sharedPreferences
     * ya que almacena el usuario logeado, en este caso el veterinario
     * la id de la cita por args al pulsar el boton
     * 
     *
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVetGenerardiagBinding.inflate(inflater, container, false)

        //habria que recuperar por args la id de la cita y idmascota o nombre propietario
        //TODO
        val id = args.citaDiad.dia.replace("/", "") + args.citaDiad.hora

        val email = recuperarDatosPreferences().toString()
        //Envia el mail para recuperar los datos del perfil
        Log.e("Jose",email.toString())


        binding.btnEnviar.setOnClickListener {
            Log.d(texto, "Click")
            //al hacer click
            //Guarda los datos actuales de los label, etc
            /**HAY QUE RECUPERAR DATOS DE IDVISITAS
             * y ID MASCOTA
             * */
            guardaDatos(email,id,"")
        }

        return binding.root
    }

    /**
     * Guardar datos editados
     * hay que pasar el veterinario, la id de la visita, i la id de la mascota
     */
    fun guardaDatos(veterinario: String, idVisita: String, idMascota: String){
        var diagnostico: Diagnostico = Diagnostico()
        val comprueba: Comprobaciones = Comprobaciones()
        var campoErroneo = false

        var diag = binding.editTextDiagnostico.text.toString()
        var medicamento = binding.editTextMedicamento.text.toString()



        if(!comprueba.contieneTexto(diag)){
            campoErroneo = true
        }else if(!comprueba.contieneTexto(medicamento)){
            campoErroneo = true
        }else{
            //No hay error llenar datos de la clase
            diagnostico.medicamento = medicamento
            diagnostico.diagnostico = diag
            diagnostico.veterinario = veterinario
            //La id sera la suma de la ide de la visita mas la id de la masdcota
            diagnostico.id = idVisita + "-" + idMascota
            diagnostico.paciente = idMascota
            //Falta proxima visita
            diagnostico.prox_visita = ""
            diagnostico.fecha = args.citaDiad.dia

            val opdb: OperacionesDBFirebase_Diagnostico = OperacionesDBFirebase_Diagnostico()
            //Guardar las modificaciones
            opdb.guardar(diagnostico)
        }
        //Si hay algun campo erroneo
        if (campoErroneo){
            missatgeEmergent("Error","Omple tots els camps correctament")
            campoErroneo = false //No es necesario, pero por si acaso
        }else{
            missatgeEmergent("Info","Diagnostic generat")
            binding.editTextDiagnostico.setText("")
            binding.editTextMedicamento.setText("")
        }

    }

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


