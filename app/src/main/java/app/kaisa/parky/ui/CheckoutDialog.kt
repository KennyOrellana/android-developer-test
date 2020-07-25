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
import app.kaisa.parky.data.models.CarRecord
import app.kaisa.parky.data.models.Record
import app.kaisa.parky.data.viewmodel.CarViewModel
import app.kaisa.parky.utils.DateTime
import kotlinx.android.synthetic.main.dialog_fragment_checkout.*


class CheckoutDialog : DialogFragment() {
    private var carViewModel: CarViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_fragment_checkout, container, false)
        if (dialog != null && dialog!!.window != null) {
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carId = arguments?.getString("car_id") ?: ""
        val carType = arguments?.getInt("car_type") ?: 0
        val minutes = arguments?.getInt("minutes") ?: 0
        val dateId = arguments?.getInt("date_id") ?: 0
        val dateInput = arguments?.getLong("date_input") ?: 0
        val car = Car(carId, carType, minutes)
        val carRecord = CarRecord(car, Record(dateId, carId, dateInput, null))

        var message = "Registrar salida de ${carId}?"

        if(carType == ParkyDatabase.CAR_TYPE_NON_RESIDENT) {
            message += "\n \n Total a pagar ${DateTime.calculateAmount(carRecord)} \n"
        }

        add_input_message.text = message

        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        btn_add_input.setOnClickListener {
            it.isEnabled = false
            carViewModel?.checkoutCar(carRecord)
            if(carType != ParkyDatabase.CAR_TYPE_OFICIAL && carType != ParkyDatabase.CAR_TYPE_RESIDENT) {
                carViewModel?.deleteCar(car)
            }
            dismiss()
        }

        btn_dismiss.setOnClickListener { dismiss() }
    }
}