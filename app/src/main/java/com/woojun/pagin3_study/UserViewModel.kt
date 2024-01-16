package com.woojun.pagin3_study

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class UserViewModel(private val type: Int, private val dao: UserDao) : ViewModel() {
    var data: Flow<PagingData<User>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            maxSize = 50
        )
    ) {
        dao.allSelect()
    }.flow.cachedIn(viewModelScope)

}

class UserVMFactory(private val type: Int, private val dao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(type, dao) as T
    }
}