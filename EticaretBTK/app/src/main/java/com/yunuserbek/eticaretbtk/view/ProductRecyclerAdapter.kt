package com.yunuserbek.eticaretbtk.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yunuserbek.eticaretbtk.R
import com.yunuserbek.eticaretbtk.model.Product
import kotlinx.android.synthetic.main.recycler_row.view.*

class ProductRecyclerAdapter(val producutList :List<Product>,private val listener:Listener) : RecyclerView.Adapter<ProductRecyclerAdapter.ProductHolder>() {

    class  ProductHolder(itemView:View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.itemView.productName.text = producutList.get(position).name
        holder.itemView.productPrice.text=producutList.get(position).price
        Glide.with(holder.itemView.context).load(producutList.get(position).url).into(holder.itemView.productImageview)
        holder.itemView.addbasketButton.setOnClickListener {
            Toast.makeText(it.context, "sepete eklendi ${producutList.get(position).name}", Toast.LENGTH_LONG).show()
            listener.onItemClick(producutList.get(position))
        }

    }

    override fun getItemCount(): Int {
       return producutList.size
    }
    interface Listener{
        fun onItemClick(product: Product)
    }
}