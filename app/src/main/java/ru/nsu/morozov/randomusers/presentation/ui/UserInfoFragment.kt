package ru.nsu.morozov.randomusers.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import ru.nsu.morozov.randomusers.databinding.UserInfoFragmentBinding
import ru.nsu.morozov.randomusers.domain.entity.User
import ru.nsu.morozov.randomusers.presentation.MainViewModel
import ru.nsu.morozov.randomusers.presentation.SelectedUserState
import ru.nsu.morozov.randomusers.presentation.ViewModelFactory
import javax.inject.Inject

class UserInfoFragment : Fragment()  {
    private var _binding: UserInfoFragmentBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

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
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]
        _binding = UserInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userState.observe(viewLifecycleOwner) { state ->
            when (state) {
                SelectedUserState.Absent -> Unit
                is SelectedUserState.Content -> showContent(state.user)
            }

        }

    }


    private fun showContent(user: User) {
        with(binding) {
            userName.text = user.name
            userAge.text = user.age.toString()
            userEmail.text = user.email
        }
    }
}