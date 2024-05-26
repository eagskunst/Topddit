package com.eagskunst.topddit.presentation.post.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eagskunst.topddit.common.ErrorResult
import com.eagskunst.topddit.common.Mapper
import com.eagskunst.topddit.common.Success
import com.eagskunst.topddit.domain.entity.PostEntity
import com.eagskunst.topddit.domain.interactor.GetPostDetail
import com.eagskunst.topddit.presentation.post.list.Post
import kotlinx.coroutines.launch

class PostDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getPostDetail: GetPostDetail,
    private val mapper: Mapper<PostEntity, Post>,
) : ViewModel() {
    private val _postDetail: MutableLiveData<PostDetailViewState> =
        MutableLiveData(PostDetailViewState.Loading)
    val postDetail: LiveData<PostDetailViewState> = _postDetail

    fun requestPostDetail(
        subredditName: String,
        postId: String,
    ) {
        viewModelScope.launch {
            val params = GetPostDetail.Params(subredditId = subredditName, postId = postId)
            val postDetail = getPostDetail(params)
            _postDetail.value =
                when (postDetail) {
                    is ErrorResult ->
                        PostDetailViewState.GeneralError(
                            error = postDetail.throwable,
                        )

                    is Success -> {
                        val post = mapper.map(postDetail.data)
                        PostDetailViewState.Success(post)
                    }
                }
        }
    }

    fun restoreInitialState() {
        _postDetail.value = PostDetailViewState.Loading
    }
}
