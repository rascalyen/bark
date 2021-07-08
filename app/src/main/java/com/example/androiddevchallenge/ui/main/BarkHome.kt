/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.model.Puppy
import com.example.androiddevchallenge.ui.theme.purple500

@Composable
fun BarkHomeContent(puppyListState: State<List<Puppy>?>, navigateToProfile: (Puppy) -> Unit) {
    // TODO - 2. compose version of recyclerView
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = puppyListState.value.orEmpty(),
            itemContent = {
                PuppyListItem(puppy = it, navigateToProfile)
            }
        )
    }
}

@Composable
fun LoadingCircle(loadingState: State<Boolean?>) {
    if (loadingState.value == true)  CircularProgressIndicator()
}

@Composable
fun BarkFab() {
    FloatingActionButton(
        onClick = { /* TODO - ADD one woof */ },
        modifier = Modifier
            .padding(16.dp)
            .padding()
            .height(48.dp)
            .widthIn(min = 48.dp),
        contentColor = Color.White,
        backgroundColor = purple500
    ) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = stringResource(R.string.woof)
        )
    }
}
