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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.data.model.Puppy
import com.example.androiddevchallenge.ui.profile.ProfileActivity
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {

    private lateinit var barkViewModel: BarkViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        barkViewModel = BarkViewModel()

        setContent {
            MyTheme {
                MyApp(barkViewModel) {
                    startActivity(ProfileActivity.newIntent(this, it))
                }
            }
        }
    }
}

@Composable
fun MyApp(barkVM: BarkViewModel, navigateToProfile: (Puppy) -> Unit) {
    Scaffold(  // TODO - 1. it's a material design layout

        content = {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()) {
                LoadingCircle(barkVM.spinner.observeAsState())
            }
            BarkHomeContent(
                puppyListState = barkVM.puppies.observeAsState(),
                //puppyListState = barkVM.puppyFlow.collectAsState(),
                navigateToProfile = navigateToProfile)
        },
        floatingActionButton = {
            BarkFab()
        }
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(BarkViewModel()) { }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(BarkViewModel()) { }
    }
}
