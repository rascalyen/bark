package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.data.model.Puppy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext


interface BarkService {

  suspend fun getAllPuppiesFlow(): Flow<List<Puppy>>

  suspend fun getFirstThreePuppiesFlow(): Flow<List<Puppy>>

  suspend fun getRandomPuppyFlow(): Flow<List<Puppy>>
}

class BarkServiceImpl: BarkService {

  override suspend fun getAllPuppiesFlow(): Flow<List<Puppy>> = withContext(Dispatchers.IO) {
    flow {
      delay(2000)
      emit(DataProvider.puppyList)
    }
  }

  override suspend fun getFirstThreePuppiesFlow(): Flow<List<Puppy>> = withContext(Dispatchers.IO) {
    flow {
      delay(2000)
      emit(DataProvider.threePuppies)
    }
  }

  override suspend fun getRandomPuppyFlow(): Flow<List<Puppy>> = withContext(Dispatchers.IO) {
    flow {
      delay(500)
      emit(DataProvider.randomPuppyList)
    }
  }
}