package cat.copernic.veterinaryapp

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.veterinaryapp.LlistaCites.CitesViewModel
import cat.copernic.veterinaryapp.LlistaCites.LlistaCitesAdapter
import cat.copernic.veterinaryapp.Objects.Cita
import cat.copernic.veterinaryapp.databinding.FragmentVetVercitasBinding
import java.sql.Time
import java.time.LocalDate

class vet_vercitas : Fragment(), LlistaCitesAdapter.OnCitaClic {

    private lateinit var binding: FragmentVetVercitasBinding
    private lateinit var adapter : LlistaCitesAdapter
    private val viewModel by lazy { ViewModelProviders.of(this).get(CitesViewModel::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVetVercitasBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var fecha = ""
        val dateact = LocalDate.now()

        fecha = dateact.dayOfMonth.toString() + "/" + dateact.monthValue + "/" + dateact.year

        //Calendario
        binding.calendarView2.setOnDateChangeListener{ calendarView, i, i2, i3 ->
            fecha = "" + i3 + "/" + (i2 + 1) + "/" + i
            observeData(fecha)
        }

        //crear RecyclerView de citas
        adapter = LlistaCitesAdapter(this)
        binding.llistaCites.layoutManager = LinearLayoutManager(context)
        binding.llistaCites.adapter = adapter
        observeData(fecha)

        binding.button2.setOnClickListener {
            findNavController().navigate(R.id.action_vet_vercitas_to_vet_generardiagnostic)
        }
    }

    override fun onCitaClickAction(citaSelect: Cita) {
        val argument = vet_vercitasDirections.actionVetVercitasToVetModificarcita2(citaSelect)
        findNavController().navigate(argument)
    }

    fun observeData(valor: String){
        viewModel.fetchUsersData(valor).observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}