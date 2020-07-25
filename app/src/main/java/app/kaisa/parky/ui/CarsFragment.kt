package app.kaisa.parky.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
        val onCheckedListener = object : FiltersAdapter.OnFiltersChange {
            override fun callback() {
                carViewModel?.searchCars(et_search.text.toString(), listFilters.filter { it.isChecked })?.observe(viewLifecycleOwner, showCarsObserver)
            }
        }

        rv_filter.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        adapterFilters = FiltersAdapter(listFilters, onCheckedListener)
        rv_filter.adapter = adapterFilters
        carViewModel?.getCarTypes()?.observe(viewLifecycleOwner, Observer {
            listFilters.clear()
            listFilters.addAll(it)
            adapterFilters.notifyDataSetChanged()
        })

        //Setup Search
        et_search.addTextChangedListener { query ->
            if(query.isNullOrEmpty()){
                carViewModel?.searchCars(
                    filters = listFilters.filter { it.isChecked }
                )?.observe(viewLifecycleOwner, showCarsObserver)
            } else {
                carViewModel?.searchCars(
                    query.toString(),
                    listFilters.filter { it.isChecked }
                )?.observe(viewLifecycleOwner, showCarsObserver)
            }
        }

        //Add Car
        iv_add_car.setOnClickListener {
            findNavController().navigate(R.id.add_car_dialog_fragment)
        }
    }

    private val showCarsObserver = Observer<List<Car>> {
        listCars.clear()
        listCars.addAll(it)
        adapterInputs.notifyDataSetChanged()
    }
}