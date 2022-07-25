package com.example.tbc_homework_12

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tbc_homework_12.databinding.FragmentFirstBinding
import java.util.*
import kotlin.collections.ArrayList


class FirstFragment : Fragment(), RecyclerViewInterface {
    private lateinit var binding: FragmentFirstBinding
    var adapter: ItemAdapter = ItemAdapter(Items, this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        val searchView = binding.searchView
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = ArrayList<Item>()
                for (item in Items)
                    if (item.title.contains(newText.toString().lowercase()))
                        filteredList.add(item)
                recyclerView.adapter = ItemAdapter(filteredList, this@FirstFragment)
                return false
            }

        })
    }

    override fun onItemClick(position: Int) {
//        super.onItemClick(position)
//        Toast.makeText(requireContext(), "ddf$position", Toast.LENGTH_SHORT).show()
        val action = FirstFragmentDirections.navigateToSecondFragment(position)
        Navigation.findNavController(view!!).navigate(action)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FirstFragment()
    }
}