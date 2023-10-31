package com.jarica.chronogol.data.repository

import com.jarica.chronogol.data.model.PuntuationDto


interface PuntuationRepository {

    suspend fun getPuntuations() : List<PuntuationDto>
    suspend fun getPuntuations30ByOrder() : List<PuntuationDto>
    suspend fun getPuntuations60ByOrder() : List<PuntuationDto>
    suspend fun getPuntuations90ByOrder() : List<PuntuationDto>

   // fun findAll(): Flow<ApiResult<List<PuntuationDto>>>


    }
