package cat.copernic.veterinaryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.veterinaryapp.administrador.LlistaUsers.UserView
import cat.copernic.veterinaryapp.administrador.homeDirections
import cat.copernic.veterinaryapp.client.LlistaMascotas.LlistaMascotasAdapter
import cat.copernic.veterinaryapp.client.LlistaMascotas.MascotaView
import cat.copernic.veterinaryapp.client.LlistaMascotas.MascotasViewModel
import cat.copernic.veterinaryapp.databinding.FragmentHomeUserBinding


class homeUser : Fragment(), LlistaMascotasAdapter.OnUserClic{
    private lateinit var adapter: LlistaMascotasAdapter
    lateinit var binding: FragmentHomeUserBinding
    private val viewModel by lazy { ViewModelProviders.of(this).get(MascotasViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeUserBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = LlistaMascotasAdapter(this)
        binding.LlistaMascotasView.layoutManager = LinearLayoutManager(context)
        binding.LlistaMascotasView.adapter = adapter
        observeData()

        binding.floatingActionButton2.setOnClickListener{
            findNavController().navigate(R.id.action_homeUser_to_fragment_CliGestionMascotas2)
        }
    }


    fun observeData(){
        viewModel.fetchUsersData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onUserClickAction(mascota: MascotaView) {

        val arg = homeUserDirections.actionHomeUserToFragmentEditarMascota(mascota.chip)
        findNavController().navigate(arg)
    }

}