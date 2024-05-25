package com.eagskunst.topddit.domain.interactor

import com.eagskunst.topddit.common.DataResult
import com.eagskunst.topddit.common.thread.CoroutineDispatchers
import com.eagskunst.topddit.data.datasource.PostDataSource
import com.eagskunst.topddit.domain.BaseInteractor
import com.eagskunst.topddit.domain.entity.PostEntity

class GetPosts(
    private val dataSource: PostDataSource,
    dispatchers: CoroutineDispatchers,
) : BaseInteractor(dispatchers) {
    suspend operator fun invoke(lastPostId: String?): DataResult<List<PostEntity>> {
        return switchToIoWithResult {
            dataSource.getPosts(lastPostId)
        }
    }
}
