package com.cyborg.currencyconverter_multiplatform

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
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
    private lateinit var mContentLayout: ConstraintLayout
    private lateinit var mLoadingView: ConstraintLayout
    private lateinit var mErrorView: ConstraintLayout
    private lateinit var mCurrenciesAdapter: CurrenciesAdapter
    private lateinit var mRetryBtn: Button
    private lateinit var mBaseCurrencyName: AppCompatTextView

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

        mLoadingView = findViewById(R.id.loadingView)
        mCurrenciesRv = findViewById(R.id.currenciesRv)
        mContentLayout = findViewById(R.id.contentLayout)
        mErrorView = findViewById(R.id.errorView)
        mRetryBtn = findViewById(R.id.retryBtn)
        mBaseCurrencyName = findViewById(R.id.baseCurrencyName)

        mRetryBtn.setOnClickListener {
            binding()
        }
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
            onNext = ::loading,
            onError = {
                error()
            }
        )
        mBinding.subscribe(
            mCurrenciesViewModel.outputs.result.observeOn(mainScheduler),
            onNext = ::result,
            onError = {
                error()
            }
        )
    }

    private fun loading(isLoading: Boolean) {
        mLoadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
        mErrorView.visibility = View.GONE
        mContentLayout.visibility = View.GONE
    }

    private fun result(currenciesList: List<CurrencyModel>) {

        mLoadingView.visibility = View.GONE
        mContentLayout.visibility = View.VISIBLE
        mErrorView.visibility = View.GONE

        mBaseCurrencyName.text = currenciesList[0].currencyName
        mCurrenciesAdapter.setCurrencyList(currenciesList)
    }

    private fun error() {
        mErrorView.visibility = View.VISIBLE
        mLoadingView.visibility = View.GONE
        mContentLayout.visibility = View.GONE
    }
}
