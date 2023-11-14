package com.jarica.chronogol.presentation.screens.oneplayerscreen

import android.util.Log
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

    private val _sizeCircularProgress = MutableLiveData<IntSize>()
    val sizeCircularProgress: LiveData<IntSize> = _sizeCircularProgress

    private val _value = MutableLiveData<Float>()
    val value: LiveData<Float> = _value

    private val _currentTime = MutableLiveData<Int>()
    val currentTime: LiveData<Int> = _currentTime

    private val _isTimerRunning = MutableLiveData<Boolean>()
    val isTimerRunning: LiveData<Boolean> = _isTimerRunning

    private val _isPenaltyActive = MutableLiveData<Boolean>()
    val isPenaltyActive: LiveData<Boolean> = _isPenaltyActive

    private val _totalTime = MutableLiveData<Int>()

    private val _goals = MutableLiveData<Int>()
    val goals: LiveData<Int> = _goals

    private val _isGoalAnimationActive = MutableLiveData<Boolean>()
    val isGoalAnimationActive: LiveData<Boolean> = _isGoalAnimationActive

    private val _isWhistleAnimationActive = MutableLiveData<Boolean>()
    val isWhistleAnimationActive: LiveData<Boolean> = _isWhistleAnimationActive

    private val _isGameFinished = MutableLiveData<Boolean>()
    val isGameFinished: LiveData<Boolean> = _isGameFinished


    init {
        _sizeCircularProgress.value = IntSize.Zero
        _value.value = 0f
        _currentTime.value = 3000
        _isTimerRunning.value = false
        _totalTime.value = 3000
        _goals.value = 0
        _isPenaltyActive.value = false
        _isGoalAnimationActive.value = false
    }

    fun playPauseClicked() {
        _isTimerRunning.value = !_isTimerRunning.value!!
        viewModelScope.launch(Dispatchers.IO) {
            while (_currentTime.value!! > 0 && _isTimerRunning.value == true) {
                delay(10L)
                if (_isTimerRunning.value == true) {
                    _currentTime.postValue(_currentTime.value?.minus(1))
                    _value.postValue(_currentTime.value!! / _totalTime.value?.toFloat()!!)
                }
            }
        }
        if (_isTimerRunning.value == false && _totalTime != _currentTime) {
            checkResult()
        }
    }

    private fun checkResult() {

        viewModelScope.launch(Dispatchers.IO) {
            delay(500L)
            Log.d("Nono", (currentTime.value!! % 100).toString())


            if (_isPenaltyActive.value == true) {
                if ((currentTime.value!! % 100 > 94) || (currentTime.value!! % 100 in 0..5)) {
                    _goals.postValue(goals.value!! + 1)
                    _isGoalAnimationActive.postValue(true)
                    delay(3000)
                    _isGoalAnimationActive.postValue(false)
                }
                _isPenaltyActive.postValue(false)

            } else {
                if ((currentTime.value!! % 100) == 0) {
                    _goals.postValue(goals.value!! + 1)
                    _isGoalAnimationActive.postValue(true)
                    delay(3000)
                    _isGoalAnimationActive.postValue(false)
                }
                if ((currentTime.value!! % 100 > 97) || (currentTime.value!! % 100 in 1..2)) {
                    _isWhistleAnimationActive.postValue(true)
                    delay(1400)
                    _isWhistleAnimationActive.postValue(false)
                    _isPenaltyActive.postValue(true)
                }
            }


        }


    }


    fun onSizeChanged(it: IntSize) {
        _sizeCircularProgress.value = it
    }

    fun changeDuration(value: Int) {
        _totalTime.value = value*100
        _currentTime.value = value*100
    }

    fun restartGame() {
        _currentTime.postValue(_totalTime.value)
        _isPenaltyActive.postValue(false)
        _goals.postValue(0)
    }


}



