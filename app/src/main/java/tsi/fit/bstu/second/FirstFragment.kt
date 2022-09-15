package tsi.fit.bstu.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import tsi.fit.bstu.second.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val viewModel: CalcViewModel by activityViewModels()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.spinnerMode.adapter = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_list_item_1, Mode.values()
        )

        val callback = object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {

                val mode = viewModel.selectedMode.get()

                val fragment : Fragment = if (mode?.equals(Mode.Average) == true) AverageModeCalculatorFragment() else TotalModeCalculatorFragment()

                if (!isAdded) return;
                val fm: FragmentManager = childFragmentManager
                val transaction: FragmentTransaction = fm.beginTransaction()
                transaction.replace(R.id.modeFragmentContainer, fragment)
                transaction.commit()


            }
        }

        binding.viewModel!!.selectedMode.addOnPropertyChangedCallback(callback);

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}