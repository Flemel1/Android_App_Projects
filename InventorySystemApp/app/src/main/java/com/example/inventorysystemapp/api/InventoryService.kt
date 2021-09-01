package com.example.inventorysystemapp.api

import com.example.inventorysystemapp.model.*
import retrofit2.Response
import retrofit2.http.*

interface InventoryService {
    @GET("/Inventory%20System/api/fetchApi/getAllStock.php")
    suspend fun getAllStock(): Response<ResponseGetAllStock>

    @GET("/Inventory%20System/api/fetchApi/getAllUser.php")
    suspend fun getAllUser(): Response<ResponseAllUser>

    @FormUrlEncoded
    @POST("/Inventory%20System/api/fetchApi/Login.php")
    suspend fun userLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<ResponseRoleStatus>

    @GET("/Inventory%20System/api/fetchApi/getAllIncome.php")
    suspend fun getCurrentIncome(): Response<ResponseCurrentIncome>

    @GET("/Inventory%20System/api/fetchApi/getAllOutcome.php")
    suspend fun getCurrentOutcome(): Response<ResponseCurrentOutcome>

    @FormUrlEncoded
    @POST("/Inventory%20System/api/fetchApi/InsertItem.php")
    suspend fun insertStockItem(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("stock") stock: String
    ): Response<ResponseMessage>

    @GET("/Inventory%20System/api/fetchApi/getDetailIncome.php")
    suspend fun getAllIncome() : Response<ResponseDetailIncome>

    @GET("/Inventory%20System/api/fetchApi/getDetailOutcome.php")
    suspend fun getAllOutcome() : Response<ResponseDetailOutcome>

    @FormUrlEncoded
    @POST("/Inventory%20System/api/fetchApi/InsertIncome.php")
    suspend fun insertIncome(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("stock") stock: String,
        @Field("penerima") penerima: String
    ): Response<ResponseMessage>

    @FormUrlEncoded
    @POST("/Inventory%20System/api/fetchApi/InsertOutcome.php")
    suspend fun insertOutcome(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("stock") stock: String,
        @Field("pengirim") pengirim: String
    ): Response<ResponseMessage>


    @DELETE("/Inventory%20System/api/fetchApi/deleteStock.php")
    suspend fun deleteStockItem(@Query("id") id: String): Response<ResponseMessage>

    @PUT("/Inventory%20System/api/fetchApi/updateStock.php")
    suspend fun updateStockItem(
        @Query("id") id: String,
        @Query("name") name: String,
        @Query("stock") stock: String
    ): Response<ResponseMessage>
}