package app.kaisa.parky.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import app.kaisa.parky.R
import app.kaisa.parky.ui.adapters.CarAdapter
import app.kaisa.parky.data.models.Car
import app.kaisa.parky.data.viewmodel.CarViewModel
import kotlinx.android.synthetic.main.fragment_inputs.*

class InputsFragment : Fragment(){
    private var carViewModel: CarViewModel? = null
    private lateinit var adapter: CarAdapter
    private val list = ArrayList<Car>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return View.inflate(context, R.layout.fragment_inputs, container);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initData()
    }

    private fun initData(){
        Handler().postDelayed({
            activity?.runOnUiThread {
                carViewModel?.addCar(Car("NA1534", 0))
                carViewModel?.addCar(Car("FA1534", 0))
                carViewModel?.addCar(Car("HA1534", 0))
                carViewModel?.addCar(Car("N5432", 1))
                carViewModel?.addCar(Car("JH33434", 2))
                carViewModel?.addCar(Car("M5678", 1))
                carViewModel?.addCar(Car("LK9834", 2))
                carViewModel?.addCar(Car("jh4242", 1))
                carViewModel?.addCar(Car("jg424", 2))
            }
        }, 1000)
    }

    private fun initUI(){
        recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = CarAdapter(list)

        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)


        carViewModel?.getCars()?.observe(viewLifecycleOwner, Observer {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })

        recycler_view.adapter = adapter
    }
}