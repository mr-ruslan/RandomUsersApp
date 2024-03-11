package ru.nsu.morozov.randomusers.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import ru.nsu.morozov.randomusers.R
import ru.nsu.morozov.randomusers.databinding.UsersListFragmentBinding
import ru.nsu.morozov.randomusers.domain.entity.User
import ru.nsu.morozov.randomusers.presentation.ListState
import ru.nsu.morozov.randomusers.presentation.MainViewModel
import javax.inject.Inject

class UsersListFragment : Fragment() {
    private var _binding: UsersListFragmentBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel : MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UsersListFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    private val adapter = UsersListAdapter(
        onMore = { user ->
            val bundle = UserInfoFragmentArgs.Builder(user.id).build().toBundle()
            findNavController().navigate(R.id.navigation_info, bundle)
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = UsersListFragmentBinding.bind(view)
        binding.recyclerView.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner) { state ->
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

        viewModel.loadData()
    }

    private fun showProgress() {
        with(binding) {
            errorContent.isVisible = false
            recyclerView.isVisible = false
            progressBar.isVisible = true
        }
    }

    private fun showContent(users: List<User>) {
        with(binding) {
            progressBar.isVisible = false
            errorContent.isVisible = false
            recyclerView.isVisible = true

            adapter.submitList(users)
        }
    }

    private fun showError(message: String) {
        with(binding) {
            progressBar.isVisible = false
            recyclerView.isVisible = false
            errorContent.isVisible = true

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