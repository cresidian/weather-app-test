package com.example.weatherapptest.core.base.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapptest.R
import com.example.weatherapptest.presentation.main.menu.ListItemMenuCategory
import com.example.weatherapptest.presentation.main.menu.ListItemMenuProduct
import com.example.weatherapptest.presentation.main.menu.ListItemMenuSubCategory
import com.example.weatherapptest.presentation.main.order.ListItemOrder
import com.example.weatherapptest.presentation.main.order.ListItemOrderCourseTitle
import com.example.weatherapptest.presentation.main.order.orderpreview.ListItemOrderPreviewAdditional
import com.example.weatherapptest.presentation.main.order.orderpreview.ListItemOrderPreviewProduct
import com.example.weatherapptest.presentation.main.productdetail.courses.ListItemCourse
import com.example.weatherapptest.presentation.views.listdivider.ListItemDivider

open class BaseListItemAdapter : RecyclerView.Adapter<BaseListItemAdapter.ViewHolder>() {

    private val items = ArrayList<BaseListItem>()
    var recentRemovedItem: BaseListItem? = null

    var onItemSelectedListener: OnItemSelectedListener? = null

    var onItemActionClickedListener: OnItemActionClickedListener = object :
        OnItemActionClickedListener {
        override fun onItemActionClicked(item: BaseListItem, position: Int) {
        }
    }

    interface OnItemSelectedListener {
        fun onItemSelected(item: BaseListItem, position: Int)
    }

    interface OnItemActionClickedListener {
        fun onItemActionClicked(item: BaseListItem, position: Int)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.onViewRecycled()
        super.onViewRecycled(holder)
    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun onBindViewHolder(adapter: BaseListItemAdapter, position: Int)
        open fun onViewRecycled() {}
    }

    override fun getItemViewType(position: Int): Int = getItem(position)!!.viewTypeResId

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.list_item_divider -> ListItemDivider.ViewHolder(view)
            R.layout.list_item_menu_cateogry -> ListItemMenuCategory.ViewHolder(view)
            R.layout.list_item_menu_sub_cateogry -> ListItemMenuSubCategory.ViewHolder(view)
            R.layout.list_item_menu_product -> ListItemMenuProduct.ViewHolder(view)
            R.layout.list_item_order -> ListItemOrder.ViewHolder(view)
            R.layout.list_item_order_courses_title -> ListItemOrderCourseTitle.ViewHolder(view)
            R.layout.list_item_course -> ListItemCourse.ViewHolder(view)
            R.layout.list_item_order_preview_product -> ListItemOrderPreviewProduct.ViewHolder(view)
            R.layout.list_item_order_preview_additional -> ListItemOrderPreviewAdditional.ViewHolder(
                view
            )
            else -> throw IllegalStateException("No ViewHolderItem found for layout")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindViewHolder(this, position)
    }

    open fun getItemMinHeight(viewHolder: RecyclerView.ViewHolder): Int = 0

    override fun getItemCount(): Int = items.size

    fun getItem(position: Int): BaseListItem? {
        if (position < 0 || position >= items.size) {
            return null
        }
        return items[position]
    }

    fun setItems(items: List<BaseListItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItems(position: Int, items: List<BaseListItem>) {
        this.items.addAll(position, items)
        notifyItemRangeInserted(position, items.size)
    }

    fun removeItems(items: List<BaseListItem>) {
        this.items.removeAll(items)
        notifyDataSetChanged()
    }

    fun removeItems(position: Int, count: Int) {
        for (i in 1..count) {
            this.items.removeAt(position)
        }
        notifyItemRangeRemoved(position, count)
    }

    fun addItem(item: BaseListItem, position: Int) {
        this.items.add(position, item)
        notifyItemInserted(position)
    }

    fun getItemIndex(item: BaseListItem): Int = items.indexOf(item)

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun removeItemAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun softRemoveItemAt(position: Int) {
        recentRemovedItem = getItem(position)
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addRecentRemovedItemAt(position: Int) {
        recentRemovedItem?.let {
            items.add(position, it)
            notifyItemInserted(position)
            recentRemovedItem = null
        }
    }

    private fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeItem(item: BaseListItem) {
        removeItem(items.indexOf(item))
    }

    fun notifyItemChanged(item: BaseListItem) {
        val position = items.indexOf(item)
        if (position != -1) {
            notifyItemChanged(position)
        }
    }

    fun replaceItem(item: BaseListItem) {
        items.removeAt(0)
        items.add(0, item)
        notifyItemChanged(0)
    }

    fun updateItems(diffResult: DiffUtil.DiffResult, newList: List<BaseListItem>) {
        this.items.clear()
        this.items.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun refreshItems() {
        notifyDataSetChanged()
        //notifyItemRangeChanged(0,this.items.size)
    }
}

fun <T : View> RecyclerView.ViewHolder.bind(@IdRes res: Int): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE) { itemView.findViewById<T>(res) }
