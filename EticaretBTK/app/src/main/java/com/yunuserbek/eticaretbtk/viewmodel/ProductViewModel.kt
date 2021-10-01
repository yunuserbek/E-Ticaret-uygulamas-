package com.yunuserbek.eticaretbtk.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunuserbek.eticaretbtk.model.Product
import com.yunuserbek.eticaretbtk.service.ProductaPI
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductViewModel :ViewModel() {
    private var  job : Job? = null
    val productList = MutableLiveData<List<Product>>()
    val basket = MutableLiveData<List<Product>>()
    val totalBasket = MutableLiveData<Int>()
     fun downloadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductaPI::class.java)

        job = viewModelScope.launch(context = Dispatchers.IO) {
            val response = retrofit.getData()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    response.body()?.let {
                        productList.value=it


                    }
                }
            }
        }



    }
    fun addToBasket(product:Product){
        if (basket.value!=null){
            val arrayList = ArrayList(basket.value)
            if (arrayList.contains(product)){
                val indexoffirst = arrayList.indexOfFirst { it==product }
                val relatedProduct = arrayList.get(indexoffirst)
                relatedProduct.count += 1
                basket.value=arrayList
            }else{
                product.count  += 1
                arrayList.add(product)
                basket.value=arrayList
            }


        }else{
            val arrayList = arrayListOf(product)
            product.count +=1
            basket.value=arrayList
        }
        basket.value.let {
            refreshTotalValue(it!!)
        }


    }
    private fun refreshTotalValue(ListOfProduct: List<Product>){
        var total =0
        ListOfProduct.forEach{product->
            val price = product.price.toIntOrNull()
            price?.let {
                val count = product.count
                val revenue = count* it
                total += revenue
            }
        }
        totalBasket.value =total

    }
    fun deleteProductFromBasket(product: Product){
        if (basket.value!=null){
            val arrayList = ArrayList(basket.value)
            arrayList.remove(product)
            basket.value =arrayList
            refreshTotalValue(arrayList)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}