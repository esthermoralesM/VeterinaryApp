package cat.copernic.veterinaryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import cat.copernic.veterinaryapp.databinding.ActivityClienteBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ActivityCliente : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        //val binding = DataBindingUtil.setContentView<ActivityClienteBinding>(this, R.layout.activity_cliente)
        val binding: ActivityClienteBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_cliente)


        drawerLayout = binding.drawerLayoutUsr

        val navController = this.findNavController(R.id.myNavHostFragmentUsr)

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        NavigationUI.setupWithNavController(binding.navViewUsr, navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragmentUsr)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tanca_sesio, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Tanca_la_sesio -> {
                Firebase.auth.signOut()
                val toInit = Intent(this, MainActivity::class.java)
                startActivity(toInit)
                true
            }
            else -> false
        }
    }
}