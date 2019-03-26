package com.my.cvapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.my.cvapp.model.CVDetail

/**
 * Created by Tejas Shah on 26/03/19.
 */
class CandidateDetailActivity : BaseActivity(){

    private var selectedCVDetail: CVDetail? = null
    private var listCandidateSkills: List<String>? = null

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate_detail)

        //TODO - need to implement butterknife
        val imgCandidateProfile = findViewById<ImageView>(R.id.imgCandidateProfile)
        val tvCandidateName = findViewById<TextView>(R.id.tvCandidateName)
        val tvCandidateDesignation = findViewById<TextView>(R.id.tvCandidateDesignation)
        val tvCandidateAddress = findViewById<TextView>(R.id.tvCandidateAddress)
        val tvProfessionalSummarySupportingText = findViewById<TextView>(R.id.tvProfessionalSummarySupportingText)
        val tvTechnicalSkillTitleSupportingText = findViewById<TextView>(R.id.tvTechnicalSkillTitleSupportingText)

        selectedCVDetail = intent.getParcelableExtra("selected_CV")

        tvCandidateName.setText(selectedCVDetail!!.name)
        tvCandidateDesignation.setText(selectedCVDetail!!.label)
        tvCandidateAddress.setText(selectedCVDetail!!.location!!.address+","+selectedCVDetail!!.location!!.city+","+selectedCVDetail!!.location!!.postalCode)
        tvProfessionalSummarySupportingText.setText(selectedCVDetail!!.summary)

        if(selectedCVDetail!!.skills!!.size > 0){
            listCandidateSkills = selectedCVDetail!!.skills!!.get(0)!!.keywords as List<String>?
            val skills: String = listCandidateSkills.toString().replace("[", "").replace("]", "").trim()
            tvTechnicalSkillTitleSupportingText.setText(skills)
        }

        Glide.with(imgCandidateProfile.context).load(selectedCVDetail!!.thumbnailpicture).into(imgCandidateProfile)

    }

}