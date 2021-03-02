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

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val imageList = arrayListOf<Int>(
        R.mipmap.dog1,
        R.mipmap.dog2,
        R.mipmap.dog3,
        R.mipmap.dog4,
        R.mipmap.dog5,
        R.mipmap.dog6,
        R.mipmap.dog7,
        R.mipmap.dog8,
        R.mipmap.dog9
    )
    val ageList = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val nameList = arrayListOf<String>(
        "Aba",
        "Adali",
        "Badia",
        "Cain",
        "Dabria",
        "Ebony",
        "Freda",
        "Georgia",
        "Ingrid"
    )
    val genderList = arrayListOf<String>(
        "male",
        "male",
        "male",
        "male",
        "female",
        "female",
        "female",
        "female",
        "female"
    )

    var displayType = MutableLiveData<String>()
    var currentIndex = MutableLiveData<Int>()

    fun setDisplayType(type: String) {
        displayType.value = type
    }

    fun setIndex(index: Int) {
        currentIndex.value = index
    }
}
