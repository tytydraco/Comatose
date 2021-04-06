package com.draco.comatose.models

import com.draco.comatose.repositories.constants.DeviceIdleConstants

data class DeviceIdleConstantsConfig(
    var inactiveTo: Long,
    var sensingTo: Long,
    var motionInactiveTo: Long,
    var idleActiveInactiveTo: Long,
    var idlePendingTo: Long,
    var maxIdlePendingTo: Long,
    var idlePendingFactor: Float,
    var quickDozeDelayTo: Long,
    var idleTo: Long,
    var maxIdleTo: Long,
    var idleFactor: Float,
    var minTimeToAlarm: Long,
    var maxTempAppWhitelistDuration: Long,
    var notificationWhitelistDuration: Long,
) {
    override fun toString(): String {
        return  "${DeviceIdleConstants.INACTIVE_TO}=$inactiveTo," +
                "${DeviceIdleConstants.SENSING_TO}=$sensingTo," +
                "${DeviceIdleConstants.MOTION_INACTIVE_TO}=$motionInactiveTo," +
                "${DeviceIdleConstants.IDLE_AFTER_INACTIVE_TO}=$idleActiveInactiveTo," +
                "${DeviceIdleConstants.IDLE_PENDING_TO}=$idlePendingTo," +
                "${DeviceIdleConstants.MAX_IDLE_PENDING_TO}=$maxIdlePendingTo," +
                "${DeviceIdleConstants.IDLE_PENDING_FACTOR}=$idlePendingFactor," +
                "${DeviceIdleConstants.QUICK_DOZE_DELAY_TO}=$quickDozeDelayTo," +
                "${DeviceIdleConstants.IDLE_TO}=$idleTo," +
                "${DeviceIdleConstants.MAX_IDLE_TO}=$maxIdleTo," +
                "${DeviceIdleConstants.IDLE_FACTOR}=$idleFactor," +
                "${DeviceIdleConstants.MIN_TIME_TO_ALARM}=$minTimeToAlarm," +
                "${DeviceIdleConstants.MAX_TEMP_APP_WHITELIST_DURATION}=$maxTempAppWhitelistDuration," +
                "${DeviceIdleConstants.NOTIFICATION_WHITELIST_DURATION}=$notificationWhitelistDuration"
    }

    fun import(string: String) {
        val keyValueMap = string.split(",").associate {
            val (key, value) = it.split("=")
            key to value
        }

        for ((key, value) in keyValueMap) {
            when (key) {
                DeviceIdleConstants.INACTIVE_TO -> inactiveTo = value.toLong()
                DeviceIdleConstants.SENSING_TO -> sensingTo = value.toLong()
                DeviceIdleConstants.MOTION_INACTIVE_TO -> motionInactiveTo = value.toLong()
                DeviceIdleConstants.IDLE_AFTER_INACTIVE_TO -> idleActiveInactiveTo = value.toLong()
                DeviceIdleConstants.IDLE_PENDING_TO -> idlePendingTo = value.toLong()
                DeviceIdleConstants.MAX_IDLE_PENDING_TO -> maxIdlePendingTo = value.toLong()
                DeviceIdleConstants.IDLE_PENDING_FACTOR -> idlePendingFactor = value.toFloat()
                DeviceIdleConstants.QUICK_DOZE_DELAY_TO -> quickDozeDelayTo = value.toLong()
                DeviceIdleConstants.IDLE_TO -> idleTo = value.toLong()
                DeviceIdleConstants.MAX_IDLE_TO -> maxIdleTo = value.toLong()
                DeviceIdleConstants.IDLE_FACTOR -> idleFactor = value.toFloat()
                DeviceIdleConstants.MIN_TIME_TO_ALARM -> minTimeToAlarm = value.toLong()
                DeviceIdleConstants.MAX_TEMP_APP_WHITELIST_DURATION -> maxTempAppWhitelistDuration = value.toLong()
                DeviceIdleConstants.NOTIFICATION_WHITELIST_DURATION -> notificationWhitelistDuration = value.toLong()
            }
        }
    }
}