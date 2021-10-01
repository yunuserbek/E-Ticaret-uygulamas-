package com.yunuserbek.eticaretbtk.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yunuserbek.eticaretbtk.R
import com.yunuserbek.eticaretbtk.model.Product
import kotlinx.android.synthetic.main.basket_recycler_row.view.*

class BasketRecyclerAdapter(val basketList:List<Product>) : RecyclerView.Adapter<BasketRecyclerAdapter.BasketViewHolder>() {

    class BasketViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.basket_recycler_row,parent,false)
        return BasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.itemView.basketproductnametext.text=basketList.get(position).name
        holder.itemView.basketproductpricetext.text=basketList.get(position).price
        holder.itemView.basketproductcounttext.text = "adet: ${basketList.get(position).count}"
        Glide.with(holder.itemView.context).load(basketList.get(position).url).into(holder.itemView.basketImageView)
    }

    override fun getItemCount(): Int {
        return basketList.size
    }


}