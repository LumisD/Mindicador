package com.lumisdinos.mindicador.ui.fragment

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.lumisdinos.mindicador.R
import com.lumisdinos.mindicador.common.CurrencyOrder
import com.lumisdinos.mindicador.common.extension.hideKeyboard
import com.lumisdinos.mindicador.common.util.isClickedShort
import com.lumisdinos.mindicador.common.util.isClickedSingle
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

@ExperimentalCoroutinesApi
class HomeListFragment : DaggerFragment(), OnCurrencyClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var viewBinding: FragmentListHomeBinding? = null
    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }
    private var listAdapter: CurrenciesListAdapter? = null
    private var orderItem: MenuItem? = null

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupListAdapter()
        Timber.d("qwer onActivityCreated -> viewModel.currencyState.observe")
        viewModel.currencyState.observe(viewLifecycleOwner, { render(it) })
        viewModel.currencies.observe(viewLifecycleOwner, { updateCurrencies(it) })
    }

//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//        Timber.d("qwer onConfigurationChanged")
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home_list, menu)
        orderItem = menu.findItem(R.id.action_switch_order)
        filterCurrencies(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.action_filter -> {
//                Timber.d("qwer action_switch_order clicked")
//                if (isClickedSingle()) return true
//                showKeyboard()
//                return true
//            }
            R.id.action_switch_order -> {
                if (isClickedShort()) return true
                viewModel.changeOrder()
                return true
            }
            R.id.action_update -> {
                if (isClickedSingle()) return true
                viewModel.downloadCurrencies()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun filterCurrencies(menu: Menu) {
        val search = menu.findItem(R.id.action_filter)
        val searchView = search.actionView as SearchView
        searchView.queryHint = getString(R.string.codigo_o_nombre)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Timber.d("qwer onQueryTextSubmit")
                searchView.clearFocus()// close the keyboard on load todo: don't work
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Timber.d("qwer onQueryTextChange")
                listAdapter?.filter(newText)
                if (newText.isNullOrEmpty()) {
                    hideKeyboard()//todo:
                }
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    private fun render(currencyState: CurrencyStateModel?) {
        currencyState?.let {
            if (!it.errorMessage.isNullOrEmpty()) showSnackBar(it.errorMessage)
            setOrderIcon(it.order)
        }
    }

    private fun updateCurrencies(currencies: List<CurrencyModel>) {
        listAdapter?.modifyList(viewModel.convertCurrencyModelsToCurrencyViews(currencies))
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

    private fun setOrderIcon(order: String?) {
        Timber.d("qwer setOrderIcon order: %s", order)
        orderItem?.let {
            when (order) {
                null -> {
                    it.icon =
                        AppCompatResources.getDrawable(requireContext(), R.drawable.ic_sort_natural)
                }
                CurrencyOrder.ASCENDING.name -> {
                    it.icon =
                        AppCompatResources.getDrawable(requireContext(), R.drawable.ic_sort_asc)
                }
                CurrencyOrder.DESCENDING.name -> {
                    it.icon =
                        AppCompatResources.getDrawable(requireContext(), R.drawable.ic_sort_des)
                }
            }
        }
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
        codigo?.let {
            val action = HomeListFragmentDirections.actionHomeListToDetailFragment(it)
            findNavController().currentDestination?.getAction(action.actionId)?.let {
                findNavController().navigate(action)
            }
        }

    }

}