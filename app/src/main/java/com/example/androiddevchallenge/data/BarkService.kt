package com.example.androiddevchallenge.data

import android.util.Log
import com.example.androiddevchallenge.data.model.Puppy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

interface BarkService {

  suspend fun getAllPuppiesFlow(): Flow<List<Puppy>>

  suspend fun getFirstThreePuppiesFlow(): Flow<List<Puppy>>

  fun getFirstThreePuppiesFlowNotSuspended(): Flow<List<Puppy>>

  suspend fun getRandomPuppyFlow(): Flow<Puppy>

  suspend fun giveMeFourPuppies(): Flow<List<Puppy>>
}

/**
 * https://developer.android.com/kotlin/flow
 */
class BarkServiceImpl: BarkService {

  /* commented area is sequential example of flow */

  override suspend fun getAllPuppiesFlow(): Flow<List<Puppy>> =
    withContext(Dispatchers.IO) {
      flow {
        delay(2000)
        emit(DataProvider.puppyList)
      }
    }
    /*flow {
      delay(2000)
      emit(DataProvider.puppyList)
    }
      // Returns a flow that invokes the given action before this flow starts to be collected.
      .onStart { Log.w("###", "onStart to get all puppies flow") }
      // Returns a flow that invokes the given action before each value of the upstream flow is emitted downstream.
      .onEach {}
      // Returns a flow that invokes the given action after the flow is completed or cancelled, passing the cancellation exception or failure as cause parameter of action.
      .onCompletion { Log.w("###", "onCompleted getting all puppies flow") }
      .flowOn(Dispatchers.IO)*/


  override fun getFirstThreePuppiesFlowNotSuspended(): Flow<List<Puppy>> =
    flow {
      delay(2000)
      emit(DataProvider.threePuppies)
    }
      .flowOn(Dispatchers.IO)


  override suspend fun getFirstThreePuppiesFlow(): Flow<List<Puppy>> =
    withContext(Dispatchers.IO) {
      flow {
        delay(2000)
        emit(DataProvider.threePuppies)
      }
    }
    /*getAllPuppiesFlow().map { puppyList -> puppyList.filter { puppy -> puppy.id < 4 } }*/


  override suspend fun getRandomPuppyFlow(): Flow<Puppy> =
    withContext(Dispatchers.IO) {
      flow {
        delay(500)
        emit(DataProvider.randomPuppy())
      }
    }
    /*getAllPuppiesFlow().map { puppyList -> puppyList.random() }*/


  override suspend fun giveMeFourPuppies(): Flow<List<Puppy>> =
    getRandomPuppyFlow().map { puppy ->
      getFirstThreePuppiesFlow().firstOrNull()!!.toMutableList().apply { add(puppy) }
    }
      .onStart { Log.w("###", "Start time = " + System.currentTimeMillis()) }
      .onCompletion { Log.w("###", "Complete time = " + System.currentTimeMillis()) }
    // this flow takes 500 + 2000 = 2500 milliseconds
}