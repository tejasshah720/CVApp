package com.my.cvapp.contractor

import com.my.cvapp.model.CVDetail

/**
 * Created by Tejas Shah on 25/03/19.
 */
interface MainContract {
    interface MainView {
        fun showProgress()
        fun hideProgress()
        fun setData(arrUpdates: List<CVDetail>)
        fun setDataError(strError: String)
    }

    interface GetCVInfoIntractor {
        interface OnFinishedListener {
            fun onResultSuccess(arrUpdates: List<CVDetail>)
            fun onResultFail(strError: String)
        }

        fun getCVArrayList(onFinishedListener: OnFinishedListener)
    }

    interface RecyclerViewClickListener{
        fun onItemClick(adapterPosition: Int,selectedCVDetail: CVDetail)
    }
}