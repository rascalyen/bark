package com.example.androiddevchallenge.ui.main

sealed class BarkEvent {

  object InitialWoofEvent: BarkEvent()

  object WoofEvent: BarkEvent()

  class SelectOptionEvent(which: Int = 0): BarkEvent()
}