package com.example.androiddevchallenge.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.BarkService
import com.example.androiddevchallenge.data.BarkServiceImpl
import com.example.androiddevchallenge.data.model.Puppy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * https://developer.android.com/jetpack/compose/state
 */
class BarkViewModel : ViewModel() {

  private val barkService: BarkService = BarkServiceImpl()

  private val _spinner = MutableLiveData(false)
  val spinner: LiveData<Boolean>
    get() = _spinner

  //private val _puppies = MutableLiveData<List<Puppy>>(DataProvider.puppyList)    // for preview
  private val _puppies = MutableLiveData<List<Puppy>>(mutableListOf())
  val puppies: LiveData<List<Puppy>>
    get() = _puppies

  private val _puppyFlow = MutableStateFlow<List<Puppy>>(mutableListOf())
  val puppyFlow: StateFlow<List<Puppy>>
    get() = _puppyFlow


  init {
    //loadPuppiesOldWay { barkService.getAllPuppiesFlow() }

    loadPuppiesFlowWay { barkService.getAllPuppiesFlow() }
  }

  @ExperimentalCoroutinesApi
  private fun loadPuppiesFlowWay(block: suspend () -> Flow<List<Puppy>>) {
    _puppyFlow.mapLatest {
      _spinner.value = true
      block()
    }
      //.flowOn(Dispatchers.IO)
      .onStart { /* Do sth before transformation. in this case: mapLatest */ }
      .onEach { /* Do sth in between each data emission and transformation */
        _spinner.value = false
      }
      .onCompletion { /* Do sth when entire transformation is done. */ }
      .catch {
        _spinner.value = false
      }
      .launchIn(viewModelScope)
  }


  private fun loadPuppiesOldWay(block: suspend () -> Flow<List<Puppy>>): Job {

    return viewModelScope.launch {
      try {
        _spinner.value = true

        block()
          .collect {
            _puppies.value = it
          }
      } catch (error: Throwable) {
        _spinner.value = false
      } finally {
        _spinner.value = false
      }
    }
  }

}