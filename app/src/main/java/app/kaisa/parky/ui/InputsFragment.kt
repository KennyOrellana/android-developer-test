package app.kaisa.parky.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.kaisa.parky.R
import app.kaisa.parky.data.db.ParkyDatabase
import app.kaisa.parky.ui.adapters.InputsCarAdapter
import app.kaisa.parky.data.models.CarRecord
import app.kaisa.parky.data.viewmodel.CarViewModel
import app.kaisa.parky.utils.CarListener
import kotlinx.android.synthetic.main.fragment_inputs.*

class InputsFragment : Fragment(){
    private var carViewModel: CarViewModel? = null
    private lateinit var adapterInputs: InputsCarAdapter
    private val list = ArrayList<CarRecord>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inputs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI(){
        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        val onClickListener = object : CarListener {
            override fun onClick(carRecord: CarRecord) {
                val bundle = bundleOf(
                    "car_id" to carRecord.car.idCar,
                    "car_type" to carRecord.car.type
                )
                findNavController().navigate(R.id.add_input_dialog_fragment, bundle)
            }
        }

        recycler_view.layoutManager = LinearLayoutManager(context)
        adapterInputs = InputsCarAdapter(list, onClickListener)

        recycler_view.adapter = adapterInputs
        carViewModel?.getCarsWithoutInputs()?.observe(viewLifecycleOwner, showCarsObserver) //Show data when start
        
        //Setup Search
        et_search.addTextChangedListener { query ->
            if(query.isNullOrEmpty()){
                ll_add.visibility = View.GONE
                carViewModel?.getCarsWithoutInputs()?.observe(viewLifecycleOwner, showCarsObserver)
            } else {
                tv_add.text = "Registrar entrada de ${et_search.text.toString()} como no residente"
                ll_add.visibility = View.VISIBLE
                carViewModel?.searchCarsWithoutInputs(query.toString())?.observe(viewLifecycleOwner, showCarsObserver)
            }
        }

        //Add
        ll_add.setOnClickListener {
            ll_add.isEnabled = false
            val bundle = bundleOf(
                "car_id" to et_search.text.toString(),
                "car_type" to ParkyDatabase.CAR_TYPE_NON_RESIDENT
            )
            et_search.text.clear()
            et_search.text.append("")
            findNavController().navigate(R.id.add_input_dialog_fragment, bundle)
            ll_add.isEnabled = true
        }
    }

    private val showCarsObserver = Observer<List<CarRecord>> {
        list.clear()
        list.addAll(it)
        adapterInputs.notifyDataSetChanged()
    }
}