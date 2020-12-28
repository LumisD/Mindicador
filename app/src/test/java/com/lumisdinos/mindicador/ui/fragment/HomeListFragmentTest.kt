package com.lumisdinos.mindicador.ui.fragment

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lumisdinos.mindicador.R
import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.lumisdinos.mindicador.factory.ViewModelUtil
import com.lumisdinos.mindicador.presentation.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class HomeListFragmentTest {

    private lateinit var fragmentScenario: FragmentScenario<TestHomeListFragment>

    @Before
    fun setUp() {
        fragmentScenario = launchFragmentInContainer()
    }

    @Ignore("IllegalArgumentException: No injector factory bound")
    @Test
    fun `givenLoadingState whenPost thenDisplayLoading`() {

        val viewState = CurrencyStateModel(loading = true)
        fragmentScenario.onFragment {
            it.currencyStateLiveData.postValue(viewState)
        }

        onView(ViewMatchers.withId(R.id.pbLoading))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Ignore("IllegalArgumentException: No injector factory bound")
    @Test
    fun `givenErrorState whenPost thenDisplayError`() {

        val viewState = CurrencyStateModel(errorMessage = "Error message")
        fragmentScenario.onFragment {
            it.currencyStateLiveData.postValue(viewState)
        }

        onView(ViewMatchers.withId(com.google.android.material.R.id.snackbar_text))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    class TestHomeListFragment : HomeListFragment() {
        private val viewModelFake = mock<HomeViewModel>()

        //val currenciesLiveData: LiveData<List<CurrencyModel>> = MutableLiveData()
        val currencyStateLiveData: MutableLiveData<CurrencyStateModel?> = MutableLiveData()

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            stubViewModel()
            this.viewModelFactory = ViewModelUtil.createFor(viewModelFake)
        }

        private fun stubViewModel() {
            whenever(viewModelFake.currencyState).thenReturn(currencyStateLiveData)
        }

    }
}