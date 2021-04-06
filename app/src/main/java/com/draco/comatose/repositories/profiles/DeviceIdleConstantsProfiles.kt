package com.draco.comatose.repositories.profiles

import com.draco.comatose.repositories.constants.DeviceIdleConstants

object DeviceIdleConstantsProfiles {
    val DEFAULT =             null

    /**
     * - Instantly enter light idle 2 minutes after the screen turns off
     * - Give apps 2 minutes to prepare for idle
     * - Handle light idle maintenance once every 10 minutes initially
     * - Handle light idle maintenance once every hour at the latest
     * - Take at most 2 minutes to fetch a location
     * - Take a location within at least 50 meters
     * - Enter deep idle 10 minutes after the screen goes off
     * - Enter inactive mode 2 minutes after finishing sensor work
     * - Re-enter deep idle 5 minutes after the device stops moving again
     * - Re-enter deep idle 5 minute if we are in a pending idle state
     */
    const val LIGHT =
        "${DeviceIdleConstants.LIGHT_AFTER_INACTIVE_TO}=${2 * 60 * 1000}," +
        "${DeviceIdleConstants.LIGHT_PRE_IDLE_TO}=${2 * 60 * 1000}," +
        "${DeviceIdleConstants.LIGHT_IDLE_TO}=${10 * 60 * 1000}," +
        "${DeviceIdleConstants.LIGHT_MAX_IDLE_TO}=${60 * 60 * 1000}," +
        "${DeviceIdleConstants.LOCATING_TO}=${2 * 60 * 1000}," +
        "${DeviceIdleConstants.LOCATION_ACCURACY}=${50}," +
        "${DeviceIdleConstants.INACTIVE_TO}=${10 * 60 * 1000}," +
        "${DeviceIdleConstants.SENSING_TO}=${2 * 60 * 1000}," +
        "${DeviceIdleConstants.MOTION_INACTIVE_TO}=${5 * 60 * 1000}," +
        "${DeviceIdleConstants.IDLE_AFTER_INACTIVE_TO}=${5 * 60 * 1000}"

    /**
     * - Instantly enter light idle 1 minute after the screen turns off
     * - Give apps 1 minute to prepare for idle
     * - Handle light idle maintenance once every 15 minutes initially
     * - Handle light idle maintenance once every 3 hours at the latest
     * - Take at most 1 minute to fetch a location
     * - Take a location within at least 100 meters
     * - Enter deep idle 1 minute after the screen goes off
     * - Enter inactive mode 1 minute after finishing sensor work
     * - Re-enter deep idle 1 minute after the device stops moving again
     * - Re-enter deep idle 1 minute if we are in a pending idle state
     * - Handle deep idle maintenance once every 2 hours initially
     * - Handle deep idle maintenance once every 8 hours at the latest
     * - Give apps 30 seconds to prepare for quick doze
     * - Wake up 30 minutes before an alarm
     */
    const val MODERATE =
        "${DeviceIdleConstants.LIGHT_AFTER_INACTIVE_TO}=${60 * 1000}," +
        "${DeviceIdleConstants.LIGHT_PRE_IDLE_TO}=${60 * 1000}," +
        "${DeviceIdleConstants.LIGHT_IDLE_TO}=${15 * 60 * 1000}," +
        "${DeviceIdleConstants.LIGHT_MAX_IDLE_TO}=${3 * 60 * 60 * 1000}," +
        "${DeviceIdleConstants.LOCATING_TO}=${60 * 1000}," +
        "${DeviceIdleConstants.LOCATION_ACCURACY}=${100}," +
        "${DeviceIdleConstants.INACTIVE_TO}=${60 * 1000}," +
        "${DeviceIdleConstants.SENSING_TO}=${60 * 1000}," +
        "${DeviceIdleConstants.MOTION_INACTIVE_TO}=${60 * 1000}," +
        "${DeviceIdleConstants.IDLE_AFTER_INACTIVE_TO}=${60 * 1000}," +
        "${DeviceIdleConstants.IDLE_TO}=${2 * 60 * 60 * 1000}," +
        "${DeviceIdleConstants.MAX_IDLE_TO}=${8 * 60 * 60 * 1000}," +
        "${DeviceIdleConstants.QUICK_DOZE_DELAY_TO}=${30 * 1000}," +
        "${DeviceIdleConstants.MIN_TIME_TO_ALARM}=${30 * 60 * 1000}"

