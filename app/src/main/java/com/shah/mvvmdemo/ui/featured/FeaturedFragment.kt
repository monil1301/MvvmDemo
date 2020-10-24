package com.shah.mvvmdemo.ui.featured

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shah.mvvmdemo.data.network.UserApi
import com.shah.mvvmdemo.data.repository.UserRepository
import com.shah.mvvmdemo.data.response.CourseDetails
import com.shah.mvvmdemo.databinding.FragmentFeaturedBinding
import com.shah.mvvmdemo.ui.course.CourseDetailActivity
import com.shah.mvvmdemo.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class FeaturedFragment : BaseFragment<FeaturedViewModel, FragmentFeaturedBinding, UserRepository>(), InnerRecyclerAdapter.OnClick{

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val categoryList = fragmentViewModel.getCategoryList()
        binding.parentRecyclerView.adapter = ParentRecyclerAdapter(requireContext(), categoryList,this)
    }

    override fun getViewModel() = FeaturedViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFeaturedBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = apiBuilder.buildApi(UserApi::class.java,token)
        return UserRepository(api)
    }

    override fun gotoDetails(course: CourseDetails) {
        val intent = Intent(requireContext(), CourseDetailActivity::class.java)
            .putExtra("course",course)
        startActivity(intent)
    }
}