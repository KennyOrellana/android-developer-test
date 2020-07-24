package app.kaisa.parky.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import app.kaisa.parky.R
import app.kaisa.parky.data.models.Car
import app.kaisa.parky.data.models.CarRecord
import app.kaisa.parky.data.viewmodel.CarViewModel
import app.kaisa.parky.ui.adapters.OutputsCarAdapter
import app.kaisa.parky.utils.CarListener
import kotlinx.android.synthetic.main.fragment_outputs.*

class OutputsFragment : Fragment(){
    private var carViewModel: CarViewModel? = null
    private lateinit var adapterOutputs: OutputsCarAdapter
    private val list = ArrayList<CarRecord>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_outputs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI(){
        val onClickListener = object : CarListener {
            override fun onClick(carRecord: CarRecord) {
                carViewModel?.checkoutCar(carRecord)
            }
        }

        recycler_view.layoutManager = LinearLayoutManager(context)
        adapterOutputs = OutputsCarAdapter(list, onClickListener)
        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)
        recycler_view.adapter = adapterOutputs
        carViewModel?.getCarsWithInputs()?.observe(viewLifecycleOwner, showCarsObserver) //Show data when start

        //Setup Search
        et_search.addTextChangedListener { query ->
            if(query.isNullOrEmpty()){
                carViewModel?.getCarsWithInputs()?.observe(viewLifecycleOwner, showCarsObserver)
            } else {
                carViewModel?.searchCarsWithInputs(query.toString())?.observe(viewLifecycleOwner, showCarsObserver)
            }
        }
    }

    private val showCarsObserver = Observer<List<CarRecord>> {
        list.clear()
        list.addAll(it)
        adapterOutputs.notifyDataSetChanged()
    }
}