    /**
     * - Instantly enter light idle 5 seconds after the screen turns off
     * - Give apps 30 seconds to prepare for idle
     * - Handle light idle maintenance once every 30 minutes initially
     * - Handle light idle maintenance once every 6 hours at the latest
     * - Take at most 10 seconds to fetch a location
     * - Take a location within at least 500 meters
     * - Enter deep idle 30 seconds after the screen goes off
     * - Enter inactive mode 30 seconds after finishing sensor work
     * - Re-enter deep idle 30 seconds after the device stops moving again
     * - Re-enter deep idle 30 seconds if we are in a pending idle state
     * - Handle deep idle maintenance once every 4 hours initially
     * - Handle deep idle maintenance once every 12 hours at the latest
     * - Give apps 10 seconds to prepare for quick doze
     * - Wake up 10 minutes before an alarm
     */
    const val HIGH =
        "${DeviceIdleConstants.LIGHT_AFTER_INACTIVE_TO}=${5 * 1000}," +
        "${DeviceIdleConstants.LIGHT_PRE_IDLE_TO}=${30 * 1000}," +
        "${DeviceIdleConstants.LIGHT_IDLE_TO}=${30 * 60 * 1000}," +
        "${DeviceIdleConstants.LIGHT_MAX_IDLE_TO}=${6 * 60 * 60 * 1000}," +
        "${DeviceIdleConstants.LOCATING_TO}=${10 * 1000}," +
        "${DeviceIdleConstants.LOCATION_ACCURACY}=${500}," +
        "${DeviceIdleConstants.INACTIVE_TO}=${30 * 1000}," +
        "${DeviceIdleConstants.SENSING_TO}=${30 * 1000}," +
        "${DeviceIdleConstants.MOTION_INACTIVE_TO}=${30 * 1000}," +
        "${DeviceIdleConstants.IDLE_AFTER_INACTIVE_TO}=${30 * 1000}," +
        "${DeviceIdleConstants.IDLE_TO}=${4 * 60 * 60 * 1000}," +
        "${DeviceIdleConstants.MAX_IDLE_TO}=${12 * 60 * 60 * 1000}," +
        "${DeviceIdleConstants.QUICK_DOZE_DELAY_TO}=${10 * 1000}," +
        "${DeviceIdleConstants.MIN_TIME_TO_ALARM}=${10 * 60 * 1000}"

    /**
     * - Instantly enter light idle as soon as screen goes off
     * - Give apps 5 seconds to prepare for idle
     * - Handle light idle maintenance once every 1 hour initially
     * - Handle light idle maintenance once every 12 hours at the latest
     * - Take at most 5 seconds to fetch a location
     * - Take a location within at least 1000 meters
     * - Instantly enter deep idle as soon as screen goes off
     * - Instantly enter inactive mode after finishing sensor work
     * - Re-enter deep idle as soon as the device stops moving again
     * - Instantly enter deep idle if we are in a pending idle state
     * - Handle deep idle maintenance once every 6 hours initially
     * - Handle deep idle maintenance once every 48 hours at the latest
     * - Give apps 5 seconds to prepare for quick doze
     * - Wake up 5 minutes before an alarm
     */
    const val EXTREME =
        "${DeviceIdleConstants.LIGHT_AFTER_INACTIVE_TO}=${0}," +
        "${DeviceIdleConstants.LIGHT_PRE_IDLE_TO}=${5 * 1000}," +
        "${DeviceIdleConstants.LIGHT_IDLE_TO}=${60 * 60 * 1000}," +
        "${DeviceIdleConstants.LIGHT_MAX_IDLE_TO}=${12 * 60 * 60 * 1000}," +
        "${DeviceIdleConstants.LOCATING_TO}=${5 * 1000}," +
        "${DeviceIdleConstants.LOCATION_ACCURACY}=${1000}," +
        "${DeviceIdleConstants.INACTIVE_TO}=${0}," +
        "${DeviceIdleConstants.SENSING_TO}=${0}," +
        "${DeviceIdleConstants.MOTION_INACTIVE_TO}=${0}," +
        "${DeviceIdleConstants.IDLE_AFTER_INACTIVE_TO}=${0}," +
        "${DeviceIdleConstants.IDLE_TO}=${6 * 60 * 60 * 1000}," +
        "${DeviceIdleConstants.MAX_IDLE_TO}=${48 * 60 * 60 * 1000}," +
        "${DeviceIdleConstants.QUICK_DOZE_DELAY_TO}=${5 * 1000}," +
        "${DeviceIdleConstants.MIN_TIME_TO_ALARM}=${5 * 60 * 1000}"
}
