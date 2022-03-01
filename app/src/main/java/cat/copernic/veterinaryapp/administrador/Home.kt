package cat.copernic.veterinaryapp.administrador

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.veterinaryapp.R
import cat.copernic.veterinaryapp.administrador.LlistaUsers.LlistaUsersAdapter
import cat.copernic.veterinaryapp.administrador.LlistaUsers.UserView
import cat.copernic.veterinaryapp.administrador.LlistaUsers.UsersViewModel
import cat.copernic.veterinaryapp.databinding.FragmentHomeBinding
import cat.copernic.veterinaryapp.databinding.ViewUsersBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.EnumSet.of

class home : Fragment(), LlistaUsersAdapter.OnUserClic {

    private lateinit var adapter: LlistaUsersAdapter
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by lazy { ViewModelProviders.of(this).get(UsersViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = LlistaUsersAdapter(this)
        binding.LlistaUsuarisView.layoutManager = LinearLayoutManager(context)
        binding.LlistaUsuarisView.adapter = adapter
        observeData()

        binding.floatingActionButton2.setOnClickListener {
            findNavController().navigate(R.id.action_home_f_to_crear_perfil)
        }
    }

    override fun onUserClickAction(usuari : UserView) {
        val arg = homeDirections.actionHomeFToEditPerfil2(usuari.usuario)
        findNavController().navigate(arg)
    }

    fun observeData(){
        viewModel.fetchUsersData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}