package com.example.uspace.ui.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.uspace.ui.fragments.CapsuleFragment
import com.example.uspace.ui.fragments.CrewFragment
import com.example.uspace.ui.fragments.DragonsFragment
import com.example.uspace.ui.fragments.RocketsFragment

class TabsAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    private val numberOfTabs = 4

    override fun getItemCount(): Int {
        return numberOfTabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RocketsFragment()
            1 -> CrewFragment()
            2 -> DragonsFragment()
            3 -> CapsuleFragment()
            else -> RocketsFragment()
        }
    }
}