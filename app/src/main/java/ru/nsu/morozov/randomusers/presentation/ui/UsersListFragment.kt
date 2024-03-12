package ru.nsu.morozov.randomusers.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import ru.nsu.morozov.randomusers.R
import ru.nsu.morozov.randomusers.databinding.UsersListFragmentBinding
import ru.nsu.morozov.randomusers.domain.entity.User
import ru.nsu.morozov.randomusers.presentation.ListState
import ru.nsu.morozov.randomusers.presentation.MainViewModel
import ru.nsu.morozov.randomusers.presentation.ViewModelFactory
import javax.inject.Inject

class UsersListFragment : Fragment() {
    private var _binding: UsersListFragmentBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]
        _binding = UsersListFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    private val adapter = UsersListAdapter(
        onSelect = { user ->
            viewModel.setSelectedUser(user)
            findNavController().navigate(R.id.navigation_info)
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = UsersListFragmentBinding.bind(view)
        binding.recyclerView.adapter = adapter

        viewModel.listState.observe(viewLifecycleOwner) { state ->
            when (state) {
                ListState.Initial -> Unit
                ListState.Loading -> showProgress()
                is ListState.Content -> showContent(state.items)
                is ListState.Error -> {
                    showError(state.msg)
                    showContent(state.items)
                }
            }

        }
        with(binding) {
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.reloadData()
            }
        }


        viewModel.loadData()
    }

    private fun showProgress() {
        with(binding) {
            errorContent.isVisible = false
            recyclerView.isVisible = false
            progressBar.isVisible = true
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun showContent(users: List<User>) {
        with(binding) {
            progressBar.isVisible = false
            errorContent.isVisible = false
            recyclerView.isVisible = true
            swipeRefreshLayout.isRefreshing = false

            adapter.submitList(users)
        }
    }

    private fun showError(message: String) {
        with(binding) {
            progressBar.isVisible = false
            recyclerView.isVisible = false
            errorContent.isVisible = true
            swipeRefreshLayout.isRefreshing = false

            errorText.text = message
            errorButton.setOnClickListener {
                viewModel.loadData()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}