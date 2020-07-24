package app.kaisa.parky.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.findNavController
import app.kaisa.parky.R
import app.kaisa.parky.data.repository.CarTypeSingleton
import app.kaisa.parky.data.viewmodel.CarViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private var carViewModel: CarViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)
        supportActionBar!!.hide() //Work around because tabs lib doesn't with NoActionBar Theme

        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        //Load CarTypes
        carViewModel?.getCarTypes()?.observe(this){
            CarTypeSingleton.carTypes.addAll(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_tabs,menu)
        bottom_bar.setupWithNavController(menu!!, navController)
        return true
    }
}