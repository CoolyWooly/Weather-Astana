package kz.weatherastana.app.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kz.weatherastana.app.BR.item
import kz.weatherastana.app.databinding.ItemDayBinding
import kz.weatherastana.app.domain.model.DayModel
import kz.weatherastana.app.ui.DataBindingViewHolder

class HomeAdapter(
    private var items: ArrayList<DayModel> = arrayListOf(),
    private var listener: OnDayClickListener
) : RecyclerView.Adapter<HomeAdapter.SimpleHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SimpleHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleHolder {
        val binding = ItemDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimpleHolder(binding)
    }

    inner class SimpleHolder(dataBinding: ViewDataBinding) :
        DataBindingViewHolder<DayModel>(dataBinding) {
        override fun onBind(t: DayModel): Unit = with(t) {
            dataBinding.setVariable(item, t)
            dataBinding.root.setOnClickListener {
                listener.onDayClick(t)
            }
        }
    }

    fun add(list: List<DayModel>) {
        items.addAll(list)
        notifyItemRangeInserted(0, list.count())
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    interface OnDayClickListener {
        fun onDayClick(dayModel: DayModel)
    }
}