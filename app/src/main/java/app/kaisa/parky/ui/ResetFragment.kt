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
import app.kaisa.parky.R
import app.kaisa.parky.ui.adapters.InputsCarAdapter
import app.kaisa.parky.data.models.Car
import app.kaisa.parky.data.models.CarRecord
import app.kaisa.parky.data.viewmodel.CarViewModel
import kotlinx.android.synthetic.main.fragment_outputs.*

class ResetFragment : Fragment(){
    private var carViewModel: CarViewModel? = null
    private lateinit var adapterInputs: InputsCarAdapter
    private val list = ArrayList<CarRecord>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reset, container, false)
    }

}