package cat.copernic.veterinaryapp

import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cat.copernic.veterinaryapp.databinding.FragmentChangeBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class Change : Fragment() {

    private lateinit var binding: FragmentChangeBinding

    var user = Firebase.auth.currentUser


    val profileUpdates = userProfileChangeRequest {
        displayName = ""

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener{
            user!!.updateEmail(binding.editTextTextEmailAddress.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "User email address updated.")
                        print("User email address updated.")
                    }
                }
            var newPassword = binding.editTextTextPassword.text.toString()
            var confirmPassword = binding.editTextTextPassword2.text.toString()

            if(newPassword.equals(confirmPassword)) {
                print("holas")
                user!!.updatePassword(newPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "User password updated.")
                        }
                    }
            }
        }
    }
}