package com.chiu.hiit_time.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chiu.hiit_time.R
import com.chiu.hiit_time.ui.AppViewModelProvider
import com.chiu.hiit_time.ui.theme.HIITTIMETheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemScreen: (String) -> Unit,
    modifier: Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .padding(top = 20.dp)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {innerPadding ->
        HomeBody(
            menuList = listOf("Timer", "Exercises", "Workouts"),
            onItemClick = navigateToItemScreen,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun HomeBody(
    menuList: List<String>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        MenuList(
            menuList = menuList,
            onItemClick = { onItemClick(it)},
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
        )
    }
}

@Composable
fun MenuItem(menuItemName: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_large)),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = menuItemName)
        }
    }
}

@Composable
fun MenuList(menuList: List<String>, onItemClick: (String) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = menuList, key = {it}) { item ->
            MenuItem(
                menuItemName = item,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(item) }
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuItemPreview() {
    HIITTIMETheme {
        MenuItem(menuItemName = "Timer")
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyPreview() {
    HIITTIMETheme {
        HomeBody(menuList = listOf("Start", "Pause", "Reset"), onItemClick = {})
    }
}