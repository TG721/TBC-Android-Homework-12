package com.example.tbc_homework_12

import android.content.Context
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tbc_homework_12.databinding.FragmentFirstBinding
import kotlin.collections.ArrayList


class FirstFragment : Fragment(), RecyclerViewInterface {

    private var binding: FragmentFirstBinding? = null
//    val args: FirstFragmentArgs by navArgs()
    var adapter: ItemAdapter = ItemAdapter(Items, this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding?.recyclerView
        recyclerView?.adapter = adapter
        if(!Settings[5].endSwitch!!)
        recyclerView?.layoutManager = GridLayoutManager(requireContext(), 3)
        else {
            recyclerView?.layoutManager = GridLayoutManager(requireContext(), 4)
        }

        val settingButton = binding?.settingsButton
        settingButton?.setOnClickListener{

            val action = FirstFragmentDirections.navigateToSettingsFragment()
            Navigation.findNavController(view).navigate(action)
        }



        val searchView = binding?.searchView
        searchView?.clearFocus()
        searchView?.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = ArrayList<Item>()
                for (item in Items)
                    if (item.title.contains(newText.toString().lowercase()))
                        filteredList.add(item)
                recyclerView?.adapter = ItemAdapter(filteredList, this@FirstFragment)
                return false
            }

        })
    }

    override fun onItemClick(item: Item) {
//        super.onItemClick(position)
//        Toast.makeText(requireContext(), "ddf$position", Toast.LENGTH_SHORT).show()
        val action = FirstFragmentDirections.navigateToSecondFragment(item)
        Navigation.findNavController(view!!).navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

//    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
//        val inflater = super.onGetLayoutInflater(savedInstanceState)
//        val contextThemeWrapper: Context = ContextThemeWrapper(requireContext(), R.style.Theme_TBC_HomeWork_12)
//        return inflater.cloneInContext(contextThemeWrapper)
//    }
}