import com.cyborg.common.data.model.CurrenciesResponse
import com.cyborg.common.data.repository.CurrenciesRepository

class CurrenciesRepositoriesImpl<R> : CurrenciesRepository<R, CurrenciesResponse> {

    override suspend fun getCurrencies(request: R?): CurrenciesResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}