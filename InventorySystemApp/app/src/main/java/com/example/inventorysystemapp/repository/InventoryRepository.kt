package com.example.inventorysystemapp.repository

import com.example.inventorysystemapp.api.InventoryService
import com.example.inventorysystemapp.model.*
import com.example.inventorysystemapp.retrofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import retrofit2.Response

class InventoryRepository {
    private val inventoryService = RetrofitInstance.getInstance().create(InventoryService::class.java)

    suspend fun getAllStock() : Response<ResponseGetAllStock> {
        return inventoryService.getAllStock()
    }

    suspend fun getAllUser() : Response<ResponseAllUser> {
        return inventoryService.getAllUser()
    }

    suspend fun loginUser(username : String, password : String) : Response<ResponseRoleStatus> {
        return CoroutineScope(IO).async {
            inventoryService.userLogin(username, password)
        }.await()
    }

    suspend fun getCurrentIncome() : Response<ResponseCurrentIncome> {
        return inventoryService.getCurrentIncome()
    }

    suspend fun getCurrentOutcome() : Response<ResponseCurrentOutcome> {
        return inventoryService.getCurrentOutcome()
    }

    suspend fun insetStockItem(id : String, name : String, stock : String) : Response<ResponseMessage> {
        return inventoryService.insertStockItem(id,name,stock)
    }

    suspend fun getAllIncome() : Response<ResponseDetailIncome> {
        return inventoryService.getAllIncome()
    }

    suspend fun getAllOutcome() : Response<ResponseDetailOutcome> {
        return inventoryService.getAllOutcome()
    }

    suspend fun insertIncome(
        id : String,
        name : String,
        stock : String,
        penerima : String
    ) : Response<ResponseMessage> {
        return inventoryService.insertIncome(id,name,stock,penerima)
    }

    suspend fun insertOutcome(
        id : String,
        name : String,
        stock : String,
        pengirim : String
    ) : Response<ResponseMessage> {
        return inventoryService.insertOutcome(id,name,stock,pengirim)
    }

    suspend fun deleteStockItem(id : String) : Response<ResponseMessage> {
        return inventoryService.deleteStockItem(id)
    }

    suspend fun updateStockItem(
        id : String,
        name : String,
        stock : String
    ) : Response<ResponseMessage> {
        return inventoryService.updateStockItem(id,name,stock)
    }
}