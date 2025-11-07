package com.griffith.shakealarmclockapp.ui.theme.setting


class SettingsRepository {
    private var snoozerDuration: Int = 5
    private var alarmVolume: Float = 50.0F
    var SnoozeDuration: Int
        get() {
            return snoozerDuration
        }
        set(value) {
            print(value)
            snoozerDuration = value
        }

    var AlarmVolume: Float
        get() {
            return alarmVolume
        }
        set(value) {
            print(value)
            alarmVolume = value
        }
}