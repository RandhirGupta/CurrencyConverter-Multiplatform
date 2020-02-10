package com.cyborg.currencyconverter_multiplatform

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.scheduler.mainScheduler
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cyborg.common.data.CurrenciesApiService
import com.cyborg.common.data.CurrenciesMapper
import com.cyborg.common.data.local.CurrencyDao
import com.cyborg.common.data.local.DatabaseHelper
import com.cyborg.common.data.model.CurrencyModel
import com.cyborg.common.data.repository.CurrenciesRepositoriesImpl
import com.cyborg.common.domain.CurrencyUseCaseImpl
import com.cyborg.common.presentation.ListViewModel
import com.cyborg.common.presentation.ListViewModelImpl
import com.cyborg.common.presentation.ViewModelBinding
import com.cyborg.common.sql.CurrencyDatabase
import com.cyborg.currencyconverter_multiplatform.adapter.CurrenciesAdapter
import com.squareup.sqldelight.android.AndroidSqliteDriver
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mCurrenciesRv: RecyclerView
    private lateinit var mContentLayout: ConstraintLayout
    private lateinit var mLoadingView: ConstraintLayout
    private lateinit var mErrorView: ConstraintLayout
    private lateinit var mCurrenciesAdapter: CurrenciesAdapter
    private lateinit var mRetryBtn: Button
    private lateinit var mBaseCurrencyName: AppCompatTextView
    private lateinit var mBaseCurrencyIcon: AppCompatImageView
    private lateinit var mBaseCurrencyDesc: AppCompatTextView

    private val mBinding = ViewModelBinding()


    private val databaseHelper: DatabaseHelper by lazy {

        val sqlDriver = AndroidSqliteDriver(
            schema = CurrencyDatabase.Schema,
            context = this,
            name = "currencies.db"
        )

        DatabaseHelper("currencies.db", sqlDriver)
    }

    private val mCurrenciesViewModel: ListViewModel<String, CurrencyModel> by lazy {

        val currencyMapper = CurrenciesMapper()

        val currenciesService =
            CurrenciesApiService("https://api.exchangeratesapi.io/latest", "EUR")

        val currencyDao = CurrencyDao(databaseHelper)

        val currenciesRepository = CurrenciesRepositoriesImpl(currenciesService, currencyDao)

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
        mBaseCurrencyIcon = findViewById(R.id.baseCurrencyIcon)
        mBaseCurrencyDesc = findViewById(R.id.baseCurrencyDesc)

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
        mBaseCurrencyDesc.text = Currency.getInstance(currenciesList[0].currencyName).displayName

        val drawable = resources.getIdentifier(
            "flag_" + currenciesList[0].currencyName.toLowerCase(Locale.ENGLISH),
            "drawable", packageName
        )

        Glide.with(this).load(drawable)
            .apply(RequestOptions.circleCropTransform())
            .into(baseCurrencyIcon)

        mCurrenciesAdapter.setCurrencyList(currenciesList)
    }

    private fun error() {
        mErrorView.visibility = View.VISIBLE
        mLoadingView.visibility = View.GONE
        mContentLayout.visibility = View.GONE
    }
}
