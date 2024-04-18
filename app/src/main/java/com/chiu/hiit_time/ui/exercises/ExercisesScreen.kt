package com.chiu.hiit_time.ui.exercises

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.chiu.hiit_time.R
import com.chiu.hiit_time.data.entities.Exercise
import com.chiu.hiit_time.ui.AppViewModelProvider
import com.chiu.hiit_time.ui.navigation.NavigationDestination
import com.chiu.hiit_time.ui.theme.HIITTIMETheme

object ExercisesDestination : NavigationDestination {
    override val route = "exercises"
    override val titleRes = R.string.exercise_title
}

@Composable
fun ExercisesScreen(
    modifier: Modifier,
    viewModel: ViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.exercise_entry_title)
                )
            }
        }
    ) {
        innerPadding ->
        ExercisesBody(
            exercises = listOf(),
            onItemClick = {},
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun ExercisesBody(
    exercises: List<Exercise>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        ExerciseList(
            exercises = exercises,
            onItemClick = { onItemClick(it.id) },
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
        )
    }
}

@Composable
fun ExerciseList(exercises: List<Exercise>, onItemClick: (Exercise) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = exercises, key = {it.id}) {item: Exercise ->
            ExerciseItem(
                exercise = item,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { }
            )
        }
    }
}

@Composable
fun ExerciseItem(exercise: Exercise, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_large))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = exercise.exerciseName)
            }
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Timer: ${exercise.exerciseLength.toString()}")
                Text(text = "Break: ${exercise.restTime.toString()}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExerciseItem() {
    val exercise = Exercise(1, "squats", 100, 50, 50)
    HIITTIMETheme {
        ExerciseItem(exercise = exercise)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExercisesBody() {
    val squats = Exercise(1, "squats", 1,1,1)
    val hiit = Exercise(2,"hiit", 2000, 200, 1)

    HIITTIMETheme {
        ExercisesBody(exercises = listOf(squats, hiit), onItemClick = {})
    }
}