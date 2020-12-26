package com.lumisdinos.mindicador.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.lumisdinos.mindicador.R
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
        Timber.d("qwer onActivityCreated -> viewModel.currencyState.observe")
        viewModel.currencyState.observe(viewLifecycleOwner, { render(it) })
        viewModel.currencies.observe(viewLifecycleOwner, { updateCurrencies(it) })
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
        if (!currencyState.errorMessage.isNullOrEmpty()) showSnackBar(currencyState.errorMessage)
    }


    private fun updateCurrencies(currencies: List<CurrencyModel>) {
        listAdapter?.submitList(viewModel.convertCurrencyModelsToCurrencyViews(currencies))
    }

    private fun showSnackBar(message: String) {
        Timber.d("qwer showSnackBar message: %s", message)
        viewModel.messageIsShown()
        val snackBar = Snackbar.make(
            viewBinding?.root!!, message,
            Snackbar.LENGTH_LONG
        ).setAction("Try again!") { viewModel.downloadCurrencies() }
        snackBar.setActionTextColor(Color.BLUE)
        snackBar.setTextColor(getColor(requireContext(), R.color.black_text))
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(Color.CYAN)
//        val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
//        textView.setTextColor(Color.BLUE)
        snackBar.show()

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
    override fun onItemClicked(codigo: String?) {
        Timber.d("qwer onItemClicked")
    }

}