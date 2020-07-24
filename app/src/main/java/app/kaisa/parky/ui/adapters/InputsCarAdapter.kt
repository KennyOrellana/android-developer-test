package app.kaisa.parky.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.kaisa.parky.R
import app.kaisa.parky.data.models.Car
import kotlinx.android.synthetic.main.item_inputs_car.view.*
import java.util.*
import kotlin.collections.ArrayList

class InputsCarAdapter(private val list: ArrayList<Car>, private val carListener: InputsCarAdapter.CarListener) : RecyclerView.Adapter<InputsCarAdapter.CarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_inputs_car, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class CarViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(car: Car){
            itemView.tv_car_id.text = car.id.toUpperCase(Locale.ROOT)

            when(car.type){
                0 -> itemView.iv_car_icon.setImageResource(R.drawable.ic_business_outline) //Oficial
                1 -> itemView.iv_car_icon.setImageResource(R.drawable.ic_home_outline) //Residentes
                2 -> itemView.iv_car_icon.setImageResource(R.drawable.ic_people_outline) //Visitantes
                else -> itemView.iv_car_icon.setImageResource(R.drawable.ic_help_circle_outline) //Otros
            }

            itemView.setOnClickListener{
                carListener.onClick(car)
            }
        }
    }

    interface CarListener {
        fun onClick(car: Car)
    }
}