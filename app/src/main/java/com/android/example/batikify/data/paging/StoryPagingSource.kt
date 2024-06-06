package com.android.example.batikify.data.paging

//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.android.example.batikify.data.api.BatikApiService
//import com.android.example.batikify.data.response.DataItemEncyclopedia
//import com.dicoding.picodiploma.loginwithanimation.data.api.ListStoryItem
//import com.dicoding.picodiploma.loginwithanimation.data.api.StoryApiService
//
//class StoryPagingSource(private val apiService: BatikApiService) : PagingSource<Int, DataItemEncyclopedia> (){
//
//    private companion object {
//        const val INITIAL_PAGE_INDEX = 1
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItemEncyclopedia> {
//        return try {
//            val position = params.key ?: INITIAL_PAGE_INDEX
//            val responseData = apiService.getEncyclopedia(position, params.loadSize)
//            LoadResult.Page(
//                data = responseData.listStory,
//                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
//                nextKey = if (responseData.listStory.isEmpty()) null else position + 1
//            )
//        } catch (exception: Exception) {
//            return LoadResult.Error(exception)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, ListStoryItem>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
//    }
//}