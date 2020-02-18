package kz.weatherastana.app.ui.home.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import kz.weatherastana.app.R
import kz.weatherastana.app.databinding.FragmentHomeDetailsBinding
import kz.weatherastana.app.domain.model.DayModel
import kz.weatherastana.app.ui.MainActivity
import javax.inject.Inject

class HomeDetailsFragment : DaggerFragment() {

    private val TAG: String = this::class.java.simpleName
    private var dayModel: DayModel? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeDetailsViewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        ).get(HomeDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeDetailsBinding>(
            inflater, R.layout.fragment_home_details, container, false
        )
        dayModel = arguments?.getSerializable("dayModel") as DayModel
        binding.item = dayModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolbarTitle(getString(R.string.overview))
//        audio_player.setURL(exhibitModel?.audio)
//        tv_rate.setOnClickListener {
////            if (!viewModel.prefsImpl.getRatings().contains(exhibitModel?.id_exhibit.toString())) {
//                showRateAlertDialog()
////            }
//        }
        with(viewModel) {
//            ratingModelData.observe(viewLifecycleOwner, Observer {
////                tv_rate.text = it.rating?.toString()
//            })
            error.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context, "${it?.error}", Toast.LENGTH_LONG).show()
            })
        }
    }
}