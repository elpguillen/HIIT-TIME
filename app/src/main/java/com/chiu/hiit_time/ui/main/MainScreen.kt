package com.chiu.hiit_time.ui.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.chiu.hiit_time.ui.theme.HIITTIMETheme

@Composable
fun MainScreen(
    modifier: Modifier
) {
    Text(text = "Genshin Character Info Screen")
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    HIITTIMETheme {
        MainScreen(modifier = Modifier)
    }
}