package com.mobile.mobileengineertranslationtest.ui.home_ui.home_fragments

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.OvershootInterpolator
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.mobile.mobileengineertranslationtest.R
import com.mobile.mobileengineertranslationtest.databinding.FragmentCalculateBinding


class CalculateFragment : Fragment() {

    private var _binding: FragmentCalculateBinding? = null
    private val binding get() = _binding!!

    private val categories = listOf("Documents", "Glass", "Liquid", "Food", "Electronic", "Product", "Others")
    private val selectedCategories = mutableSetOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculateBinding.inflate(inflater, container, false)
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


        // 👉 Animate button from bottom
        val slideUp = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 1f,
            Animation.RELATIVE_TO_SELF, 0f
        ).apply {
            duration = 500
            interpolator = OvershootInterpolator(1.2f)
        }

        binding.btnCalculate.startAnimation(slideUp)

        binding.btnCalculate.setOnClickListener {
            it.startAnimation(bounceAnim)
            findNavController().navigate(R.id.action_calculateFragment_to_calculateSummaryFragment)

        }

        binding.arrowBack.setOnClickListener {
            findNavController().navigateUp()
        }


        setupCategoryButtons()
        inputFieldsAnim()
//        topLayoutAnim()

        animateTopLayoutHeight()
    }


    private fun animateTopLayoutHeight() {
        val topLayout = binding.topLayout

        // Convert dp to px
        val startHeight = dpToPx(350f)
        val endHeight = dpToPx(100f)

        val valueAnimator = ValueAnimator.ofInt(startHeight, endHeight).apply {
            duration = 600
            interpolator = OvershootInterpolator()

            addUpdateListener { animation ->
                val newHeight = animation.animatedValue as Int
                val layoutParams = topLayout.layoutParams
                layoutParams.height = newHeight
                topLayout.layoutParams = layoutParams
            }
        }

        valueAnimator.start()
    }

    private fun dpToPx(dp: Float): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }


    fun topLayoutAnim(){

        val topLayout = binding.topLayout  // Replace with correct binding

        // Create scale animation (scale from 1.3x back to 1x)
        val scaleAnimation = ScaleAnimation(
            1.3f, 1.0f, // fromX, toX
            1.3f, 1.0f, // fromY, toY
            Animation.RELATIVE_TO_SELF, 0.5f, // pivotX
            Animation.RELATIVE_TO_SELF, 0.5f  // pivotY
        ).apply {
            duration = 600
            interpolator = OvershootInterpolator()
            fillAfter = true
        }

        topLayout.startAnimation(scaleAnimation)
    }


    fun inputFieldsAnim(){

        val inputLayout = binding.inputLayout  // Replace with actual binding reference

        val slideIn = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 1f,  // Start from below
            Animation.RELATIVE_TO_SELF, 0f   // End at original position
        ).apply {
            duration = 200
            fillAfter = true
            interpolator = OvershootInterpolator()
        }

        val fadeIn = AlphaAnimation(0f, 1f).apply {
            duration = 200
            fillAfter = true
        }

// Combine both animations
        val animationSet = AnimationSet(true).apply {
            addAnimation(slideIn)
            addAnimation(fadeIn)
        }

// Start animation
        inputLayout.startAnimation(animationSet)

    }



    private fun setupCategoryButtons() {
        categories.forEach { category ->
            val button = MaterialButton(requireContext()).apply {
                text = category
                isAllCaps = false
                isClickable = true
                isCheckable = false
                isSelected = false

                setTextColor(ContextCompat.getColorStateList(context, R.color.category_text_selector))
                background = ContextCompat.getDrawable(context, R.drawable.bg_category_button)
                setBackgroundTintList(null)
                stateListAnimator = AnimatorInflater.loadStateListAnimator(context, R.animator.button_state_animator)

                layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(8, 8, 8, 8)
                }

                setOnClickListener {
                    isSelected = !isSelected
                    if (isSelected) {
                        selectedCategories.add(category)
                        setCompoundDrawablesWithIntrinsicBounds(
                            ContextCompat.getDrawable(context, R.drawable.ic_check),
                            null, null, null
                        )
                    } else {
                        selectedCategories.remove(category)
                        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
                    }

                    val scaleUp = ObjectAnimator.ofPropertyValuesHolder(
                        this,
                        PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 1.1f),
                        PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 1.1f)
                    ).apply { duration = 500 }

                    val scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                        this,
                        PropertyValuesHolder.ofFloat(View.SCALE_X, 1.1f, 1f),
                        PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.1f, 1f)
                    ).apply { duration = 500 }

                    AnimatorSet().apply {
                        playSequentially(scaleUp, scaleDown)
                        start()
                    }
                }
            }

            // Add button to layout
            binding.flexCategories.addView(button)

            // Apply animation immediately
            val slideIn = TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 1f,  // From X: right
                Animation.RELATIVE_TO_SELF, 0f,  // To X: normal position
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f
            ).apply {
                duration = 600
                interpolator = OvershootInterpolator(1.5f)
            }

            button.startAnimation(slideIn)
        }
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
