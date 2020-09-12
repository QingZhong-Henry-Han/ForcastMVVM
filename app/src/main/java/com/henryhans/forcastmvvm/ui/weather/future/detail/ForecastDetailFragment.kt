package com.henryhans.forcastmvvm.ui.weather.future.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.henryhans.forcastmvvm.R

class ForecastDetailFragment : Fragment() {

    companion object {
        fun newInstance() =
            ForecastDetailFragment()
    }

    private lateinit var viewModel: ForecastDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forecast_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ForecastDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}