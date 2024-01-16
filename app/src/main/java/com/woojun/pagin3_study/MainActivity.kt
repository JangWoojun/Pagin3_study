package com.woojun.pagin3_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.woojun.pagin3_study.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: UserViewModel

    companion object{
        const val TYPE_ROOM = 0
        const val TYPE_RETROFIT = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        binding.dataList.adapter = adapter
        val db = RoomDB.getInstance(applicationContext)

        viewModel = ViewModelProvider(this, UserVMFactory(
            intent.getIntExtra("type",TYPE_ROOM), db.userDao()
        ))[UserViewModel::class.java]

        lifecycleScope.launch {
            viewModel.data.collectLatest { adapter.submitData(it) }
        }
    }
}