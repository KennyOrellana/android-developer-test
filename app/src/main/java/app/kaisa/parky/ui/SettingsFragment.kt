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
    }
}