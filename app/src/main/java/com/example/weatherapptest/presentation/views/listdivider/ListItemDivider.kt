package com.example.weatherapptest.presentation.views.listdivider

import android.view.View
import com.example.weatherapptest.R
import com.example.weatherapptest.core.base.recycler.BaseListItem
import com.example.weatherapptest.core.base.recycler.BaseListItemAdapter

class ListItemDivider : BaseListItem {

    override var viewTypeResId = R.layout.list_item_divider

    class ViewHolder(view: View) : BaseListItemAdapter.ViewHolder(view) {
        override fun onBindViewHolder(adapter: BaseListItemAdapter, position: Int) {

        }
    }
}
