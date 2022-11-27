package com.example.umc09_st

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.example.umc09_st.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

val CLIENT_ID = "Xtx5gFEcj_JZerq7RROG"
val CLIENT_SECRET = "qnDQSqAr6c"
val BASE_URL_NAVER_API = "https://openapi.naver.com/"

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_NAVER_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(NaverAPI::class.java)
        val callPostTransferPapago = api.transferPapago(CLIENT_ID, CLIENT_SECRET,
            "ko", "en", "테스트입니다. 이거 번역해주세요.")

        callPostTransferPapago.enqueue(object : Callback<ResultTransferPapago> {
            override fun onResponse(
                call: Call<ResultTransferPapago>,
                response: Response<ResultTransferPapago>
            ) {
                Log.d(TAG, "성공 : ${response.raw()}")
            }

            override fun onFailure(call: Call<ResultTransferPapago>, t: Throwable) {
                Log.d(TAG, "실패 : $t")
            }
        })
/*
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_NAVER_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(NaverAPI::class.java)
        val callGetSearchNews = api.getSearchNews(CLIENT_ID, CLIENT_SECRET, "테스트")


        callGetSearchNews.enqueue(object : Callback<ResultGetSearchNews> {
            override fun onResponse(
                call: Call<ResultGetSearchNews>,
                response: Response<ResultGetSearchNews>
            ) {
                Log.d("결과", "성공 : ${response.raw()}")
            }

            override fun onFailure(call: Call<ResultGetSearchNews>, t: Throwable) {
                Log.d("결과:", "실패 : $t")
            }
        })
*/
    }
}