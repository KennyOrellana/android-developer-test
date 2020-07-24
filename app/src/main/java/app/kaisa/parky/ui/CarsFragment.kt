package app.kaisa.parky.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.kaisa.parky.R
import app.kaisa.parky.data.models.Car
import app.kaisa.parky.data.models.CarType
import app.kaisa.parky.data.viewmodel.CarViewModel
import app.kaisa.parky.ui.adapters.CarsAdapter
import app.kaisa.parky.ui.adapters.FiltersAdapter
import app.kaisa.parky.ui.utils.VerticalDivider
import kotlinx.android.synthetic.main.fragment_cars.*
import kotlinx.android.synthetic.main.fragment_outputs.et_search
import kotlinx.android.synthetic.main.fragment_outputs.recycler_view


class CarsFragment : Fragment(){
    private var carViewModel: CarViewModel? = null
    private lateinit var adapterInputs: CarsAdapter
    private val listCars = ArrayList<Car>()
    private lateinit var adapterFilters: FiltersAdapter
    private val listFilters = ArrayList<CarType>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
//        initData()
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
        //Cars RecyclerView
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.addItemDecoration(VerticalDivider(context))
        adapterInputs = CarsAdapter(listCars)
        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)
        recycler_view.adapter = adapterInputs
        carViewModel?.getCars()?.observe(viewLifecycleOwner, showCarsObserver) //Show data when start

        //Filters RecyclerView
        listFilters.addAll(
            listOf(
                CarType(0, "Oficial",0.0, ""),
                CarType(1, "Residente Temporal",0.05, ""),
                CarType(2, "Visita",0.5, "")
            )
        )
        rv_filter.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        adapterFilters = FiltersAdapter(listFilters)
//        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)
        rv_filter.adapter = adapterFilters
//        carViewModel?.getCars()?.observe(viewLifecycleOwner, showCarsObserver) //Show data when start

        //Setup Search
        et_search.addTextChangedListener { query ->
            if(query.isNullOrEmpty()){
                carViewModel?.getCars()?.observe(viewLifecycleOwner, showCarsObserver)
            } else {
                carViewModel?.searchCars(query.toString())?.observe(viewLifecycleOwner, showCarsObserver)
            }
        }
    }

    private val showCarsObserver = Observer<List<Car>> {
        listCars.clear()
        listCars.addAll(it)
        adapterInputs.notifyDataSetChanged()
    }
}