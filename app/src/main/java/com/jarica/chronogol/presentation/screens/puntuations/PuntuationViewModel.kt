package com.jarica.chronogol.presentation.screens.puntuations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jarica.chronogol.data.model.PuntuationDto
import com.jarica.chronogol.data.repository.PuntuationRepository
import com.jarica.chronogol.domain.models.Puntuation
import com.jarica.chronogol.presentation.screens.oneplayerscreen.OnePlayerViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PuntuationViewModel @Inject constructor(
    private val puntuationRepository: PuntuationRepository,
) : ViewModel() {

    private val _puntuationListByOrder = MutableStateFlow<List<Puntuation>?>(listOf())
    val puntuationListByOrder: Flow<List<Puntuation>?> = _puntuationListByOrder

    private val _gameDuration = MutableLiveData<Int>()
    val gameDuration: LiveData<Int> = _gameDuration
    init {
        getPuntuations30ByOrder()
    }
    fun getPuntuations30ByOrder() {
        viewModelScope.launch {
            val puntuations = puntuationRepository.getPuntuations30ByOrder()
            _puntuationListByOrder.emit(puntuations.map {
                it.asDomainModel()
            })
        }
    }

    fun getPuntuations60ByOrder() {
        viewModelScope.launch {
            val puntuations = puntuationRepository.getPuntuations60ByOrder()
            _puntuationListByOrder.emit(puntuations.map {
                it.asDomainModel()
            })
        }
    }

    fun getPuntuations90ByOrder() {
        viewModelScope.launch {
            val puntuations = puntuationRepository.getPuntuations90ByOrder()
            _puntuationListByOrder.emit(puntuations.map {
                it.asDomainModel()
            })
        }
    }


    private fun PuntuationDto.asDomainModel(): Puntuation {
        return Puntuation(
            id = this.id,
            name = this.name,
            goals = this.goals
        )

    }

    fun changeDuration(duration: Int, onClick: () -> Unit) {
        _gameDuration.value = duration
        onClick()
        //onePlayerViewModel.changeDuration(_gameDuration.value!!)
    }
}

