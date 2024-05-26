package com.eagskunst.topddit.domain.interactor

import com.eagskunst.topddit.common.DataResult
import com.eagskunst.topddit.common.thread.CoroutineDispatchers
import com.eagskunst.topddit.data.datasource.PostDataSource
import com.eagskunst.topddit.domain.BaseInteractor
import com.eagskunst.topddit.domain.entity.PostEntity

class GetPostDetail(
    private val dataSource: PostDataSource,
    dispatchers: CoroutineDispatchers,
) : BaseInteractor(dispatchers) {
    data class Params(
        val subredditId: String,
        val postId: String,
    )

    suspend operator fun invoke(params: Params): DataResult<PostEntity> {
        return switchToIoWithResult {
            dataSource.getPostDetail(subredditId = params.subredditId, postId = params.postId)
        }
    }
}
