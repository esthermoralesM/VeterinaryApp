package cat.copernic.veterinaryapp

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import cat.copernic.veterinaryapp.dataBase.OperacionsBDfirebase_Faqs
import cat.copernic.veterinaryapp.databinding.FragmentCrearLesFaqsBinding
import cat.copernic.veterinaryapp.modelos.FAQS
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class CrearLesFaqs : Fragment() {
    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private lateinit var binding: FragmentCrearLesFaqsBinding
    lateinit  var faqs : FAQS

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCrearLesFaqsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        faqs = FAQS()

        binding.faqs=faqs
        binding.butonGuardarBtnnn.setOnClickListener(){

            if(binding.pregunta .equals("")||binding.resposta .equals("")){
                //validacion()

                if (binding.pregunta.equals("")) {
                    binding.pregunta.setError("Required")
                } else if (binding.resposta.equals("")) {
                    binding.resposta.setError("Required")
                }

            } else {
                faqs.pregunta=binding.pregunta.text.toString()
                faqs.resposta=binding.resposta.text.toString()


                binding.pregunta.setText("")
                binding.resposta.setText("")
            }

            val opdiag =  OperacionsBDfirebase_Faqs()
            opdiag.guardar(faqs)

        }
        return binding.root
    }


}