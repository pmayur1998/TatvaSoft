package com.example.tatvasoft.userScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tatvasoft.R
import com.example.tatvasoft.ViewModelFactory
import com.example.tatvasoft.models.Result
import com.example.tatvasoft.models.User
import com.example.tatvasoft.utils.Paggination
import com.example.tatvasoft.utils.Response
import kotlinx.android.synthetic.main.activity_user_screen.*

class UserScreenActivity : AppCompatActivity() {
    private lateinit var viewModel: UserScreenViewModel
    private lateinit var adapter: UserScreenAdapter
    private var user: ArrayList<User> = ArrayList()
    private var currentPage = 0
    private var totalPage  = 0

    private val apiObserver = Observer<Response<Result?>> { response ->
        if (response != null) {
            user.addAll(response.responseBody!!.data.users)

            var pages = 0
            pages = if(user.size.rem(5) > 0){
                val items = user.size - user.size.rem(5)
                items.div(5) + 1
            } else{
                user.size.div(5)
            }

            totalPage = pages
            addNewData()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_screen)

        adapter = UserScreenAdapter(mutableListOf(),this)

        viewModel = ViewModelProvider(this, ViewModelFactory()).get(
            UserScreenViewModel::class.java
        )

        user_screen_rv.layoutManager = LinearLayoutManager(this)
        user_screen_rv.adapter = adapter
        setPaginationListener(user_screen_rv.layoutManager as LinearLayoutManager)

        viewModel.getUsersFromAPI()!!.observe(this,apiObserver)
    }

    private fun setPaginationListener(linearLayoutManager: LinearLayoutManager) {
        val scrollListner = object : Paggination(linearLayoutManager) {
            override fun loadMoreItems() {
                addNewData()
            }

            override fun isLastPage(): Boolean {
                return currentPage >= totalPage
            }

        }
        user_screen_rv.addOnScrollListener(scrollListner)
    }

    /**
     * usage add new data after limitation
     * @param
     * @return none
     */
    private fun addNewData() {
        val startIndex = currentPage.times(5)
        var endIndex = startIndex + 5
        if(endIndex > user.lastIndex){
            endIndex = user.lastIndex + 1
        }
        val subList = user.subList(startIndex,endIndex)
        adapter.setItem(subList)
        currentPage++
    }
}