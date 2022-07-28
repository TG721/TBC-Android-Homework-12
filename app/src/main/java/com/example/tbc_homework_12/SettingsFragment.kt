package com.example.tbc_homework_12

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_homework_12.databinding.FragmentSecondBinding
import com.example.tbc_homework_12.databinding.FragmentSettingsBinding
import com.example.tbc_homework_12.databinding.ItemBinding

class SettingsFragment : Fragment() {
    private var binding: FragmentSettingsBinding? = null
    private var bindingItem: ItemBinding? = null
    private var adapter: SettingItemAdapter = SettingItemAdapter(Settings) { it, pos ->
        updateSwitch(it, pos)
    }

    private fun updateSwitch(it: SettingItem, pos: Int) {
        Settings[pos].endSwitch=!(Settings[pos].endSwitch)!!
        adapter.notifyItemChanged(pos)

        if(Settings[4].endSwitch==true) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fagment
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding?.settingsRecyclerView
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())


//        val callback = object : OnBackPressedCallback(true){
//            override fun handleOnBackPressed() {
//
//                val action = SettingsFragmentDirections.navigateToFirstFragment(Settings[5].endSwitch!!)
//                Navigation.findNavController(view!!).navigate(action)
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(callback)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}