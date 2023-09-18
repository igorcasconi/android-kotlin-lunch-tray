package com.igorcasconi.lunchtray.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.igorcasconi.lunchtray.data.DataSource
import java.text.NumberFormat


class LunchViewModel: ViewModel() {

    val menuItems = DataSource.menuItems

    private var previousEntreePrice = 0.0
    private var previousSidePrice = 0.0
    private var previousAccompanimentPrice = 0.0

    private val taxRate = 0.08

    private val _entreeDish = MutableLiveData<MenuItem?>()
    val entreeDish: LiveData<MenuItem?> = _entreeDish

    private val _sideDish = MutableLiveData<MenuItem?>()
    val sideDish: LiveData<MenuItem?> = _sideDish

    private val _accompanimentDish = MutableLiveData<MenuItem?>()
    val accompanimentDish: LiveData<MenuItem?> = _accompanimentDish

    private val _subTotal = MutableLiveData<Double?>(0.0)
    val subTotal: LiveData<String> = _subTotal.map {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _total = MutableLiveData<Double?>(0.0)
    val total: LiveData<String> = _total.map {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _tax = MutableLiveData<Double?>(0.0)
    val tax: LiveData<String> = _tax.map {
        NumberFormat.getCurrencyInstance().format(it)
    }

    fun setEntree(entree: String) {
        if(entreeDish != null) {
            previousEntreePrice = entreeDish.value?.price ?: 0.0
        }

        if (subTotal != null) {
            _subTotal.value = _subTotal.value?.minus(previousEntreePrice)
        }

        _entreeDish.value = menuItems[entree]
        updateSubTotal(entreeDish.value?.price)
    }

    fun setSide(side: String) {
        if(sideDish != null) {
            previousSidePrice = sideDish.value?.price ?: 0.0
        }

        if (subTotal != null) {
            _subTotal.value = _subTotal.value?.minus(previousSidePrice)
        }

        _sideDish.value = menuItems[side]
        updateSubTotal(sideDish.value?.price)
    }

    fun setAccompaniment(accompaniment: String) {
        if(sideDish != null) {
            previousAccompanimentPrice = accompanimentDish.value?.price ?: 0.0
        }

        if (subTotal != null) {
            _subTotal.value = _subTotal.value?.minus(previousAccompanimentPrice)
        }

        _accompanimentDish.value = menuItems[accompaniment]
        updateSubTotal(accompanimentDish.value?.price)
    }

    fun updateSubTotal(dishPrice: Double?) {
        if (subTotal != null){
            _subTotal.value = _subTotal.value?.plus(dishPrice!!)
        } else {
            _subTotal.value = dishPrice
        }
        calculateTaxAndTotal()
    }

    fun calculateTaxAndTotal() {
        _tax.value = _subTotal.value?.times(taxRate)
        _total.value = _subTotal.value?.plus(_tax.value!!)
    }

    fun resetOrder() {
        _tax.value = 0.0
        _subTotal.value = 0.0
        _total.value = 0.0
        _entreeDish.value = null
        _accompanimentDish.value = null
        _sideDish.value = null
        previousEntreePrice = 0.0
        previousSidePrice = 0.0
        previousAccompanimentPrice = 0.0
    }

}