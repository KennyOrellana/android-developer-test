package app.kaisa.parky.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.kaisa.parky.R
import app.kaisa.parky.data.models.CarType
import kotlinx.android.synthetic.main.item_filter.view.*
import kotlin.collections.ArrayList

class FiltersAdapter(private val list: ArrayList<CarType>, private val onChange: OnFiltersChange) : RecyclerView.Adapter<FiltersAdapter.CarTypeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarTypeViewHolder {
        return CarTypeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_filter, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CarTypeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class CarTypeViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(carType: CarType){
            itemView.toggle_filter.text = carType.name
            itemView.toggle_filter.textOn = carType.name
            itemView.toggle_filter.textOff = carType.name
            itemView.toggle_filter.isChecked = carType.isChecked

            itemView.toggle_filter.setOnCheckedChangeListener{ _ , isChecked ->
                carType.isChecked = isChecked
                onChange.callback()
            }
        }
    }

    interface OnFiltersChange{
        fun callback()
    }
}