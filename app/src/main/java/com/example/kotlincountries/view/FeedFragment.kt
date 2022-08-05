package com.example.kotlincountries.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlincountries.R
import com.example.kotlincountries.adapter.CountryAdapter
import com.example.kotlincountries.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by viewModels()
    private val countryAdapter = CountryAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.refreshData()

        countryList.layoutManager = LinearLayoutManager(context)
        countryList.adapter = countryAdapter
        observeLiveData()
    }

    fun observeLiveData() {

        viewModel.countries.observe(viewLifecycleOwner) { countries ->
            countryList.visibility = View.VISIBLE
            countryAdapter.updateCountryList(countries)
        }

        viewModel.countryError.observe(viewLifecycleOwner) { error ->
            if (error) {
                countryError.visibility = View.VISIBLE
            } else {
                countryError.visibility = View.GONE
            }
        }

        viewModel.countryLoading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                progressBar.visibility = View.VISIBLE
                countryList.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                countryList.visibility = View.VISIBLE
            }
        }
    }
}