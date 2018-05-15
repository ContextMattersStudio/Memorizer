package com.exgames.exmi.main.bus.events.base


abstract class ShouldPlayMusicBusObserver :
        BusObserverKotlin<ShouldPlayMusicBusObserver.ShouldPlayMusicEvent>(ShouldPlayMusicEvent::class.java) {

    data class ShouldPlayMusicEvent(val shouldPlayMusic: Boolean)
}