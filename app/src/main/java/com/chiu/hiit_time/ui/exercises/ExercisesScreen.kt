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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chiu.hiit_time.R
import com.chiu.hiit_time.data.entities.Exercise
import com.chiu.hiit_time.ui.AppViewModelProvider
import com.chiu.hiit_time.ui.navigation.NavigationDestination
import com.chiu.hiit_time.ui.theme.HIITTIMETheme
import com.chiu.hiit_time.ui.utils.convertTimesToSeconds
import com.chiu.hiit_time.ui.utils.formatSecondsToTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

object ExercisesDestination : NavigationDestination {
    override val route = "exercises"
    override val titleRes = R.string.exercise_title
}

@Composable
fun ExercisesScreen(
    modifier: Modifier,
    navigateToExerciseEntry: () -> Unit,
    viewModel: ExercisesViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.Factory)
) {
    val defaultExercises: MutableList<Exercise> = mutableListOf(
        Exercise(1, "squats", 1000, 1000, 5, 1, 2,5),
        Exercise(2, "lunge", 2000, 1000, 10, 1, 2, 5),
        Exercise(3, "hip thrust", 3000, 1000, 15, 3, 4, 2)
    )

    val exercises = remember {
        mutableStateListOf<Exercise>(
        )
    }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {

        defaultExercises.forEach {exercise ->
            viewModel.addExercise(exercise)
        }

        viewModel.getAllExercises().collect() { exerciseList ->
            for (exercise in exerciseList) {
                exercises.add(exercise)
            }
        }

        defaultExercises.forEach {exercise ->
            exercises.add(exercise)
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToExerciseEntry,
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
            exercises = exercises,
            onItemClick = {},
            coroutineScope = coroutineScope,
            viewModel = viewModel,
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
    coroutineScope: CoroutineScope,
    viewModel: ExercisesViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 16.dp)
    ) {
        ExerciseList(
            exercises = exercises,
            onItemClick = { onItemClick(it.id) },
            coroutineScope = coroutineScope,
            viewModel = viewModel,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
        )
    }
}

@Composable
fun ExerciseList(
    exercises: List<Exercise>,
    onItemClick: (Exercise) -> Unit,
    coroutineScope: CoroutineScope,
    viewModel: ExercisesViewModel,
    modifier: Modifier = Modifier)
{
    LazyColumn(modifier = modifier) {
        items(items = exercises, key = {it.id}) {item: Exercise ->
            ExerciseItem(
                exercise = item,
                onDelete = {
                    coroutineScope.launch {
                        viewModel.deleteExercise(item)
                    }
                },
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { }
            )
        }
    }
}

@Composable
fun ExerciseItem(
    exercise: Exercise,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
        ) {
            ExerciseItemTitle(exercise = exercise)
            ExerciseItemBody(
                exercise = exercise,
                onDelete = {
                    onDelete()
                }
            )
        }
    }
}

@Composable
fun ExerciseItemTitle(
    exercise: Exercise,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = exercise.exerciseName)
    }
}

@Composable
fun ExerciseItemBody(
    exercise: Exercise,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var  deleteConfirmationRequired by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = "Timer: ${formatSecondsToTime(convertTimesToSeconds(exercise.exerciseHours, exercise.exerciseMinutes, exercise.exerciseSeconds))}",
                modifier = Modifier


            )
            Text(
                text = "Break: ${formatSecondsToTime(convertTimesToSeconds(0, exercise.restMinutes, exercise.restSeconds))}",
                modifier = Modifier


            )
            Text(
                text = "Sets: ${exercise.numSets}",
                modifier = Modifier
            )
        }

        Column(
            modifier = Modifier
        ) {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "Start"
                )
            }
        }

        Column {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Edit"
                )
            }

            IconButton(onClick = { deleteConfirmationRequired = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "Delete"
                )
            }
        }
    }

    if (deleteConfirmationRequired) {
        DeleteConfirmationDialog(
            onDeleteConfirm = {
                deleteConfirmationRequired = false
                onDelete()
            },
            onDeleteCancel = { deleteConfirmationRequired = false }
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        title = { Text(text = stringResource(id = R.string.delete_confirmation_alert)) },
        text = { Text(text = stringResource(id = R.string.delete_confirmation_question)) },
        modifier = modifier,
        onDismissRequest = { /* Do nothing*/ },
        confirmButton = {
            TextButton(onClick = { onDeleteConfirm() }) {
                Text(text = stringResource(id = R.string.yes))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDeleteCancel() }) {
                Text(text = stringResource(id = R.string.no))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewExerciseItem() {
    val exercise = Exercise(1, "squats", 100, 50, 50, 5, 3, 1)
    HIITTIMETheme {
        ExerciseItem(exercise = exercise, onDelete = {} )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExercisesBody() {
    val squats = Exercise(1, "squats", 1,1,1, 2, 3, 5)
    val hiit = Exercise(2,"hiit", 2000, 200, 1, 6, 1, 5)

    HIITTIMETheme {
        ExercisesBody(exercises = listOf(squats, hiit), coroutineScope = rememberCoroutineScope(), viewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.Factory), onItemClick = {})
    }
}