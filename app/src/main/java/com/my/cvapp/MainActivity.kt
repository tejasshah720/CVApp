package com.my.cvapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.my.cvapp.adapter.CVAdapter
import com.my.cvapp.contractor.MainContract
import com.my.cvapp.intractor.GetCVInfoIntractorImpl
import com.my.cvapp.model.CVDetail
import com.my.cvapp.presenter.MainPresenter
import com.my.cvapp.utils.showToast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainContract.MainView, MainContract.RecyclerViewClickListener {

    private var progressOverlay: View? = null
    private var progressBar: ProgressBar? = null
    private var tvProgressMessage: TextView? = null
    private var cvAdapter: CVAdapter? = null
    private var recyclerView: RecyclerView? = null

    private lateinit var mainPresenter: MainPresenter

    private val cvs = mutableListOf<CVDetail>()

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerView)
        progressOverlay = findViewById(R.id.progressOverlay)
        progressBar = findViewById(R.id.progressBar);
        tvProgressMessage = findViewById(R.id.tvProgressMessage)

        progressOverlay?.visibility = View.GONE

        recyclerView?.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)

        mainPresenter = MainPresenter(this, GetCVInfoIntractorImpl())
        mainPresenter.getData()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "We can use add click to add candidate detail", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun setData(arrUpdates: List<CVDetail>) {
        cvs.clear()
        cvs.addAll(arrUpdates)

        cvAdapter = CVAdapter(arrUpdates,this)
        recyclerView?.adapter = cvAdapter
    }

    override fun setDataError(strError: String) {
        showToast(applicationContext, "strError is: $strError")
    }

    override fun showProgress() {
        progressOverlay?.visibility = View.VISIBLE
        progressBar?.visibility = View.VISIBLE
        tvProgressMessage?.text = this.resources.getString(R.string.progress_msg)
        tvProgressMessage?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar?.visibility = View.GONE
        tvProgressMessage?.visibility = View.GONE
        progressOverlay?.visibility = View.GONE
    }

    override fun onItemClick(adapterPosition: Int, selectedCVDetail: CVDetail) {
        //showToast(applicationContext, "selected CV candidate name : ${selectedCVDetail.name}")
        val intent = Intent(this, CandidateDetailActivity::class.java)
        intent.putExtra("selected_CV", selectedCVDetail)
        startActivity(intent)
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }*/
}
