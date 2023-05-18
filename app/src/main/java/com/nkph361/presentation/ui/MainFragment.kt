package com.nkph361.presentation.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nkph361.R
import com.nkph361.databinding.FragmentMainBinding
import com.nkph361.presentation.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment @Inject constructor() : Fragment(), AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        val spinnerAdapter = ArrayAdapter.createFromResource(
            view.context,
            R.array.cities,
            android.R.layout.simple_spinner_item
        )

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        with(binding.citiesSpinner) {
            adapter = spinnerAdapter
            setSelection(0, false)
            onItemSelectedListener = this@MainFragment
            gravity = Gravity.CENTER
            prompt = getString(R.string.spinner_promt)
        }

        binding.updateExchangeRateButton.setOnClickListener {
            viewModel.loadData(city = binding.citiesSpinner.selectedItem.toString())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.mainFragmentUiState.collect { uiState ->

                    setDefaultText()

                    binding.USDINTextView.append(
                        uiState.usdIn.toTextWithBynEnding()
                    )
                    binding.USDOUTTextView.append(
                        uiState.usdOut.toTextWithBynEnding()
                    )
                    binding.EURINTextView.append(
                        uiState.eurIn.toTextWithBynEnding()
                    )
                    binding.EUROUTTextView.append(
                        uiState.eurOut.toTextWithBynEnding()
                    )
                    binding.RUBINTextView.append(
                        uiState.rubIn.toTextWithBynEnding()
                    )
                    binding.RUBOUTTextView.append(
                        uiState.rubOut.toTextWithBynEnding()
                    )
                    binding.exchangeRateCard.isVisible = uiState.loadComplete
                    binding.progressCircular.isVisible = uiState.loadInProgress
                    binding.failTextView.isVisible = uiState.loadError
                }
            }
        }
        return view
    }

    private fun Double.toTextWithBynEnding() =
        toString().plus(getString(R.string.BYN_TEXT))

    private fun setDefaultText() {
        binding.USDINTextView.setText(R.string.BUY_TEXT)
        binding.USDOUTTextView.setText(R.string.SALE_TEXT)
        binding.EURINTextView.setText(R.string.BUY_TEXT)
        binding.EUROUTTextView.setText(R.string.SALE_TEXT)
        binding.RUBINTextView.setText(R.string.BUY_TEXT)
        binding.RUBOUTTextView.setText(R.string.SALE_TEXT)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.loadData(city = resources.getStringArray(R.array.cities)[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}