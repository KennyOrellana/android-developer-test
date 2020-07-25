package app.kaisa.parky.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.kaisa.parky.R
import app.kaisa.parky.data.models.Car
import app.kaisa.parky.data.viewmodel.CarViewModel
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment(){
    private var carViewModel: CarViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        btn_reset.setOnClickListener {
            carViewModel?.deleteRecordsOfficial()

            val bundle = bundleOf(
                "message" to "Los registros se ajustaron correctamente."
            )
            findNavController().navigate(R.id.checkout_dialog_message, bundle)
        }

        btn_pay.setOnClickListener {
            val bundle = bundleOf(
                "message" to "Lo Sentimos, \n Esta opción estará disponible en la próxima actualización."
            )
            findNavController().navigate(R.id.checkout_dialog_message, bundle)
        }

        btn_data.setOnClickListener {
            carViewModel?.addCars(PREPOPULATE_DATA_CAR)
        }
    }

    companion object {
        val PREPOPULATE_DATA_CAR = listOf(
            Car("M9876", 1),
            Car("FAA1234", 2),
            Car("HA3421", 1),
            Car("SDK5432", 2),
            Car("MHB5678", 2),
            Car("JDK9876", 1),
            Car("KLA1234", 2),
            Car("MMA3421", 1),
            Car("LKO5432", 2),
            Car("ADK5678", 2),
            Car("LKJ9834", 1)
        )
    }
}