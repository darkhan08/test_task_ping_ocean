package com.example.testtaskpingocean.ui.profile

import com.example.testtaskpingocean.R
import com.example.testtaskpingocean.data.entity.ProfileItem
import com.example.testtaskpingocean.data.entity.UserProfile

class ProfileFactory {

    fun create(profile: UserProfile): ArrayList<ProfileItem> {
        val profileList: ArrayList<ProfileItem> = ArrayList()
        profileList.apply {
            add(
                ProfileItem(
                    R.string.profile_id,
                    profile.id
                )
            )
            add(
                ProfileItem(
                    R.string.profile_name,
                    profile.name
                )
            )
            add(
                ProfileItem(
                    R.string.profile_email,
                    profile.email
                )
            )
            add(
                ProfileItem(
                    R.string.profile_phone,
                    profile.phone
                )
            )
            add(
                ProfileItem(
                    R.string.profile_name_eng,
                    profile.nameEng
                )
            )
            add(
                ProfileItem(
                    R.string.profile_company_name,
                    profile.companyName
                )
            )
            add(
                ProfileItem(
                    R.string.profile_avatar,
                    profile.avatar
                )
            )
            add(
                ProfileItem(
                    R.string.profile_position,
                    profile.position
                )
            )
            add(
                ProfileItem(
                    R.string.profile_timezone,
                    profile.timeZone
                )
            )
            add(
                ProfileItem(
                    R.string.profile_alert_email,
                    profile.alertEmail
                )
            )
            add(
                ProfileItem(
                    R.string.send_system_notifications,
                    profile.notifications.toString()
                )
            )
        }
        return profileList
    }
}