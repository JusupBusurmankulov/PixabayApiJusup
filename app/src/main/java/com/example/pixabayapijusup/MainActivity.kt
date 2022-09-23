package com.example.pixabayapijusup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pixabayapijusup.Retrofit.App
import com.example.pixabayapijusup.Retrofit.PixabayModel
import com.example.pixabayapijusup.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ImageAdapter
    val layoutManager = LinearLayoutManager(this)
    var isLoading = false
    var per_page = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = layoutManager
                binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        val total: Int = adapter.itemCount
                        val visibleItemCount: Int = layoutManager.childCount
                        val pastVisibleItem: Int = layoutManager.findLastCompletelyVisibleItemPosition()

                        if (!isLoading) {
                            if (visibleItemCount + pastVisibleItem >= total) {
                                per_page += 10
                                doRequest()

                            }
                        }
                        super.onScrolled(recyclerView, dx, dy)


                    }
                })

        initClicker()

    }


    private fun initClicker() {
        with(binding) {
            btnAdd.setOnClickListener {
                doRequest()
            }
            btnSearch.setOnClickListener {
                per_page += 10
                doRequest()

            }
        }
    }

    fun doRequest() {
        isLoading = true
        App.api.getImages(binding.etValue.text.toString(), perPage = per_page)
            .enqueue(object : Callback<PixabayModel> {
                override fun onResponse(
                    call: Call<PixabayModel>,
                    response: Response<PixabayModel>
                ) {
                    if (response.isSuccessful) {
                        adapter = ImageAdapter(response.body()!!.hits)
                        binding.recyclerView.adapter = adapter
                    }

                }



                override fun onFailure(call: Call<PixabayModel>, t: Throwable) {
                    Log.e("jusup ", "onFailure: ${t.message}")
                }


            })

        isLoading = false


    }
}


