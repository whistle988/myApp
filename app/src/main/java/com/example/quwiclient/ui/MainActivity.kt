package com.example.quwiclient.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quwiclient.model.ApiClient
import com.example.quwiclient.model.ProjectList
import com.example.quwiclient.R
import com.example.quwiclient.ui.adapters.ProjectListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ProjectListAdapter
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.mToolbar))

        apiClient = ApiClient()

        adapter = ProjectListAdapter(this, listOf())
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.adapter = adapter


        getProjects()
    }

    fun getProjects() {

        apiClient.getApiService(this).getProjectsList()
            .enqueue(object : Callback<ProjectList> {
                override fun onFailure(call: Call<ProjectList>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<ProjectList>, response: Response<ProjectList>) {
                    if (response.isSuccessful) {
                        val body = response.body().toString()
                        println(body)

                        adapter.setProjectList(response.body()!!.projects)
                        adapter.notifyDataSetChanged()

                        /*Toast.makeText(this@MainActivity, "List load success!", Toast.LENGTH_SHORT)
                            .show()*/
                    } else {
                        Toast.makeText(this@MainActivity, "List load failed!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                moveTaskToBack(true);
                exitProcess(-1)
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        getProjects()
    }

}
