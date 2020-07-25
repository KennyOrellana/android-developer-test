package app.kaisa.parky.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.kaisa.parky.R
import app.kaisa.parky.data.db.ParkyDatabase.Companion.CAR_TYPE_NON_RESIDENT
import app.kaisa.parky.data.db.ParkyDatabase.Companion.CAR_TYPE_OFICIAL
import app.kaisa.parky.data.db.ParkyDatabase.Companion.CAR_TYPE_RESIDENT
import app.kaisa.parky.data.models.Car
import app.kaisa.parky.data.repository.CarTypeSingleton
import app.kaisa.parky.utils.DateTime
import kotlinx.android.synthetic.main.item_cars.view.*
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

            val cost = CarTypeSingleton.getPrice(car.type)
            if(cost!=null) {
                itemView.tv_value_1.text = DateTime.formatMinutes(car.minutes)
            } else {
                itemView.tv_value_1.text = ""
            }

            itemView.tv_value_2.text = "Saldo Pendiente"
            itemView.tv_value_3.text = DateTime.currentDebt(car)

            when(car.type){
                CAR_TYPE_OFICIAL -> itemView.iv_car_icon.setImageResource(R.drawable.ic_business_outline)
                CAR_TYPE_RESIDENT -> itemView.iv_car_icon.setImageResource(R.drawable.ic_home_outline)
                CAR_TYPE_NON_RESIDENT -> itemView.iv_car_icon.setImageResource(R.drawable.ic_people_outline)
                else -> itemView.iv_car_icon.setImageResource(R.drawable.ic_help_circle_outline) //Otros
            }
        }
    }
}