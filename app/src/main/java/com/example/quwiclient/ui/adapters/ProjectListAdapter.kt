package com.example.quwiclient.ui.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quwiclient.model.Project
import com.example.quwiclient.ui.ProjectDetailActivity
import com.example.quwiclient.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.view.*


class ProjectListAdapter(var context: Context, var projList: List<Project>) : RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(projList[position])
    }

    override fun getItemCount(): Int {
        return projList.size
    }

    fun setProjectList(projList: List<Project>) {
        this.projList = projList
        notifyDataSetChanged()
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        val defaultImage = R.drawable.pngegg

        fun bind(item: Project) {
            view.app_name.text = item.name
            Picasso
                .get()
                .load(item.logoURL)
                .placeholder(defaultImage)
                .error(defaultImage)
                .into(view.app_image)


            if(item.isActive == 1.toLong()) {
                view.app_status.text = context.resources.getString(R.string.Active)
                view.app_status.setTextColor(Color.parseColor("#4CAF50"))

            } else {
                view.app_status.text = context.resources.getString(R.string.Inactive)
                view.app_status.setTextColor(Color.parseColor("#D8D7D7"))
            }


            view.setOnClickListener {
                val intent = Intent(view.context, ProjectDetailActivity::class.java)

                intent.putExtra(PROJECT_TITLE_KEY, item.name)
                intent.putExtra(PROJECT_ID_KEY,  item.id)
                intent.putExtra(PROJECT_ACTIVITY_KEY, item.isActive)

                view.context.startActivity(intent)
            }
        }
    }

    companion object {
        val PROJECT_TITLE_KEY = "PROJECT_TITLE"
        val PROJECT_ACTIVITY_KEY = "PROJECT_ACTIVITY"
        val PROJECT_ID_KEY = "PROJECT_ID"
    }
}


