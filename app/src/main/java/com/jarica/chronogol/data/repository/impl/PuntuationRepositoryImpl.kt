package com.jarica.chronogol.data.repository.impl


import com.jarica.chronogol.data.model.PuntuationDto
import com.jarica.chronogol.data.repository.PuntuationRepository
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Order
import io.github.jan.supabase.postgrest.query.Returning
import javax.inject.Inject

class PuntuationRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest
) : PuntuationRepository {

    override suspend fun getPuntuations(): List<PuntuationDto> {
        val response = postgrest["Puntuations"].select().decodeList<PuntuationDto>()
        return response
    }

    override suspend fun getPuntuations30ByOrder(): List<PuntuationDto> {
        val response = postgrest["Puntuations30"].select(columns = Columns.list("name", "goals")) {
            order(column = "goals", order = Order.DESCENDING)
        }

        return response.decodeList()
    }

    override suspend fun getPuntuations60ByOrder(): List<PuntuationDto> {

        val response = postgrest["Puntuations60"].select(columns = Columns.list("name", "goals")) {
            order(column = "goals", order = Order.DESCENDING)
        }
        return response.decodeList()
    }

    override suspend fun getPuntuations90ByOrder(): List<PuntuationDto> {

        val response = postgrest["Puntuations90"].select(columns = Columns.list("name", "goals")) {
            order(column = "goals", order = Order.DESCENDING)
        }
        return response.decodeList()
    }

    override suspend fun setPuntuation(puntuation: PuntuationDto, value: Int?) {
        val table = value!!.div(100)
        postgrest["Puntuations$table"].insert(puntuation, returning = Returning.MINIMAL)
    }

}

