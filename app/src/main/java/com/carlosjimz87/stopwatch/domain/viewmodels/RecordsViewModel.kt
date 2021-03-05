package com.carlosjimz87.stopwatch.domain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carlosjimz87.stopwatch.data.repo.RecordsRepository
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.ManagedCoroutineScope
import kotlinx.coroutines.*


class RecordsViewModel(
    val coroutineScope : ManagedCoroutineScope
) : ViewModel() {
    enum class RecordsApiStatus { LOADING, ERROR, DONE }

    private val recordsRepository = RecordsRepository()

    private val _status = MutableLiveData<RecordsApiStatus>()
    val status: LiveData<RecordsApiStatus>
        get() = _status

    private var _records = MutableLiveData<List<Record>>()
    val records : LiveData<List<Record>>
        get() = _records


    fun updateRecords() = coroutineScope.launch{
        _status.value = RecordsApiStatus.LOADING

        _records.value = recordsRepository.listRecords(RecordsRepository.SOURCE.DB)

        _status.value = RecordsApiStatus.DONE
    }


    fun addRecord(time:String) = coroutineScope.launch{


    }

    init {
        updateRecords()
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

}

