package com.example.tbc_homework_12

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_homework_12.databinding.SettingsItemBinding
import com.example.tbc_homework_12.databinding.SettingsItemPlaceholderBinding
import com.example.tbc_homework_12.databinding.SettingsItemVer2Binding

class SettingItemAdapter(private var settings: MutableList<SettingItem>,  private var onItemSwitchClicked: ((setting: SettingItem, position: Int) -> Unit))
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class SettingWithSwitchHolder(private val settingWithSwitch: SettingsItemVer2Binding) :
    RecyclerView.ViewHolder(settingWithSwitch.root){
        fun bind(setting: SettingItem, position: Int){
        settingWithSwitch.startIcon.setImageResource(setting.startImage)
            settingWithSwitch.middleText.text = setting.title
            settingWithSwitch.endSwitch.isChecked = setting.endSwitch!!
            settingWithSwitch.endSwitch.setOnCheckedChangeListener { _ , _ ->
                onItemSwitchClicked(setting, position)
            }
        }
    }
    inner class SettingWithEndIconHolder(private val settingWithEndIcon: SettingsItemBinding) :
        RecyclerView.ViewHolder(settingWithEndIcon.root){
        fun bind(setting: SettingItem, position: Int){
            settingWithEndIcon.startIcon.setImageResource(setting.startImage)
            settingWithEndIcon.middleText.text = setting.title
            settingWithEndIcon.endIcon.setImageResource(setting.endImage!!)
        }
    }
    inner class SettingWithOutBothHolder(private val settingWithOutBoth: SettingsItemPlaceholderBinding) :
        RecyclerView.ViewHolder(settingWithOutBoth.root){
        fun bind(setting: SettingItem, position: Int){
            settingWithOutBoth.startIcon.setImageResource(setting.startImage)
            settingWithOutBoth.middleText.text = setting.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            1 ->  {
                val view = SettingsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return SettingWithEndIconHolder(view)
            }
            2 -> {
                val view = SettingsItemVer2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
                return SettingWithSwitchHolder(view)
            }
        }
            return SettingWithSwitchHolder(SettingsItemVer2Binding.inflate(LayoutInflater.from(parent.context), parent, false))


    }
    override fun getItemViewType(position: Int): Int {
        if (settings[position].hasEndImage) return 1
        if (settings[position].hasEndSwitch) return 2
        return  0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 1) return (holder as SettingWithEndIconHolder).bind(settings[position], position)
        if (getItemViewType(position) == 2) return (holder as SettingWithSwitchHolder).bind(settings[position], position)
        else return (holder as SettingWithOutBothHolder).bind(settings[position], position)

    }
    override fun getItemCount(): Int {
        return settings.size
    }

}