package com.nkph361.presentation.UI

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val usdInTextView = view.findViewById<TextView>(R.id.USD_IN_text_view)
        val usdOutTextView = view.findViewById<TextView>(R.id.USD_OUT_text_view)
        val eurInTextView = view.findViewById<TextView>(R.id.EUR_IN_text_view)
        val eurOutTextView = view.findViewById<TextView>(R.id.EUR_OUT_text_view)
        val rubInTextView = view.findViewById<TextView>(R.id.RUB_IN_text_view)
        val rubOutTextView = view.findViewById<TextView>(R.id.RUB_OUT_text_view)
        val progress = view.findViewById<View>(R.id.progress_circular)
        val exchangeCard = view.findViewById<View>(R.id.exchange_rate_card)
        val spinner = view.findViewById<Spinner>(R.id.cities_spinner)
        val updateButton = view.findViewById<Button>(R.id.update_exchange_rate_button)
        val errorTextView = view.findViewById<TextView>(R.id.fail_text_view)
        val spinnerAdapter = ArrayAdapter.createFromResource(
            view.context,
            R.array.cities,
            android.R.layout.simple_spinner_item
        )

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        with(spinner) {
            adapter = spinnerAdapter
            setSelection(0, false)
            onItemSelectedListener = this@MainFragment
            gravity = Gravity.CENTER
            prompt = getString(R.string.spinner_promt)
        }

        updateButton.setOnClickListener {
            viewModel.loadData(city = spinner.selectedItem.toString())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.exchangeRateStateFlow.collect {
                    usdInTextView.setText(R.string.BUY_TEXT)
                    usdOutTextView.setText(R.string.SALE_TEXT)
                    eurInTextView.setText(R.string.BUY_TEXT)
                    eurOutTextView.setText(R.string.SALE_TEXT)
                    rubInTextView.setText(R.string.BUY_TEXT)
                    rubOutTextView.setText(R.string.SALE_TEXT)
                    usdInTextView.append(it.usdIn.toString().plus(getString(R.string.BYN_TEXT)))
                    usdOutTextView.append(it.usdOut.toString().plus(getString(R.string.BYN_TEXT)))
                    eurInTextView.append(it.eurIn.toString().plus(getString(R.string.BYN_TEXT)))
                    eurOutTextView.append(it.eurOut.toString().plus(getString(R.string.BYN_TEXT)))
                    rubInTextView.append(it.rubIn.toString().plus(getString(R.string.BYN_TEXT)))
                    rubOutTextView.append(it.rubOut.toString().plus(getString(R.string.BYN_TEXT)))
                    exchangeCard.isVisible = it.loadStatus
                    progress.isVisible = it.inProgress
                    errorTextView.isVisible = it.loadError
                }
            }
        }
        return view
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.loadData(city = resources.getStringArray(R.array.cities)[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}