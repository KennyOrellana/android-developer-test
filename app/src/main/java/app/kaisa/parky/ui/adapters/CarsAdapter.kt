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

class CarsAdapter(private val list: ArrayList<Car>) : RecyclerView.Adapter<CarsAdapter.CarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cars, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class CarViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(car: Car){
            itemView.tv_car_id.text = car.idCar.toUpperCase(Locale.ROOT)

            when(car.type){
                1 -> itemView.iv_car_icon.setImageResource(R.drawable.ic_business_outline) //Oficial
                2 -> itemView.iv_car_icon.setImageResource(R.drawable.ic_home_outline) //Residentes
                3 -> itemView.iv_car_icon.setImageResource(R.drawable.ic_people_outline) //Visitantes
                else -> itemView.iv_car_icon.setImageResource(R.drawable.ic_help_circle_outline) //Otros
            }
        }
    }
}