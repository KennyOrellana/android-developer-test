package app.kaisa.parky.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.kaisa.parky.R
import app.kaisa.parky.data.db.ParkyDatabase
import app.kaisa.parky.data.db.ParkyDatabase.Companion.CAR_TYPE_NON_RESIDENT
import app.kaisa.parky.data.db.ParkyDatabase.Companion.CAR_TYPE_OFICIAL
import app.kaisa.parky.data.db.ParkyDatabase.Companion.CAR_TYPE_RESIDENT
import app.kaisa.parky.data.models.CarRecord
import app.kaisa.parky.utils.CarListener
import app.kaisa.parky.utils.DateTime
import kotlinx.android.synthetic.main.item_inputs_car.view.*
import java.util.*
import kotlin.collections.ArrayList

class OutputsCarAdapter(private val list: ArrayList<CarRecord>, private val carListener: CarListener) : RecyclerView.Adapter<OutputsCarAdapter.CarViewHolder>() {
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
        fun bind(item: CarRecord){
            itemView.tv_car_id.text = item.car.idCar.toUpperCase(Locale.ROOT)
            val inputDate = "Entró ${DateTime.millisToDate(item)}"
            itemView.tv_value_1.text = inputDate
            itemView.tv_value_2.text = DateTime.elapsedTime(item)
            itemView.tv_value_3.text = DateTime.calculateAmount(item)

            when(item.car.type){
                CAR_TYPE_OFICIAL -> itemView.iv_car_icon.setImageResource(R.drawable.ic_business_outline)
                CAR_TYPE_RESIDENT -> itemView.iv_car_icon.setImageResource(R.drawable.ic_home_outline)
                CAR_TYPE_NON_RESIDENT -> itemView.iv_car_icon.setImageResource(R.drawable.ic_people_outline)
                else -> itemView.iv_car_icon.setImageResource(R.drawable.ic_help_circle_outline) //Otros
            }

            itemView.setOnClickListener{
                carListener.onClick(item)
            }
        }
    }
}