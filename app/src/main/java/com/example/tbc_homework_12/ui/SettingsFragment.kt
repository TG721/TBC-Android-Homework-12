package com.example.tbc_homework_12.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tbc_homework_12.R
import com.example.tbc_homework_12.adapter.SettingItemAdapter
import com.example.tbc_homework_12.databinding.FragmentSettingsBinding
import com.example.tbc_homework_12.helper.SettingsImage.imageURI
import com.example.tbc_homework_12.model.Settings
import com.github.dhaval2404.imagepicker.ImagePicker


class SettingsFragment : Fragment() {

    private var binding: FragmentSettingsBinding? = null
    private var adapter: SettingItemAdapter = SettingItemAdapter(Settings) { _, pos ->
        updateSwitch(pos)
    }

    private fun updateSwitch(pos: Int) {
        Settings[pos].endSwitch=!(Settings[pos].endSwitch)!!
        adapter.notifyItemChanged(pos)

        if(Settings[4].endSwitch==true) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding!!.root


    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    val image = if (imageURI != null) {
        imageURI
    } else {
        Uri.parse("android.resource://" + "com.example.tbc_homework_12" + "/" + R.drawable.nika1)
    }
        Glide.with(this@SettingsFragment)
            .load(image)
            .into((binding?.imageUser) as ImageView)

        val recyclerView = binding?.settingsRecyclerView
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())



        val pencilButton = binding?.imagePencil

        //handling click
        pencilButton?.setOnClickListener {
            ImagePicker.Companion.with(this@SettingsFragment)
                .crop(150f,150f)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }

        }


//        val callback = object : OnBackPressedCallback(true){
//            override fun handleOnBackPressed() {
//
//                val action = SettingsFragmentDirections.navigateToFirstFragment(Settings[5].endSwitch!!)
//                Navigation.findNavController(view!!).navigate(action)
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(callback)

    }



    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    //Image Uri will not be null for RESULT_OK
        //                 viewModel.updateUri(data?.data!!)
                    imageURI = data?.data


                    Glide.with(this@SettingsFragment)
                        .load(imageURI)
                        .into((binding?.imageUser) as ImageView)

                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        d("bla", "onDestroyView")
        binding = null
    }



}