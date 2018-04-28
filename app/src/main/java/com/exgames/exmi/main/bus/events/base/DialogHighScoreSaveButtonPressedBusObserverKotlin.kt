package com.exgames.exmi.main.bus.events.base


abstract class DialogHighScoresSaveButtonPressedBusObserverKotlin :
        BusObserverKotlin<DialogHighScoresSaveButtonPressedBusObserverKotlin.DialogHighScoresSaveButtonPressedEvent>(DialogHighScoresSaveButtonPressedEvent::class.java) {

    data class DialogHighScoresSaveButtonPressedEvent(val userName: String)
}
