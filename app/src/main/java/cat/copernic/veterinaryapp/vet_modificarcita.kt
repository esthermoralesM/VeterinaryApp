package cat.copernic.veterinaryapp

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.veterinaryapp.administrador.HoraAdapter
import cat.copernic.veterinaryapp.databinding.FragmentVetModificarcitaBinding
import cat.copernic.veterinaryapp.vet_generardiagnosticos.Companion.horaSelec
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class vet_modificarcita : Fragment() {
    private lateinit var binding: FragmentVetModificarcitaBinding
    private lateinit var fecha: String
    var user = Firebase.auth.currentUser
    private val args by navArgs<vet_modificarcitaArgs>()
    val db = FirebaseFirestore.getInstance()

    var dia = 0
    var mes = 0
    var anyo = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVetModificarcitaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val horas = ArrayList<String>()
        horas.add("7:00")
        horas.add("7:30")
        horas.add("8:00")
        horas.add("8:30")
        horas.add("9:00")
        horas.add("9:30")
        horas.add("10:00")
        horas.add("10:30")
        horas.add("11:00")
        horas.add("11:30")
        horas.add("12:00")
        horas.add("12:30")
        horas.add("13:00")
        horas.add("13:30")
        horas.add("14:00")
        horas.add("14:30")

        val adapador = HoraAdapter(horas)
        binding.RecyHoras.layoutManager = LinearLayoutManager(context)
        binding.RecyHoras.adapter = adapador

        fecha = args.citaData.dia
        horaSelec = args.citaData.hora

        binding.modDia.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
            val dd : String = dayOfMonth.toString() + "/" + (month + 1) + "/" + year
            fecha = dd
        }

        binding.editClient.setText(args.citaData.client)
        binding.editMascota.setText(args.citaData.animal)

        binding.editClient.setOnClickListener {

        }

        binding.delCita.setOnClickListener{
            delCita()
        }
        binding.modCita.setOnClickListener{
            guardaDatos()
        }
        binding.genDiag.setOnClickListener {
            val args = vet_modificarcitaDirections.actionVetModificarcita2ToVetGenerardiag2(args.citaData)
            findNavController().navigate(args)
        }
    }

    fun guardaDatos(){
        val comprobar = Comprobaciones()
        var continua = true
        var mensage = ""

        if(!comprobar.contieneTexto(binding.editClient.text.toString())){
            mensage = "Introduzca cliente \n"
            continua = false
        }

        if(!comprobar.contieneTexto(binding.editClient.text.toString())){
            mensage = "Omble el camp animal"
            continua = false
        }

        if (continua){
            db.collection("Cita").document(fecha.replace("/","") + horaSelec).set(
                hashMapOf(
                    "Client" to binding.editClient.text.toString(),
                    "Veterinari" to user!!.email,
                    "Animal" to binding.editMascota.text.toString(),
                    "Fecha" to fecha,
                    "Hora" to horaSelec
                )
            )
        }else{
            missatgeEmergent(mensage)
        }

    }

    fun delCita(){
        db.collection("Cita").document(fecha.replace("/","") + horaSelec).delete()
    }

    fun missatgeEmergent(missatge: String) {
        val builder = android.app.AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage(missatge)
        builder.setPositiveButton("Aceptar") { dialog, which -> }
        val dialog: android.app.AlertDialog = builder.create()
        dialog.show()
    }
}