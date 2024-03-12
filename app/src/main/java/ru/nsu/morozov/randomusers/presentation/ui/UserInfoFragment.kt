package ru.nsu.morozov.randomusers.presentation.ui

import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.AndroidSupportInjection
import ru.nsu.morozov.randomusers.R
import ru.nsu.morozov.randomusers.databinding.UserInfoFragmentBinding
import ru.nsu.morozov.randomusers.domain.entity.User
import ru.nsu.morozov.randomusers.presentation.MainViewModel
import ru.nsu.morozov.randomusers.presentation.SelectedUserState
import ru.nsu.morozov.randomusers.presentation.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class UserInfoFragment : Fragment() {
    private var _binding: UserInfoFragmentBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: MainViewModel

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

    private val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    private val outputFormat = SimpleDateFormat("dd.MM.yyyy, HH:mm", Locale.getDefault())

    private fun showContent(user: User) {
        with(binding) {
            userName.text = getString(
                R.string.full_name_template,
                user.nameTitle,
                user.firstName,
                user.lastName
            )
            userGender.text = getString(R.string.gender_template, user.gender)
            userAge.text = getString(R.string.age_template, user.age.toString())
            userBirthday.text = getString(
                R.string.born_template,
                inputFormat.parse(user.birthDate)?.let { outputFormat.format(it) } ?: user.birthDate
            )

            userCountry.text = getString(R.string.country_template, user.country)
            userState.text = getString(R.string.state_template, user.state)
            userCity.text = getString(R.string.city_template, user.city)
            userAddress.text = getString(R.string.address_template, user.street, user.house)

            userPhone.text = makeLink(getString(R.string.phone_template, user.phone))
            userCell.text = makeLink(getString(R.string.cell_template, user.cell))
            userEmail.text = makeLink(getString(R.string.email_template, user.email))


            Glide.with(userImage.context)
                .load(user.image)
                //.placeholder(R.drawable.loading_placeholder)
                //.error(R.drawable.loading_placeholder)
                .apply(
                    RequestOptions.bitmapTransform(
                        RoundedCorners(
                            TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP,
                                12f,
                                Resources.getSystem().displayMetrics
                            ).toInt()
                        )
                    )
                )
                .into(userImage)
        }
    }

    private fun makeLink(
        src: String,
        scale: Float = 1.1f,
        separator: String = ": "
    ): SpannableString {
        val spannableString = SpannableString(src)
        val start = src.indexOf(separator) + separator.length
        val end = src.length
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            RelativeSizeSpan(scale),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            UnderlineSpan(),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableString
    }

}