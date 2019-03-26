package com.my.cvapp.intractor

import com.my.cvapp.contractor.MainContract
import com.my.cvapp.model.CVDetail
import com.my.cvapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Tejas Shah on 25/03/19.
 */
class GetCVInfoIntractorImpl : MainContract.GetCVInfoIntractor {
    var call : Call<List<CVDetail>>? = null

    companion object {
        private val TAG: String = GetCVInfoIntractorImpl::class.java.simpleName
    }

    override fun getCVArrayList(onFinishedListener: MainContract.GetCVInfoIntractor.OnFinishedListener) {
        /** Create handle for the RetrofitInstance interface*/
        val apiClient = ApiClient.getClientInstance()
        call = apiClient.getCVDetailInfo();

        call?.enqueue(object : Callback<List<CVDetail>> {
            override fun onFailure(call: Call<List<CVDetail>>, t: Throwable) {
                onFinishedListener.onResultFail(t.toString())
            }

            override fun onResponse(call: Call<List<CVDetail>>, response: Response<List<CVDetail>>) {
                response.body()?.let { onFinishedListener.onResultSuccess(it) }
            }
        })
    }

}