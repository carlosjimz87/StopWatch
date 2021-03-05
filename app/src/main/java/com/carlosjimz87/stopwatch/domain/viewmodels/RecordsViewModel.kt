package com.carlosjimz87.stopwatch.domain.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.carlosjimz87.stopwatch.data.repo.RecordsRepository
import com.carlosjimz87.stopwatch.domain.models.Record
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class RecordsViewModel(application: Application) : AndroidViewModel(application) {
    enum class RecordsApiStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<RecordsApiStatus>()
    val status: LiveData<RecordsApiStatus>
        get() = _status

    private var _records = MutableLiveData<List<Record>>()
    val records: LiveData<List<Record>>
        get() = _records


    fun updateRecords() {
        _status.value = RecordsApiStatus.LOADING

        viewModelScope.launch {
            _records.value = RecordsRepository.listRecords()
        }
        Log.d("RECORDS UPDATED:", "Size: ${_records.value!!.size}")
        _status.value = RecordsApiStatus.DONE
    }


    fun addRecord(record: Record) {

        _status.value = RecordsApiStatus.LOADING

        viewModelScope.launch {
            RecordsRepository.createRecord(record)
            _records.value = RecordsRepository.listRecords()
        }

        Log.d("NEWREC VM:", "Newrec: $record")
        _status.value = RecordsApiStatus.DONE

        updateRecords()
    }

    fun deleteRecord(recordId: String) {
        _status.value = RecordsApiStatus.LOADING

        viewModelScope.launch {
            RecordsRepository.deleteRecord(recordId)
            _records.value = RecordsRepository.listRecords()
        }

        _status.value = RecordsApiStatus.DONE

    }

    init {
        RecordsRepository.init(application.applicationContext)
        updateRecords()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}

