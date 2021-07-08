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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.model.Puppy
import com.example.androiddevchallenge.ui.theme.purple500
import kotlinx.coroutines.launch

@Composable
fun BarkHomeContent(puppyListState: State<List<Puppy>?>, navigateToProfile: (Puppy) -> Unit) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    // TODO - 2. compose version of recyclerView
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(16.dp, 8.dp, 16.dp, 40.dp)
    ) {
        items(
            items = puppyListState.value.orEmpty(),
            itemContent = {
                PuppyListItem(puppy = it, navigateToProfile)
            }
        )

        scope.launch {
            listState.animateScrollToItem(Int.MAX_VALUE)
        }
    }
}

@Composable
fun LoadingCircle(loadingState: State<Boolean?>) {
    if (loadingState.value == true)  CircularProgressIndicator()
}

@Composable
fun BarkFab(barkVM: BarkViewModel) {
    FloatingActionButton(
        onClick = { barkVM.handleBarkEvents(BarkEvent.WoofEvent) },
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
