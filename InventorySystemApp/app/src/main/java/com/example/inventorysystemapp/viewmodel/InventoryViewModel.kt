package com.example.inventorysystemapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventorysystemapp.model.*
import com.example.inventorysystemapp.repository.InventoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InventoryViewModel(private val repository: InventoryRepository) : ViewModel() {

    val TAG = "InventoryViewModel"
    private var responseAllStock: MutableLiveData<ResponseGetAllStock> = MutableLiveData()
    private var responseAllUser: MutableLiveData<ResponseAllUser> = MutableLiveData()
    private var responseRoleStatus: MutableLiveData<ResponseRoleStatus> = MutableLiveData()
    private var responseCurrentIncome: MutableLiveData<ResponseCurrentIncome> = MutableLiveData()
    private var responseCurrentOutcome: MutableLiveData<ResponseCurrentOutcome> = MutableLiveData()
    private var messageResponseInsertStockItem: MutableLiveData<String> = MutableLiveData()
    private var responseAllIncome: MutableLiveData<ResponseDetailIncome> = MutableLiveData()
    private var responseAllOutcome: MutableLiveData<ResponseDetailOutcome> = MutableLiveData()
    private var responseMessageString: MutableLiveData<String> = MutableLiveData()
    private var isInsertIncomeState: MutableLiveData<Boolean> = MutableLiveData(false)
    private var isInsertOutcomeState: MutableLiveData<Boolean> = MutableLiveData(false)
    private var isUpdateOrDelete: MutableLiveData<Boolean> = MutableLiveData(false)
    private var mutableStockItem: MutableLiveData<StockItem> = MutableLiveData()

    val myStock: LiveData<ResponseGetAllStock>
        get() = responseAllStock
    val myCurrentIncome: LiveData<ResponseCurrentIncome>
        get() = responseCurrentIncome
    val myCurrentOutcome: LiveData<ResponseCurrentOutcome>
        get() = responseCurrentOutcome
    val myStatusLogin: LiveData<ResponseRoleStatus>
        get() = responseRoleStatus
    val myAllUser: LiveData<ResponseAllUser>
        get() = responseAllUser
    val myAllIncome: LiveData<ResponseDetailIncome>
        get() = responseAllIncome
    val myAllOutcome: LiveData<ResponseDetailOutcome>
        get() = responseAllOutcome
    val incomeState: LiveData<Boolean>
        get() = isInsertIncomeState
    val outcomeState: LiveData<Boolean>
        get() = isInsertOutcomeState
    val updateOrDeleteState: LiveData<Boolean>
        get() = isUpdateOrDelete
    val myStockItem: LiveData<StockItem>
        get() = mutableStockItem

    fun getAllStock(): LiveData<ResponseGetAllStock> {
        viewModelScope.launch {
            var response: ResponseGetAllStock?
            withContext(Dispatchers.IO) {
                Log.d(TAG, "thread ${Thread.currentThread().name}")
                response = repository.getAllStock().body()
            }
            responseAllStock.value = response
        }
        return responseAllStock
    }

    fun getAllUser(): LiveData<ResponseAllUser> {
        viewModelScope.launch {
            var response: ResponseAllUser?
            withContext(Dispatchers.IO) {
                response = repository.getAllUser().body()
            }
            responseAllUser.value = response
        }
        return responseAllUser
    }

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            responseRoleStatus.value = repository.loginUser(username, password).body()
        }
    }

    fun getCurrentIncome(): LiveData<ResponseCurrentIncome> {
        viewModelScope.launch {
            var response: ResponseCurrentIncome?
            withContext(Dispatchers.IO) {
                response = repository.getCurrentIncome().body()
            }
            responseCurrentIncome.value = response
        }
        return responseCurrentIncome
    }

    fun getCurrentOutcome(): LiveData<ResponseCurrentOutcome> {
        viewModelScope.launch {
            var response: ResponseCurrentOutcome?
            withContext(Dispatchers.IO) {
                response = repository.getCurrentOutcome().body()
            }
            responseCurrentOutcome.value = response
        }
        return responseCurrentOutcome
    }

    fun insertStockItem(id: String, name: String, stock: String): LiveData<String> {
        var message: String?
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                message = repository.insetStockItem(id, name, stock).body()?.message
            }
            messageResponseInsertStockItem.value = message
            responseAllStock.value = repository.getAllStock().body()
        }
        return messageResponseInsertStockItem
    }

    fun getAllIncome() {
        viewModelScope.launch {
            var response: ResponseDetailIncome?
            withContext(Dispatchers.IO) {
                response = repository.getAllIncome().body()
            }
            responseAllIncome.value = response
        }
    }

    fun getAllOutcome() {
        viewModelScope.launch {
            var response: ResponseDetailOutcome?
            withContext(Dispatchers.IO) {
                response = repository.getAllOutcome().body()
            }
            responseAllOutcome.value = response
        }
    }

    fun insertIncome(id : String,
                     name : String,
                     stock : String,
                     penerima : String
    ) : LiveData<String> {
        viewModelScope.launch {
            var message: String?
            withContext(Dispatchers.IO) {
                message = repository.insertIncome(id,name,stock,penerima).body()?.message
            }
            responseMessageString.value = message
            responseAllStock.value = repository.getAllStock().body()
            responseCurrentIncome.value = repository.getCurrentIncome().body()
            responseAllIncome.value = repository.getAllIncome().body()
        }

        return responseMessageString
    }

    fun insertOutcome(id : String,
                     name : String,
                     stock : String,
                     pengirim : String
    ) : LiveData<String> {
        viewModelScope.launch {
            var message: String?
            withContext(Dispatchers.IO) {
                message = repository.insertOutcome(id,name,stock,pengirim).body()?.message
            }
            responseMessageString.value = message
            responseAllStock.value = repository.getAllStock().body()
            responseCurrentOutcome.value = repository.getCurrentOutcome().body()
            responseAllOutcome.value = repository.getAllOutcome().body()
        }

        return responseMessageString
    }

    fun deleteStockItem(id : String) : LiveData<String> {
        viewModelScope.launch {
            var message: String?
            withContext(Dispatchers.IO) {
                message = repository.deleteStockItem(id).body()?.message
            }
            responseMessageString.value = message
            responseAllStock.value = repository.getAllStock().body()
        }

        return responseMessageString
    }

    fun updateStockItem(id : String,
                      name : String,
                      stock : String
    ) : LiveData<String> {
        viewModelScope.launch {
            var message: String?
            withContext(Dispatchers.IO) {
                message = repository.updateStockItem(id,name,stock).body()?.message
            }
            responseMessageString.value = message
            responseAllStock.value = repository.getAllStock().body()
        }

        return responseMessageString
    }

    fun saveStateInsertIncome() {
        isInsertIncomeState.value = true
        isInsertOutcomeState.value = false
    }

    fun saveStateInsertOutcome() {
        isInsertOutcomeState.value = true
        isInsertIncomeState.value = false
    }

    fun saveStateUpdateOrDelete(state : Boolean) {
        isUpdateOrDelete.value = state
    }

    fun passStockItemForUpdateOrDelete(stock: StockItem) {
        mutableStockItem.value = stock
    }
}