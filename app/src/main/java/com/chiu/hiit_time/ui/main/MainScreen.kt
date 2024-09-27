package com.chiu.hiit_time.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chiu.hiit_time.ui.theme.HIITTIMETheme

@Composable
fun MainScreen(
    modifier: Modifier
) {
     Column(
         modifier = Modifier
             .padding(horizontal = 8.dp)
     ) {
        Text(text = "Genshin Character Info Screen", modifier = Modifier.padding(vertical = 8.dp))
        Text(text = "Character Name", modifier = Modifier.padding(vertical = 8.dp))
        Text(text = "Character Element", modifier = Modifier.padding(vertical = 8.dp))
        Text(text = "Character Region", modifier = Modifier.padding(vertical = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    HIITTIMETheme {
        MainScreen(modifier = Modifier)
    }
}