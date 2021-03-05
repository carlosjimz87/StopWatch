package com.carlosjimz87.stopwatch.domain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosjimz87.stopwatch.data.repo.RecordsRepository
import com.carlosjimz87.stopwatch.domain.models.Record
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class RecordsViewModel : ViewModel() {
    enum class RecordsApiStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<RecordsApiStatus>()
    val status: LiveData<RecordsApiStatus>
        get() = _status

    private var _records = MutableLiveData<List<Record>>()
    val records : LiveData<List<Record>>
        get() = _records


    fun updateRecords() = viewModelScope.launch{
        _status.value = RecordsApiStatus.LOADING

        _records.value = RecordsRepository.listRecords()

        _status.value = RecordsApiStatus.DONE
    }


    fun addRecord(time:String) = viewModelScope.launch{
        _status.value = RecordsApiStatus.LOADING

        val newRecord = Record(
            id="",
            datetime = "2100-12-31T23:59:59.001+0000",
            time = "00:00:00.001"
        )
        RecordsRepository.createRecord(newRecord)
        _records.value = RecordsRepository.listRecords()

        _status.value = RecordsApiStatus.DONE

    }

    fun deleteRecord(recordId: String) = viewModelScope.launch{
        _status.value = RecordsApiStatus.LOADING

        RecordsRepository.deleteRecord(recordId)
        _records.value = RecordsRepository.listRecords()

        _status.value = RecordsApiStatus.DONE

    }

    init {
        updateRecords()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}

