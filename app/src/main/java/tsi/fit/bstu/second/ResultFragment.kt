package tsi.fit.bstu.second

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import tsi.fit.bstu.second.databinding.FragmentAverageModeCalculatorBinding
import tsi.fit.bstu.second.databinding.FragmentResultBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val viewModel: CalcViewModel by activityViewModels()
    private fun goBack() {
        val mode = viewModel.selectedMode.get()

        val fragment : Fragment = if (mode?.equals(Mode.Average) == true) AverageModeCalculatorFragment() else TotalModeCalculatorFragment()

        val fm: FragmentManager = parentFragmentManager
        val transaction: FragmentTransaction = fm.beginTransaction()
        transaction.replace(R.id.modeFragmentContainer, fragment)
        transaction.commit()
    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            goBack()
        }

        _binding = FragmentResultBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mode = viewModel.selectedMode.get()
        binding.averageModeGroup?.visibility = if (mode == Mode.Average) View.VISIBLE else View.GONE
        binding.totalModeGroup?.visibility = if (mode == Mode.Total) View.VISIBLE else View.GONE

        binding.tableLayout?.removeViewAt(if (mode == Mode.Total) 1 else 2)

        binding.buttonGoBack.setOnClickListener {
          goBack()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}