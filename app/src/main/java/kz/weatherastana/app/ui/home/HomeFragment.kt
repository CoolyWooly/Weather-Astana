package kz.weatherastana.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kz.weatherastana.app.R
import kz.weatherastana.app.databinding.FragmentHomeBinding
import kz.weatherastana.app.domain.model.DayModel
import kz.weatherastana.app.domain.model.response.ErrorStatus
import kz.weatherastana.app.ui.MainActivity
import kz.weatherastana.app.ui.home.adapter.HomeAdapter
import kz.weatherastana.app.utils.EndlessRecyclerViewScrollListener
import javax.inject.Inject


class HomeFragment : DaggerFragment(), HomeAdapter.OnDayClickListener {

    private val TAG: String = this::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }
    private val adapter: HomeAdapter by lazy { HomeAdapter(arrayListOf(), this) }
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

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
        (activity as MainActivity).setToolbarTitle(getString(R.string.nav_item_main))
        initView()
        with(viewModel) {
            searchString.observe(viewLifecycleOwner, Observer {
                scrollListener.resetValues()
                adapter.clear()
                getWeather(7)
            })
            daysData.observe(viewLifecycleOwner, Observer {
                ll_connection_error.visibility = View.GONE
                if (!it.isNullOrEmpty()) {
                    adapter.add(it)
                }
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
            viewModel.getWeather(7)
        }
    }


    private fun initView() {
        val mLayoutManager = GridLayoutManager(context, 2)
        rv_main_home.layoutManager = mLayoutManager
        rv_main_home.adapter = adapter
        scrollListener = object : EndlessRecyclerViewScrollListener(mLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                viewModel.getWeather(7)
            }
        }
        rv_main_home.addOnScrollListener(scrollListener)
        swipe.setOnRefreshListener {
            scrollListener.resetValues()
            adapter.clear()
            viewModel.getWeather(7)
        }
    }

    override fun onDayClick(dayModel: DayModel) {
        val action = HomeFragmentDirections.actionNavItemMainToFragmentHomeDetails()
        action.dayModel = dayModel
        val navController = Navigation.findNavController(view!!)
        navController.navigate(action)
    }
}