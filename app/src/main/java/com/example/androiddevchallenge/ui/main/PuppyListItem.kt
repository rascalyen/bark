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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.DataProvider
import com.example.androiddevchallenge.data.model.Puppy
import com.example.androiddevchallenge.ui.theme.darkGraySurface
import com.example.androiddevchallenge.ui.theme.typography

@Composable
fun PuppyListItem(puppy: Puppy, navigateToProfile: (Puppy) -> Unit) {
    Card(
        modifier = Modifier  // TODO - 3. Modifier builder provides modification to compose component
            .padding(horizontal = 0.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 0.dp
    ) {
        // TODO - 4. Modifier clickable function
        Row(Modifier.clickable { navigateToProfile(puppy) }) {
            PuppyImage(puppy)
            PuppyWoof(puppy)
        }
    }
}

@Composable
private fun PuppyWoof(puppy: Puppy) {
    Column(
        modifier = Modifier
            .padding(4.dp, 8.dp, 8.dp, 8.dp)
            .fillMaxWidth()
            //.align(Alignment.CenterVertically)
            .background(
                color = if (isSystemInDarkTheme()) darkGraySurface  // TODO - 5. you can also pre-construct Color variables.
                else colorResource(R.color.light_grey),
                shape = RoundedCornerShape(0.dp, 16.dp, 16.dp, 16.dp)
            )
    ) {
        Text(text = puppy.description,
            style = typography.h6,
            modifier = Modifier.padding(8.dp))
    }
}

@Composable
private fun PuppyImage(puppy: Puppy) {
    Image(
        painter = painterResource(id = puppy.puppyImageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(50.dp)
            .clip(CircleShape)
    )
}

@Preview
@Composable
fun PreviewPuppyOne() {
    val puppy = DataProvider.puppyOne
    PuppyListItem(puppy = puppy, navigateToProfile = {})
}

@Preview
@Composable
fun PreviewPuppyTwo() {
    val puppy = DataProvider.puppyTwo
    PuppyListItem(puppy = puppy, navigateToProfile = {})
}