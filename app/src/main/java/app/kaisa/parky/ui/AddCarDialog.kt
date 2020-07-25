package app.kaisa.parky.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import app.kaisa.parky.R
import app.kaisa.parky.data.models.Car
import app.kaisa.parky.data.viewmodel.CarViewModel
import kotlinx.android.synthetic.main.dialog_fragment_add_car.*


class AddCarDialog : DialogFragment() {
    private var carViewModel: CarViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_fragment_add_car, container, false)
        if (dialog != null && dialog!!.window != null) {
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        et_add_car.addTextChangedListener { query ->
            btn_add.isEnabled = query!=null && query.isNotEmpty()
        }

        btn_add.setOnClickListener {
            val carId = et_add_car.text.toString()
            val carType = when(radio_group?.checkedRadioButtonId) {
                R.id.rb_oficial -> 1
                R.id.rb_resident -> 2
                else -> 3
            }
            carViewModel?.addCar(Car(carId, carType))

            dismiss()
        }
    }
}