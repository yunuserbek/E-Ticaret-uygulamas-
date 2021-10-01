package com.yunuserbek.eticaretbtk.service

import com.yunuserbek.eticaretbtk.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductaPI {

    @GET("atilsamancioglu/BTK23-DataSet/main/products.json")
    suspend fun getData():Response<List<Product>>
}