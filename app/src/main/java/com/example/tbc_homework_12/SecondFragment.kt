package com.example.tbc_homework_12

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.tbc_homework_12.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private var binding: FragmentSecondBinding? = null
    val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.clickedPicTitlte.text = Items[args.pos].title
        binding!!.clickedPicDescription.text = Items[args.pos].description
        binding!!.clickedPicItself.setImageResource(Items[args.pos].image)
    }

    companion object {

        @JvmStatic
        fun newInstance() = SecondFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}