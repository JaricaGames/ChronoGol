package com.jarica.chronogol.presentation.screens.optionscreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.jarica.chronogol.core.Config
import com.jarica.chronogol.presentation.screens.oneplayerscreen.OnePlayerViewModel
import com.jarica.chronogol.presentation.screens.oneplayerscreen.OnePlayerViewModel.Companion.totalduration
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OptionViewModel @Inject constructor(

) : ViewModel() {




    private val _gameDuration = MutableLiveData<Int>()
    val gameDuration: LiveData<Int> = _gameDuration

    private val _isSoundActive = MutableLiveData<Boolean>()
    val isSoundActive: LiveData<Boolean> = _isSoundActive

    private val _isMusicActive = MutableLiveData<Boolean>()
    val isMusicActive: LiveData<Boolean> = _isMusicActive



    init {
        _gameDuration.value = 30
        _isMusicActive.value = true
        _isSoundActive.value = true
    }

    fun changeDuration(duration: Int) {
        _gameDuration.value = duration
        totalduration = _gameDuration.value!!
        //OnePlayerViewModel().changeDuration(_gameDuration.value!!)
    }

    fun onSoundActiveChecked() {
        _isSoundActive.value = !isSoundActive.value!!
    }

    fun onMusicActiveChecked() {
        _isMusicActive.value = !isMusicActive.value!!
    }


}