package com.example.quwiclient.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.example.quwiclient.R
import com.example.quwiclient.model.ApiClient
import com.example.quwiclient.model.Info
import com.example.quwiclient.model.NewNameRequest
import com.example.quwiclient.ui.adapters.ProjectListAdapter
import com.example.quwiclient.ui.adapters.UsersListAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_project_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProjectDetailActivity : AppCompatActivity() {

    private lateinit var apiClient: ApiClient
    private var projID : Long? = null
    private lateinit var adapter: UsersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_detail)

        apiClient = ApiClient()

        adapter = UsersListAdapter(listOf())

        val manager = GridLayoutManager(this, 3)
        manager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 1 || position == 6) {
                    2 // ITEMS AT POSITION 1 AND 6 OCCUPY 2 SPACES
                } else {
                    1 // OTHER ITEMS OCCUPY ONLY A SINGLE SPACE
                }
            }
        }

        rv_users.layoutManager = manager
        rv_users.adapter = adapter

        onClickListener()

        projID = intent.getLongExtra(ProjectListAdapter.PROJECT_ID_KEY, 0L)
        println(projID)

        getProjectInfo()

        okBtn.setOnClickListener {
            val newProjName = etProjName.text.toString()
            println(newProjName)

            if (newProjName.isNotEmpty()) {
                changeProjectName(newProjName)
            }

        }
    }

    fun onClickListener() {
        backBtn.setOnClickListener {
            finish()
        }
    }

    fun changeProjectName(newProjName: String) {

        apiClient.getApiService(this).changeProjName(projID, NewNameRequest(newProjName))
            .enqueue(object : Callback<Info> {
                override fun onFailure(call: Call<Info>, t: Throwable) {
                    Toast.makeText(this@ProjectDetailActivity, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Info>, response: Response<Info>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        println(body)

                        etProjName.setText(response.body()!!.project.name)
                        Toast.makeText(
                            this@ProjectDetailActivity,
                            "Project Name was successfully changed to ${etProjName.text}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            })
    }


    fun getProjectInfo() {

        apiClient.getApiService(this).getProjectInfo(projID)
            .enqueue(object : Callback<Info> {
                override fun onFailure(call: Call<Info>, t: Throwable) {
                    Toast.makeText(this@ProjectDetailActivity, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Info>, response: Response<Info>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        println(body)

                        etProjName.setText(response.body()!!.project.name)

                        Picasso.get()
                            .load(body!!.project.logoURL)
                            .placeholder(R.drawable.pngegg)
                            .error(R.drawable.pngegg)
                            .into(user_image)

                        if (body.project.isActive == 1.toLong()) {
                            switchS.isChecked = true
                        } else {
                            switchS.isChecked = false
                        }

                        if (body.project.isOwnerWatcher == 1.toLong()) {
                            switchL.isChecked = true
                        } else {
                            switchL.isChecked = false
                        }

                        adapter.setUsersList(body.project.users)


                        /*Toast.makeText(
                            this@ProjectDetailActivity,
                            "Info load success!",
                            Toast.LENGTH_SHORT
                        )
                            .show()*/
                    } else {
                        Toast.makeText(
                            this@ProjectDetailActivity,
                            "Info load failed!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            })
    }


}