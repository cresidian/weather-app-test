package com.example.weatherapptest.core.base.recycler

import java.io.Serializable

interface BaseListItem : Serializable {

    interface OnClickListener {
        fun onItemClicked(item: BaseListItem, adapter: BaseListItemAdapter)
    }

    val header: String get() = ""
    val tag: Any? get() = null
    val viewTypeResId: Int
    val dividerDrawableAbove: Int get() = 0
    val dividerDrawableBelow: Int get() = 0

}
