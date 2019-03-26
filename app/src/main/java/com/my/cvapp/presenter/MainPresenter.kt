package com.my.cvapp.presenter

import com.my.cvapp.contractor.MainContract
import com.my.cvapp.model.CVDetail

/**
 * Created by Tejas Shah on 25/03/19.
 */
class MainPresenter(private var mainView: MainContract.MainView?, private val getCVInfoIntractor: MainContract.GetCVInfoIntractor)
    : MainContract.GetCVInfoIntractor.OnFinishedListener {

    companion object {
        private val TAG: String = MainPresenter::class.java.simpleName
    }

    fun getData() {
        mainView?.showProgress()
        getCVInfoIntractor.getCVArrayList(this)
    }

    override fun onResultSuccess(arrUpdates: List<CVDetail>) {
        mainView?.hideProgress()
        mainView?.setData(arrUpdates)
    }

    override fun onResultFail(strError: String) {
        mainView?.hideProgress()
        mainView?.setDataError(strError)
    }

    // Destroy View when Activity destroyed
    fun onDestroy() {
        mainView = null
    }
}