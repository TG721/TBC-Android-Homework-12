package com.example.tbc_homework_12.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tbc_homework_12.adapter.ItemAdapter
import com.example.tbc_homework_12.databinding.FragmentFirstBinding
import com.example.tbc_homework_12.helper.RecyclerViewInterface
import com.example.tbc_homework_12.model.Item
import com.example.tbc_homework_12.model.Items
import com.example.tbc_homework_12.model.Settings
import kotlin.collections.ArrayList


class FirstFragment : Fragment(), RecyclerViewInterface {

    private var binding: FragmentFirstBinding? = null

    //    val args: FirstFragmentArgs by navArgs()
    private var adapter: ItemAdapter = ItemAdapter(Items, this)
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
        if (!Settings[5].endSwitch!!)
            recyclerView?.layoutManager = GridLayoutManager(requireContext(), 3)
        else {
            recyclerView?.layoutManager = GridLayoutManager(requireContext(), 4)
        }

        val settingButton = binding?.settingsButton
        //handling touch
        //simulate button click visual effect
        settingButton?.setOnTouchListener { _, arg1 ->
            when (arg1.action) {
                MotionEvent.ACTION_DOWN -> {
                    settingButton.isSelected = arg1.action == MotionEvent.ACTION_DOWN
                }
                MotionEvent.ACTION_UP -> settingButton.performClick()
            }
            true
        }
        //handling click
        settingButton?.setOnClickListener {

            val action =
                FirstFragmentDirections.actionFirstFragmentToSettingsFragment2()
            findNavController(view).navigate(action)
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
        val action =
            FirstFragmentDirections.actionFirstFragmentToSecondFragment3(
                item
            )
        findNavController(view!!).navigate(action)
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