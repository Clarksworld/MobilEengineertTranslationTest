package com.mobile.mobileengineertranslationtest.ui.home_ui.home_fragments.calculate

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import com.mobile.mobileengineertranslationtest.R
import com.mobile.mobileengineertranslationtest.databinding.FragmentCalculateSummaryBinding
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import androidx.navigation.fragment.findNavController
import com.mobile.mobileengineertranslationtest.ui.home_ui.MainActivity
import kotlinx.coroutines.MainScope


class CalculateSummaryFragment : Fragment() {

    private var _binding: FragmentCalculateSummaryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculateSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bounceAnim = ScaleAnimation(
            0.95f, 1.0f,   // fromX, toX
            0.95f, 1.0f,   // fromY, toY
            Animation.RELATIVE_TO_SELF, 0.5f, // pivotX
            Animation.RELATIVE_TO_SELF, 0.5f  // pivotY
        ).apply {
            duration = 600
            interpolator = OvershootInterpolator(30f)
        }


        //  Animate button from bottom
        val slideUp = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 1f,
            Animation.RELATIVE_TO_SELF, 0f
        ).apply {
            duration = 500
            interpolator = OvershootInterpolator(1.2f)
        }

        binding.btnBackToHome.startAnimation(slideUp)


        binding.btnBackToHome.setOnClickListener {
            it.startAnimation(bounceAnim)
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            activity?.finish()

        }

        binding.imgBox.post {
            val zoomIn = AnimationUtils.loadAnimation(requireContext(), R.anim.zoom_in)
            binding.imgBox.startAnimation(zoomIn)
        }

        val animator = ValueAnimator.ofInt(0, 1460)
        animator.duration = 1500 // 1.5 seconds
        animator.interpolator = DecelerateInterpolator() // smooth finish

        animator.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Int
            binding.tvAmount.text = "$$animatedValue USD"
        }

        animator.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

