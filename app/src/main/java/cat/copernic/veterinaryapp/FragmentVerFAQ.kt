package cat.copernic.veterinaryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.veterinaryapp.databinding.FragmentVerFAQBinding

class FragmentVerFAQ : Fragment() {

    private lateinit var adapter: LlistaFaqsAdapter
    private lateinit var binding: FragmentVerFAQBinding
    private val viewModel by lazy { ViewModelProviders.of(this).get(FaqsViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentVerFAQBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = LlistaFaqsAdapter()
        binding.LlistaFaqsView.layoutManager = LinearLayoutManager(context)
        binding.LlistaFaqsView.adapter = adapter
        observeData()
    }

    fun observeData(){
        viewModel.fetchFaqsData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}