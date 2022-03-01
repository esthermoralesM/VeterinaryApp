package cat.copernic.veterinaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import cat.copernic.veterinaryapp.dataBase.OperacionesDBFirebase_Perfil
import cat.copernic.veterinaryapp.databinding.ActivityMainBinding
import cat.copernic.veterinaryapp.databinding.ActivityRegistreBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class RegistreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistreBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        registreUsuari()
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        Log.i("user:", "" + currentUser)
        binding.idEmail.setText("")
        binding.idContrasenya.setText("")
        binding.idContraConfirm.setText("")

    }

    fun createUserWithEmailAndPassword(email: String, passwordd: String){

        mAuth!!.createUserWithEmailAndPassword(email, passwordd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Èxit", "createUserWithEmail:success")
                    val user = mAuth!!.currentUser
                    updateUI(user)
                    missatgeEmergent("Informació", "Usuari registrar")

                    //--Jose: Añado porcion de codigo para crear la db del usuario donde se almacena rol etc
                    val opdiag = OperacionesDBFirebase_Perfil()
                    if (user != null) {
                        Log.e("Jose",user.email.toString())
                        opdiag.crearPerfilUsuario(user.email.toString(),"")
                    }
                    //----
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Error", "createUserWithEmail:failure", task.exception)
                    missatgeEmergent("Error", "Error en l'autentificació")
                    updateUI(null)
                }

                // ...
            }

    }

    private fun registreUsuari(){

        binding.idRegistrarseBtn.setOnClickListener(){
            if(!binding.idEmail.text.isEmpty() && !binding.idContrasenya.text.isEmpty() && !binding.idContraConfirm.text.isEmpty()){
                if(binding.idContrasenya.text.toString().equals(binding.idContraConfirm.text.toString())){
                    if(binding.idContrasenya.text.toString().length>5){
                        createUserWithEmailAndPassword(binding.idEmail.text.toString(), binding.idContrasenya.text.toString())
                    }else{
                        missatgeEmergent("Error", "La contrasenya ha de contenir mínim 6 caràcters")
                    }
                }else{
                    missatgeEmergent("Error", "Les contrasenyes no són iguals")
                }
            }else{
                missatgeEmergent("Error", "S'han d'omplir tots els camps")
            }
        }
    }

    private fun missatgeEmergent(titol: String, missatge: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(titol)
        builder.setMessage(missatge)
        builder.setPositiveButton("Aceptar") { dialog, which ->
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}