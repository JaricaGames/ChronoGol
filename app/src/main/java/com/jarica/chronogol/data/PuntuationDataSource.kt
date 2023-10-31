package com.jarica.chronogol.data

import com.jarica.chronogol.data.model.PuntuationDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.xml.transform.Result

class PuntuationDataSource @Inject constructor(private val supabaseClient: SupabaseClient) {


    fun findAll():Flow<ApiResult<List<PuntuationDto>>>{
        return flow {
            emit(ApiResult.Loading)
            try {
                val columns = Columns.list("name", "goals")
                val result = supabaseClient.postgrest["Puntuations"].select(columns) {
                    order("goals", Order.DESCENDING)
                }.decodeList<PuntuationDto>()
                emit(ApiResult.Success(result))
            }catch (e:Exception){
                emit(ApiResult.Error(e.message))
            }
        }
    }

}


sealed class  ApiResult<out R>{
    data class Success<out R>(val data: R):ApiResult<R>()
    data class Error(val message:String?):ApiResult<Nothing>()
    object Loading:ApiResult<Nothing>()
}