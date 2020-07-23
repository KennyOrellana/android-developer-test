package app.kaisa.parky.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.kaisa.parky.R
import app.kaisa.parky.data.adapters.CarAdapter
import app.kaisa.parky.data.models.Car
import kotlinx.android.synthetic.main.fragment_inputs.*

class InputsFragment : Fragment(){
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
        list.add(Car("FAA2323", 0))
        list.add(Car("N11111", 1))
        list.add(Car("M1453", 2))
        initUI()
    }

    private fun initUI(){
        recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = CarAdapter(list)
        recycler_view.adapter = adapter
    }
}