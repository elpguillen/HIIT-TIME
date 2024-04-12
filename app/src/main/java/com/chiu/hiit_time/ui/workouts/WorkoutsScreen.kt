package com.chiu.hiit_time.ui.workouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.chiu.hiit_time.R
import com.chiu.hiit_time.data.entities.Workout
import com.chiu.hiit_time.ui.AppViewModelProvider
import com.chiu.hiit_time.ui.theme.HIITTIMETheme

@Composable
fun WorkoutsScreen(
    modifier: Modifier,
    viewModel: ViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold { innerPadding ->
        WorkoutsBody(
            workouts = listOf(),
            onItemClick = {},
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun WorkoutsBody(
    workouts: List<Workout>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(

    ) {
        WorkoutList(workouts = workouts, onItemClick = {})
    }
}

@Composable
fun WorkoutList(workouts: List<Workout>, onItemClick: (Workout) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = workouts, key = {it.id}) { item: Workout ->
            WorkoutItem(
                workout = item,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { }
            )
        }
    }
}

@Composable
fun WorkoutItem(workout: Workout, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Text(
            text = workout.name,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWorkoutItem() {
    HIITTIMETheme {
        WorkoutItem(workout = Workout(1, "30-min full body"))
    }
}