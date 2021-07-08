package com.example.androiddevchallenge.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.BarkService
import com.example.androiddevchallenge.data.BarkServiceImpl
import com.example.androiddevchallenge.data.DataProvider
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

  //private val _puppies = MutableLiveData(DataProvider.puppyList)    // for preview
  private val _puppies = MutableLiveData<List<Puppy>>(mutableListOf())
  val puppies: LiveData<List<Puppy>>
    get() = _puppies

  private val _puppyFlow = MutableStateFlow<List<Puppy>>(mutableListOf())
  val puppyFlow: StateFlow<List<Puppy>>
    get() = _puppyFlow


  init {
    loadPuppiesOldWay { barkService.getAllPuppiesFlow() }

    //loadPuppiesFlowWay { barkService.getFirstThreePuppiesFlow() }
  }

  private fun loadPuppiesOldWay(block: suspend () -> Flow<List<Puppy>>): Job {

    return viewModelScope.launch {
      try {
        _spinner.value = true

        block()
          .collect {
            _puppies.value = it
            _puppyFlow.value = it
          }
      } catch (error: Throwable) {
        _spinner.value = false
      } finally {
        _spinner.value = false
      }
    }
  }

  @ExperimentalCoroutinesApi
  private fun loadPuppiesFlowWay(block: suspend () -> Flow<List<Puppy>>): Job {

    return viewModelScope.launch {
      block()
        .onStart { _spinner.value = true }
        .catch { _spinner.value = false }
        .onCompletion { _spinner.value = false }
        .collect {
          _puppies.value = it
          _puppyFlow.value = it
        }
    }
  }


  fun handleBarkEvents(event: BarkEvent) {

    when (event) {
      is BarkEvent.WoofEvent -> { addOneBark() }

      is BarkEvent.SelectOptionEvent -> { /* Nothing to do yet */  }

      is BarkEvent.InitialWoofEvent -> { loadPuppiesFlowWay { barkService.getFirstThreePuppiesFlow() } }
    }
  }

  private fun addOneBark() {
    viewModelScope.launch {
      barkService.getRandomPuppyFlow()
        .onStart { _spinner.value = true }
        .catch { _spinner.value = false }
        .onCompletion { _spinner.value = false }
        .collect { puppy ->

          val listToBe: MutableList<Puppy> = _puppies.value?.toMutableList() ?: mutableListOf()
          listToBe.add(puppy)

          _puppies.value = listToBe
          _puppyFlow.value = listToBe
        }
    }
  }
}