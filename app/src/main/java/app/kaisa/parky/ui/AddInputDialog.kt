package app.kaisa.parky.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import app.kaisa.parky.R
import app.kaisa.parky.data.db.ParkyDatabase
import app.kaisa.parky.data.models.Car
import app.kaisa.parky.data.viewmodel.CarViewModel
import kotlinx.android.synthetic.main.dialog_fragment_add_input.*


class AddInputDialog : DialogFragment() {
    private var carViewModel: CarViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_fragment_add_input, container, false)
        if (dialog != null && dialog!!.window != null) {
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carId = arguments?.getString("car_id")
        add_input_message.text ="Registrar entrada de ${carId}?"

        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        btn_dismiss.setOnClickListener { dismiss() }

        btn_add_input.setOnClickListener {
            dismiss()

            if(carId!=null) {
                val car = Car(carId, ParkyDatabase.CAR_TYPE_NON_RESIDENT)
                carViewModel?.addCar(car)
                carViewModel?.insertRecord(car)
            }
        }
    }
}