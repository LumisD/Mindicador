package com.lumisdinos.mindicador.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.lumisdinos.mindicador.databinding.FragmentListHomeBinding
import com.lumisdinos.mindicador.domain.model.CurrencyModel
import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import com.lumisdinos.mindicador.presentation.HomeViewModel
import com.lumisdinos.mindicador.ui.adapter.CurrenciesListAdapter
import com.lumisdinos.mindicador.ui.adapter.OnCurrencyClickListener
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import javax.inject.Inject


class HomeListFragment : DaggerFragment(), OnCurrencyClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var viewBinding: FragmentListHomeBinding? = null
    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }
    private var listAdapter: CurrenciesListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentListHomeBinding.inflate(inflater, container, false)
        val view = viewBinding?.root
        setHasOptionsMenu(true)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("qwer onViewCreated -> downloadCurrencies")
        viewModel.downloadCurrencies()
    }


    @ExperimentalCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupListAdapter()
        viewModel.currencyState.observe(viewLifecycleOwner, { render(it) })
    }


//    override fun onResume() {
//        super.onResume()
//        viewModel.getGame()
//    }
//
//
//    override fun onStop() {
//        super.onStop()
//        viewModel.saveGame()
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }


    private fun render(currencyState: CurrencyStateModel) {
        if (currencyState.isCurrenciesUpdated) updateCurrencies(currencyState.currencies)
    }


    private fun updateCurrencies(currencies: List<CurrencyModel>) {
        viewModel.currenciesAreRendered()
        listAdapter?.submitList(viewModel.convertCurrencyModelsToCurrencyViews(currencies))
    }


    private fun setupListAdapter() {
        listAdapter = CurrenciesListAdapter(this)
        viewBinding?.currenciesList?.adapter = listAdapter
        viewBinding?.currenciesList?.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }


    //--  OnCurrencyClickListener  --
    override fun onItemClicked(id: Int?) {
        Timber.d("qwer onItemClicked")
    }

}