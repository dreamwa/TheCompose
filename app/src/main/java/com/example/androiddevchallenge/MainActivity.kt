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
package com.example.androiddevchallenge

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setDisplayType("LIST")

        viewModel.displayType.observe(
            this,
            Observer {
                when (it) {
                    "LIST" -> {
                        setContent {
                            MyTheme {
                                MyList(viewModel)
                            }
                        }
                    }
                    "DETAILS" -> {
                        setContent {
                            MyTheme {
                                myDetails(viewModel)
                            }
                        }
                    }
                }
            }
        )
    }

    override fun onBackPressed() {
        if (viewModel.displayType.value == "DETAILS") {
            viewModel.setDisplayType("LIST")
        } else {
            super.onBackPressed()
        }
    }
}

// Start building your app here!
@Composable
fun MyList(viewModel: MainViewModel) {

//    Surface(color = MaterialTheme.colors.background) {
//        Text(text = "Ready... Set... GO!")
//    }

    LazyColumn(modifier = Modifier.padding(10.dp, 3.dp, 10.dp, 10.dp)) {
        val imageModifier =
            Modifier
                .height(120.dp)
                .padding(10.dp, 3.dp, 10.dp, 10.dp)
                .wrapContentWidth()
                .clip(shape = RoundedCornerShape(3.dp))

        val textModifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 5.dp, 10.dp, 5.dp)

        itemsIndexed(viewModel.imageList) { key, value ->

            Row(
                modifier = Modifier.clickable {
                    Log.e("TAG", "click,index==$key")
                    viewModel.setDisplayType("DETAILS")
                    viewModel.setIndex(key)
                }
            ) {
                Image(
                    painter = painterResource(id = value),
                    contentDescription = "image",
                    contentScale = ContentScale.Inside,
                    modifier = imageModifier
                )

                Column() {
                    Text(
                        text = "name : ${viewModel.nameList[key]}",
                        modifier = textModifier
                            .align(alignment = Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,
                        color = Color.Red

                    )
                    Text(
                        text = "age : ${viewModel.ageList[key]}",
                        modifier = textModifier
                            .align(alignment = Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,
                        color = Color.Red

                    )
                    Text(
                        text = "gender : ${viewModel.genderList[key]}",
                        modifier = textModifier
                            .align(alignment = Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,
                        color = Color.Red

                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .height(1.dp)
            )
        }
    }
}

@Composable
fun myDetails(viewModel: MainViewModel) {
    val imageModifier =
        Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(10.dp, 3.dp, 10.dp, 10.dp)
            .clip(shape = RoundedCornerShape(3.dp))

    val textModifier = Modifier
        .fillMaxWidth()
        .padding(10.dp, 5.dp, 10.dp, 5.dp)

    val typeGraphy = MaterialTheme.typography

    Column() {

        Image(
            painter = painterResource(id = viewModel.imageList[viewModel.currentIndex.value!!]),
            contentDescription = "image",
            contentScale = ContentScale.Fit,
            modifier = imageModifier
        )
        Text(
            text = "name : ${viewModel.nameList[viewModel.currentIndex.value!!]}",
            modifier = textModifier
                .align(alignment = Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            color = Color.Red,
            style = typeGraphy.h6

        )
        Text(
            text = "age : ${viewModel.ageList[viewModel.currentIndex.value!!]}",
            modifier = textModifier
                .align(alignment = Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            color = Color.Red,
            style = typeGraphy.body1

        )
        Text(
            text = "gender : ${viewModel.genderList[viewModel.currentIndex.value!!]}",
            modifier = textModifier
                .align(alignment = Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            color = Color.Red,
            style = typeGraphy.body2

        )
    }
}

// @Preview("Light Theme", widthDp = 360, heightDp = 640)
// @Composable
// fun LightPreview(viewModel: MainViewModel) {
//    MyTheme {
//        MyList(viewModel = viewModel)
//        Log.e("TAG","")
//    }
// }
//
// @Preview("Dark Theme", widthDp = 360, heightDp = 640)
// @Composable
// fun DarkPreview(viewModel: MainViewModel) {
//    MyTheme(darkTheme = true) {
//        MyList(viewModel)
//    }
// }
