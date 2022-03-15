package com.example.sendimgefilewithretrofit.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sendimgefilewithretrofit.R
import com.example.sendimgefilewithretrofit.adapter.SearchAdapter
import com.example.sendimgefilewithretrofit.networking.ApiClient
import com.example.sendimgefilewithretrofit.networking.ApiService
import com.example.sendimgefilewithretrofit.networking.model.Breed
import com.example.sendimgefilewithretrofit.networking.model.BreedItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    private lateinit var apiService: ApiService
    private lateinit var adapter: SearchAdapter
    private lateinit var list: ArrayList<BreedItem>
    private lateinit var root:View
    private lateinit var   et_search : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_search, container, false)

        initViews()

        return root
    }

    private fun initViews() {
        recyclerView = root.findViewById(R.id.recyclerViewSearch)
        list = ArrayList()

        adapter = SearchAdapter(requireContext(), list)
        apiService = ApiClient.createService(ApiService::class.java)
        et_search = root.findViewById(R.id.et_search)

        et_search.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard(requireActivity(),et_search)
                searchData(et_search.text.toString())
                return@OnEditorActionListener true
            }
            false
        })

        recyclerView.adapter = adapter
    }



    override fun onResume() {
        super.onResume()
        et_search.post {
            et_search.requestFocus()
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(et_search, InputMethodManager.SHOW_IMPLICIT)
        }
    }
    private fun hideKeyboard(activity: Activity, viewToHide: View) {
        val imm = activity
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(viewToHide.windowToken, 0)
    }



    fun searchData(query: String) {
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
      apiService.getActivity(query, 1,10)
          .enqueue(object : Callback<Breed>{
              override fun onResponse(call: Call<Breed>, response: Response<Breed>) {
                  list.clear()
                  if (response.body() != null) {
                      list.addAll((response.body()!!))
                      Log.d("########", response.body().toString())
                        recyclerView.layoutManager = linearLayoutManager
                      recyclerView.adapter = adapter
                  }
              }

              override fun onFailure(call: Call<Breed>, t: Throwable) {
                  Log.d("@@@@@@@@@@", t.localizedMessage)
              }

          })
    }
}