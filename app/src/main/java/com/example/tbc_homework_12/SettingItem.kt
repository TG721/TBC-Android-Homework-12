package com.example.tbc_homework_12

data class SettingItem(
    val startImage: Int,
    val title: String,
    val hasEndImage: Boolean,
    val endImage: Int?,
    val hasEndSwitch: Boolean,
    var endSwitch: Boolean?
)

val Settings = mutableListOf<SettingItem>(
    SettingItem(R.drawable.ic_notification, "Notification", true, R.drawable.ic_right_arrow, false,null),
    SettingItem(R.drawable.ic_location, "Location", true, R.drawable.ic_right_arrow, false, null),
    SettingItem(R.drawable.ic_payment, "Payment", true, R.drawable.ic_right_arrow, false, null),
    SettingItem(R.drawable.ic_language, "Language", true, R.drawable.ic_right_arrow, false, null),
    SettingItem(R.drawable.ic_dark_mode_switch, "Dark Mode", false, null, true, endSwitch = false),
    SettingItem(R.drawable.ic_grid_4, "4 grid view", false, null, true, endSwitch = false),
    SettingItem(R.drawable.ic_space_community, "Invite friends", true, R.drawable.ic_right_arrow, false, endSwitch = null)
)
