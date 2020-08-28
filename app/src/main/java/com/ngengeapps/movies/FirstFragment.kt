package com.ngengeapps.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ngengeapps.movies.api.TheMoviesDbAPI
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val viewModel:MoviesViewModel by viewModels {
        MoviesViewModelFactory(MoviesRepository(TheMoviesDbAPI.create()))
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val adapter = MoviesRecyclerViewAdapter()
        petsRecyclerview.adapter = adapter
        lifecycleScope.launch {
            viewModel.getMovies(BuildConfig.MOVIES_KEY).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}