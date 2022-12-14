package tsi.fit.bstu.second

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import tsi.fit.bstu.second.databinding.FragmentAverageModeCalculatorBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AverageModeCalculatorFragment : Fragment() {

    private var _binding: FragmentAverageModeCalculatorBinding? = null
    private val viewModel: CalcViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAverageModeCalculatorBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)


        binding.buttonCalculate.setOnClickListener {
            val fragment = ResultFragment()
            val fm: FragmentManager = parentFragmentManager
            val transaction: FragmentTransaction = fm.beginTransaction()
            transaction.replace(R.id.modeFragmentContainer, fragment)
            transaction.commit()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}