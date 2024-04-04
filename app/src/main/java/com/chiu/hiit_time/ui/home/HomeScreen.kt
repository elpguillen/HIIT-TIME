package com.chiu.hiit_time.ui.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chiu.hiit_time.ui.AppViewModelProvider
import com.chiu.hiit_time.ui.theme.HIITTIMETheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemScreen: () -> Unit,
    modifier: Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .padding(top = 20.dp)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {innerPadding ->
        Text(text = "hello", modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun MenuItem(itemName: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row {
            Text(text = itemName)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuItemPreview() {
    HIITTIMETheme {
        MenuItem(itemName = "Timer")
    }
}