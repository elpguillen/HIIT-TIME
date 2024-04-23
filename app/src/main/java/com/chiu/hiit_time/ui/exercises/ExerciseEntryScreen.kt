package com.chiu.hiit_time.ui.exercises

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.chiu.hiit_time.R
import com.chiu.hiit_time.ui.navigation.NavigationDestination
import com.chiu.hiit_time.ui.theme.HIITTIMETheme

object ExerciseEntryDestination : NavigationDestination {
    override val route = "exercise_entry"
    override val titleRes = R.string.exercise_entry_title
}

@Composable
fun ExerciseEntryScreen(

) {

}

@Composable
fun ExerciseEntryBody(
    modifier: Modifier = Modifier
) {

}

@Composable
fun ExerciseInputForm(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = "Enter Exercise: ",
            onValueChange = {},
            singleLine = true
        )

        OutlinedTextField(
            value = "Enter Minutes: ",
            onValueChange = {},
            singleLine = true
        )

        OutlinedTextField(
            value = "Enter Seconds: ",
            onValueChange = {},
            singleLine = true
        )

        OutlinedTextField(
            value = "Enter # of repetitions: ",
            onValueChange = {},
            singleLine = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExerciseInputForm() {
    HIITTIMETheme {
        ExerciseInputForm()
    }
}