package com.project.android.newkiosk.ui.selectapp.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.android.newkiosk.R
import com.project.android.newkiosk.data.model.SelectApp


class SelectAppAdapter(mContext: Context, private var mListSelectApp: ArrayList<SelectApp>) :
    RecyclerView.Adapter<SelectAppAdapter.ViewHolder>() {

    private var mContext: Context? = mContext

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View =
            LayoutInflater.from(mContext).inflate(R.layout.apps_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mListSelectApp.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val selectApp: SelectApp = mListSelectApp[position]

        val icon: Drawable? = mListSelectApp[position].getAppIcon()
        val label: String? = mListSelectApp[position].getAppName()

        viewHolder.mTextViewLabel.text = label
        viewHolder.mImageViewIcon.setImageDrawable(icon)
        viewHolder.mAppSelect.isChecked = mListSelectApp[position].isSelected();

        viewHolder.setItemClickListener(object : ItemClickListener {
            override fun onItemClick(v: View, pos: Int) {
                val checkBox = v as CheckBox

                if(checkBox.isChecked) {
                    selectApp.setSelected(true);
                }
                else if (!checkBox.isChecked) {
                    selectApp.setSelected(false);
                }
            }
        })

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private var itemClickListener: ItemClickListener? = null

        var mTextViewLabel: TextView = itemView.findViewById<View>(R.id.txt_apps_name) as TextView
        var mImageViewIcon: ImageView = itemView.findViewById<View>(R.id.img_apps) as ImageView
        var mAppSelect: CheckBox = itemView.findViewById<View>(R.id.checkbox_apps) as CheckBox

        override fun onClick(view: View) {
            itemClickListener?.onItemClick(view, layoutPosition);
        }

        init {
            mAppSelect.setOnClickListener(this);
        }

        fun setItemClickListener(itemClickListener: ItemClickListener) {
            this.itemClickListener = itemClickListener
        }
    }


    interface ItemClickListener {
        fun onItemClick(v: View, pos: Int)
    }


}