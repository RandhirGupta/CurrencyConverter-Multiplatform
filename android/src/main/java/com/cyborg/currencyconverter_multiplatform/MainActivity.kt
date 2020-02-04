package com.cyborg.currencyconverter_multiplatform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.scheduler.mainScheduler
import com.cyborg.common.data.CurrenciesApiService
import com.cyborg.common.data.CurrenciesMapper
import com.cyborg.common.data.model.CurrencyModel
import com.cyborg.common.data.repository.CurrenciesRepositoriesImpl
import com.cyborg.common.domain.CurrencyUseCaseImpl
import com.cyborg.common.presentation.ListViewModel
import com.cyborg.common.presentation.ListViewModelImpl
import com.cyborg.common.presentation.ViewModelBinding
import com.cyborg.currencyconverter_multiplatform.adapter.CurrenciesAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var mCurrenciesRv: RecyclerView
    private lateinit var mCurrenciesAdapter: CurrenciesAdapter

    private val mBinding = ViewModelBinding()


    private val mCurrenciesViewModel: ListViewModel<String, CurrencyModel> by lazy {

        val currencyMapper = CurrenciesMapper()

        val currenciesService = CurrenciesApiService("https://revolut.duckdns.org/latest", "EUR")

        val currenciesRepository = CurrenciesRepositoriesImpl(currenciesService)

        val currencyUseCase = CurrencyUseCaseImpl(currenciesRepository)

        ListViewModelImpl(currencyUseCase, currencyMapper)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding()

        mCurrenciesRv = findViewById(R.id.currenciesRv)

        mCurrenciesAdapter = CurrenciesAdapter()


        mCurrenciesRv.layoutManager = LinearLayoutManager(this)
        mCurrenciesRv.adapter = mCurrenciesAdapter

        mCurrenciesViewModel.inputs.get("latest")
    }

    override fun onDestroy() {
        super.onDestroy()

        mBinding.dispose()
    }

    private fun binding() {
        mBinding.subscribe(
            mCurrenciesViewModel.outputs.loading.observeOn(mainScheduler),
            onNext = ::loading
        )
        mBinding.subscribe(
            mCurrenciesViewModel.outputs.result.observeOn(mainScheduler),
            onNext = ::result
        )
    }

    private fun loading(isLoading: Boolean) {
    }

    private fun result(currenciesList: List<CurrencyModel>) {
        mCurrenciesAdapter.setCurrencyList(currenciesList)
    }
}
