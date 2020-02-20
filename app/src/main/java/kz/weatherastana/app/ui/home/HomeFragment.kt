package kz.weatherastana.app.ui.home

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kz.weatherastana.app.R
import kz.weatherastana.app.databinding.DialogDayBinding
import kz.weatherastana.app.databinding.FragmentHomeBinding
import kz.weatherastana.app.domain.model.DayModel
import kz.weatherastana.app.domain.model.response.ErrorStatus
import kz.weatherastana.app.ui.MainActivity
import kz.weatherastana.app.ui.home.adapter.HomeAdapter
import javax.inject.Inject

class HomeFragment : DaggerFragment(), HomeAdapter.OnDayClickListener {

    private val TAG: String = this::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }
    private val adapter: HomeAdapter by lazy { HomeAdapter(arrayListOf(), this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolbarTitle(getString(R.string.app_name))
        initView()
        with(viewModel) {
            daysData.observe(viewLifecycleOwner, Observer {
                ll_connection_error.visibility = View.GONE
                adapter.add(it)
                rv_main_home.scheduleLayoutAnimation()
            })
            error.observe(viewLifecycleOwner, Observer {
                if (listOf(ErrorStatus.NO_CONNECTION, ErrorStatus.TIMEOUT).contains(it.status) &&
                    adapter.itemCount == 0
                ) {
                    ll_connection_error.visibility = View.VISIBLE
                }
                Toast.makeText(context, "${it?.error}", Toast.LENGTH_LONG).show()
            })
        }

        tv_retry.setOnClickListener {
            viewModel.getWeather()
        }
    }


    private fun initView() {
        rv_main_home.adapter = adapter
        swipe.setOnRefreshListener {
            adapter.clear()
            viewModel.getWeather()
        }
    }

    override fun onDayClick(dayModel: DayModel) {
        showRateAlertDialog(context!!, dayModel)
    }

    private fun showRateAlertDialog(context: Context, dayModel: DayModel) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_day)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val binding = DataBindingUtil.inflate<DialogDayBinding>(LayoutInflater.from(getContext()), R.layout.dialog_day, null, false)
        dialog.setContentView(binding.root)
        binding.item = dayModel

        dialog.show()
    }
}