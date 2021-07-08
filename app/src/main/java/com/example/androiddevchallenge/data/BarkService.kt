package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.data.model.Puppy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

interface BarkService {

  suspend fun getAllPuppiesFlow(): Flow<List<Puppy>>

  suspend fun getFirstThreePuppiesFlow(): Flow<List<Puppy>>

  suspend fun getRandomPuppyFlow(): Flow<Puppy>
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
      .onStart {}
      // Returns a flow that invokes the given action before each value of the upstream flow is emitted downstream.
      .onEach {}
      // Returns a flow that invokes the given action after the flow is completed or cancelled, passing the cancellation exception or failure as cause parameter of action.
      .onCompletion {}
      .flowOn(Dispatchers.IO)*/


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

}