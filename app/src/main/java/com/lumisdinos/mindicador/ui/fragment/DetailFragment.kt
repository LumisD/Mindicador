package com.lumisdinos.mindicador.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
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
        viewModel.downloadSeriesByCurrencyId(0)//todo: set meaningful id
    }


    @ExperimentalCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupListAdapter()
        viewModel.serieState.observe(viewLifecycleOwner, { render(it) })
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


    private fun render(serieState: SerieStateModel) {
        if (serieState.isSeriesUpdated) updateSeries(serieState.series)
    }


    private fun updateSeries(series: List<SerieModel>) {
        viewModel.seriesAreRendered()
        listAdapter?.submitList(viewModel.convertSerieyModelsToSerieViews(series))
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


    override fun onItemClicked(id: Int?) {
        Timber.d("qwer onItemClicked")
    }

}