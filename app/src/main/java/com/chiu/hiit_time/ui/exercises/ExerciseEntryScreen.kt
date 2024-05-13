package com.chiu.hiit_time.ui.exercises

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chiu.hiit_time.HiitTopAppBar
import com.chiu.hiit_time.R
import com.chiu.hiit_time.ui.AppViewModelProvider
import com.chiu.hiit_time.ui.navigation.NavigationDestination
import com.chiu.hiit_time.ui.theme.HIITTIMETheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

object ExerciseEntryDestination : NavigationDestination {
    override val route = "exercise_entry"
    override val titleRes = R.string.exercise_entry_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: ExerciseEntryViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            HiitTopAppBar(
                title = stringResource(ExerciseEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        ExerciseEntryBody(
            exerciseUiState = viewModel.exerciseUiState,
            onItemValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveExercise()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun ExerciseEntryBody(
    exerciseUiState: ExerciseItemUiState,
    onItemValueChange: (ExerciseDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large))
    ) {
        ExerciseInputForm(
            exerciseDetails = exerciseUiState.exerciseDetails,
            modifier,
            onValueChange = onItemValueChange
        )

        Button(
            onClick = { onSaveClick() },
        ) {
            Text(text = stringResource(id = R.string.save_action_button))
        }
    }
}

@Composable
fun ExerciseInputForm(
    exerciseDetails: ExerciseDetails,
    modifier: Modifier = Modifier,
    onValueChange: (ExerciseDetails) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = exerciseDetails.exerciseName,
            onValueChange = { onValueChange(exerciseDetails.copy(exerciseName = it))},
            label = { Text(text = stringResource(id = R.string.exercise_name_label))},
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            singleLine = true,
            enabled = enabled
        )

        OutlinedTextField(
            value = exerciseDetails.exerciseHours,
            onValueChange = { onValueChange(exerciseDetails.copy(exerciseHours = it))},
            label = { Text(text = stringResource(id = R.string.exercise_hours_label))},
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            singleLine = true,
            enabled = enabled
        )

        OutlinedTextField(
            value = exerciseDetails.exerciseMinutes,
            onValueChange = { onValueChange(exerciseDetails.copy(exerciseMinutes = it)) },
            label = { Text(text = stringResource(id = R.string.exercise_minutes_label)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            singleLine = true,
            enabled = enabled
        )

        OutlinedTextField(
            value = exerciseDetails.exerciseSeconds,
            onValueChange = { onValueChange(exerciseDetails.copy(exerciseSeconds = it)) },
            label = { Text(text = stringResource(id = R.string.exercise_seconds_label)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            singleLine = true,
            enabled = enabled
        )

        OutlinedTextField(
            value = exerciseDetails.restMinutes,
            onValueChange = { onValueChange(exerciseDetails.copy(restMinutes = it))},
            label = { Text(text = stringResource(id = R.string.exercise_rest_minutes_label))},
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            singleLine = true,
            enabled = enabled
        )

        OutlinedTextField(
            value = exerciseDetails.restSeconds,
            onValueChange = { onValueChange(exerciseDetails.copy(restSeconds = it))},
            label = { Text(text = stringResource(id = R.string.exercise_rest_seconds_label))},
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            singleLine = true,
            enabled = enabled
        )

        OutlinedTextField(
            value = exerciseDetails.numSets,
            onValueChange = { onValueChange(exerciseDetails.copy(numSets = it))},
            label = { Text(text = stringResource(id = R.string.exercise_rep_amount_label))},
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            singleLine = true,
            enabled = enabled
        )
    }
}