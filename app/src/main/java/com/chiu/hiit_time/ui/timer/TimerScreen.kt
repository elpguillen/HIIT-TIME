package com.chiu.hiit_time.ui.timer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.chiu.hiit_time.ui.AppViewModelProvider

@Composable
fun TimerScreen(
    modifier: Modifier,
    viewModel: ViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.Factory)
) {
    
}