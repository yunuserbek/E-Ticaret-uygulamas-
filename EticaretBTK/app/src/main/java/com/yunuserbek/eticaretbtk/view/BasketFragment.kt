package com.yunuserbek.eticaretbtk.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yunuserbek.eticaretbtk.R
import com.yunuserbek.eticaretbtk.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_basket.*


class BasketFragment : Fragment() {
    private val productViewModel:ProductViewModel by activityViewModels()
    private var basketRecyclerAdapter : BasketRecyclerAdapter?=null

    private val swipeCallBack = object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
           return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutposition = viewHolder.layoutPosition
            if (basketRecyclerAdapter!=null){
                val selectedproduct = basketRecyclerAdapter!!.basketList.get(layoutposition)
                productViewModel.deleteProductFromBasket(selectedproduct)
                basketRecyclerAdapter!!.notifyDataSetChanged()

            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basketrecyclerView.layoutManager =LinearLayoutManager(activity?.baseContext)
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(basketrecyclerView)

        productViewModel.basket.observe(viewLifecycleOwner, Observer {
            basketRecyclerAdapter = BasketRecyclerAdapter(it)
            basketrecyclerView.adapter = basketRecyclerAdapter
        })
        productViewModel.totalBasket.observe(viewLifecycleOwner, Observer {
            totalsepettextView.text = "Toplam sepet :${it}"
        })
    }


}