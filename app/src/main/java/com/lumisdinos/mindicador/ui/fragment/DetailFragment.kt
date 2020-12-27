package com.lumisdinos.mindicador.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.lumisdinos.mindicador.R
import com.lumisdinos.mindicador.common.util.isClickedShort
import com.lumisdinos.mindicador.databinding.FragmentDetailBinding
import com.lumisdinos.mindicador.domain.model.SerieModel
import com.lumisdinos.mindicador.domain.model.SerieStateModel
import com.lumisdinos.mindicador.presentation.DetailViewModel
import com.lumisdinos.mindicador.ui.adapter.OnSerieClickListener
import com.lumisdinos.mindicador.ui.adapter.SeriesListAdapter
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import javax.inject.Inject

class DetailFragment : DaggerFragment(), OnSerieClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var viewBinding: FragmentDetailBinding? = null
    private val viewModel by viewModels<DetailViewModel> { viewModelFactory }
    private val args: DetailFragmentArgs by navArgs()
    private var listAdapter: SeriesListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = viewBinding?.root
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("qwer onViewCreated -> downloadCurrencies")
        viewModel.downloadSeriesByCurrencyId(args.currencyCode)
    }

    @ExperimentalCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupListAdapter()
        viewModel.serieState.observe(viewLifecycleOwner, { render(it) })
        viewModel.series.observe(viewLifecycleOwner, { updateSeries(it) })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                Timber.d("qwer action_share clicked")
                if (isClickedShort()) return true
                viewModel.share()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    private fun render(serieState: SerieStateModel) {
        if (!serieState.errorMessage.isNullOrEmpty()) showSnackBar(serieState.errorMessage)
    }

    private fun updateSeries(series: List<SerieModel>) {
        viewModel.messageIsShown()
        listAdapter?.submitList(viewModel.convertSerieyModelsToSerieViews(series))
    }

    private fun showSnackBar(message: String) {
        Timber.d("qwer showSnackBar message: %s", message)
        viewModel.messageIsShown()
        val snackBar = Snackbar.make(
            viewBinding?.root!!, message,
            Snackbar.LENGTH_LONG
        ).setAction("Try again!") { viewModel.downloadSeriesByCurrencyId() }
        snackBar.setActionTextColor(Color.BLUE)
        snackBar.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_text))
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(Color.CYAN)
        snackBar.show()
    }

    private fun setupListAdapter() {
        listAdapter = SeriesListAdapter(this)
        viewBinding?.seriesList?.adapter = listAdapter
        viewBinding?.seriesList?.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    //--  OnSerieClickListener  --
    override fun onItemClicked(id: Int?) {
        Timber.d("qwer onItemClicked")
    }

}