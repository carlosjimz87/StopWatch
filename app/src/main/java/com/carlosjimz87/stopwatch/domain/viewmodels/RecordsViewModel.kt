package com.carlosjimz87.stopwatch.domain.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.carlosjimz87.stopwatch.data.repo.RecordsRepository
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.Constants
import com.carlosjimz87.stopwatch.utils.Extensions.sort
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class RecordsViewModel(application: Application) : AndroidViewModel(application) {
    enum class RecordsApiStatus { LOADING, ERROR, DONE }

    var app: Application = application
    private val _status = MutableLiveData<RecordsApiStatus>()
    val status: LiveData<RecordsApiStatus>
        get() = _status

    private var _records = MutableLiveData<List<Record>>()
    val records: LiveData<List<Record>>
        get() = _records


    fun updateRecords() {
        _status.value = RecordsApiStatus.LOADING

        // updating the list of records from the SharedPrefs
        viewModelScope.launch {
            try {
                _records.value = RecordsRepository.toDB().listRecords()
                Log.d("STOPWATCH:", "Total Records: ${_records.value!!.size}")
            } catch (e: Exception) {
                handleErrors(e)
            } finally {
                _status.value = RecordsApiStatus.DONE
            }

        }

    }


    fun addRecord(record: Record) {

        _status.value = RecordsApiStatus.LOADING

        viewModelScope.launch {
            try {


                // saving record in SharedPreferences and updating the list
                if (RecordsRepository.toDB().createRecord(record)) {
                    _records.value = RecordsRepository.listRecords()
                    Log.d("STOPWATCH:", "Record Saved: $record")
                }

                if(Constants.SAVE_ON_API == "yes") {
                    // sending record to API
                    if (RecordsRepository.toAPI().createRecord(record)) {
                        // NOTIFY UI
                        Log.d("STOPWATCH:", "Record Sent: $record")
                    }
                }


            } catch (e: Exception) {
                handleErrors(e)
            } finally {
                _status.value = RecordsApiStatus.DONE
                updateRecords()
            }

        }

    }

    fun deleteRecord(pos: Int) {
        _status.value = RecordsApiStatus.LOADING


        // deleting record in SharedPreferences and updating the list
        viewModelScope.launch {
            try {
                val recordToDelete: Record? = _records.value?.get(pos)
                if (recordToDelete != null) {
                    if (RecordsRepository.toDB().deleteRecord(recordToDelete)) {
                        _records.value = RecordsRepository.listRecords()
                        Log.d("STOPWATCH:", "Record Deleted (ID): ${recordToDelete.id}")


                    }
                }
            } catch (e: Exception) {
                handleErrors(e)
            } finally {

                _status.value = RecordsApiStatus.DONE
            }

        }


    }

    private fun handleErrors(e: Exception) {
        _status.value = RecordsApiStatus.DONE
        Log.e("STOPWATCH", "Error ${e.message}")
    }

    init {
        RecordsRepository.init(app)
//        updateRecords()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}

