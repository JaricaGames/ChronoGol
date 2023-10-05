package com.jarica.chronogol.presentation.screens.oneplayerscreen

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnePlayerViewModel @Inject constructor() : ViewModel() {

    private val _size = MutableLiveData<IntSize>()
    val size: LiveData<IntSize> = _size

    private val _value = MutableLiveData<Float>()
    val value: LiveData<Float> = _value

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long> = _currentTime

    private val _isTimerRunning = MutableLiveData<Boolean>()
    val isTimerRunning: LiveData<Boolean> = _isTimerRunning

    private val _totalTime = MutableLiveData<Long>()
    val totalTime: LiveData<Long> = _totalTime


    init {
        _size.value = IntSize.Zero
        _value.value = 0f
        _currentTime.value = 2000
        _isTimerRunning.value = false
        _totalTime.value = 2000L
    }

    fun playPauseClicked(isTimeRunning: Boolean) {

        _isTimerRunning.value = !isTimeRunning
        Log.d("OnePlayerVM", _isTimerRunning.value.toString())

        viewModelScope.launch(Dispatchers.IO) {
            
            while (_currentTime.value!! > 0 && _isTimerRunning.value == true) {
                delay(10L)
                _currentTime.postValue(_currentTime.value?.minus(1L) ?: -1L)
                Log.d("OnePlayerVM", currentTime.value.toString())
                _value.postValue(_currentTime.value!! / _totalTime.value?.toFloat()!!)
            }
        }

    }


    fun onSizeChanged(it: IntSize) {
        _size.value = it
    }
}



