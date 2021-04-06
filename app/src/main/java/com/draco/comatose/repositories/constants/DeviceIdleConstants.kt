package com.draco.comatose.repositories.constants

object DeviceIdleConstants {
    /**
     * Time after being inactive to enter light idle mode
     */
    const val LIGHT_AFTER_INACTIVE_TO =             "light_after_inactive_to"

    /**
     * Time after we would like to go idle until we actually do (wait for apps to finish)
     */
    const val LIGHT_PRE_IDLE_TO =                   "light_pre_idle_to"

    /**
     * Time to run in idle maintenance mode
     */
    const val LIGHT_IDLE_TO =                       "light_idle_to"

    /**
     * Scale factor to apply to LIGHT_IDLE_TO after each cycle
     */
    const val LIGHT_IDLE_FACTOR =                   "light_idle_factor"

    /**
     * Max time to run in idle maintenance mode
     */
    const val LIGHT_MAX_IDLE_TO =                   "light_max_idle_to"

    /**
     * Minimum time to make available for maintenance before exiting
     */
    const val LIGHT_IDLE_MAINTENANCE_MIN_BUDGET =   "light_idle_maintenance_min_budget"

    /**
     * Maximum time to make available for maintenance before exiting
     */
    const val LIGHT_IDLE_MAINTENANCE_MAX_BUDGET =   "light_idle_maintenance_max_budget"

    /**
     * Minimum time to stay in light idle mode after light doze so that we do not exit maintenance mode too quickly
     */
    const val MIN_LIGHT_MAINTENANCE_TIME =          "min_light_maintenance_time"

    /**
     * Minimum time to stay in light idle mode after deep doze so that we do not exit maintenance mode too quickly
     */
    const val MIN_DEEP_MAINTENANCE_TIME =           "min_deep_maintenance_time"

    /**
     * How long we will wait to get a good location before idling
     */
    const val LOCATING_TO =                         "locating_to"

    /**
     * Desired maximum accuracy (in meters) of location before idling
     */
    const val LOCATION_ACCURACY =                   "location_accuracy"

    /**
     * Should the user still be in idle mode until unlock, or as soon as the screen turns on?
     */
    const val WAIT_FOR_UNLOCK =                     "wait_for_unlock"

    /**
     * Pre idle time factor to make idle delay longer
     */
    const val PRE_IDLE_FACTOR_LONG =                "pre_idle_factor_long"

    /**
     * Pre idle time factor to make idle delay shorter
     */
    const val PRE_IDLE_FACTOR_SHORT =               "pre_idle_factor_short"

    /**
     * Time after being inactive to check if device is not in motion
     */
    const val INACTIVE_TO =                         "inactive_to"

    /**
     * Time after not noticing any motion to enter the inactive state
     */
    const val SENSING_TO =                          "sensing_to"

    /**
     * Time after noticing motion to start looking for motion again
     */
    const val MOTION_INACTIVE_TO =                  "motion_inactive_to"

    /**
     * Time after INACTIVE_TO that we will check for motion
     */
    const val IDLE_AFTER_INACTIVE_TO =              "idle_after_inactive_to"

    /**
     * Initial time after being idle that we can start regular maintenance again
     */
    const val IDLE_PENDING_TO =                     "idle_pending_to"

    /**
     * Maximum IDLE_PENDING_TO allowed
     */
    const val MAX_IDLE_PENDING_TO =                 "max_idle_pending_to"

    /**
     * Scale factor to apply to IDLE_PENDING_TO after each cycle
     */
    const val IDLE_PENDING_FACTOR =                 "idle_pending_factor"

    /**
     * Time in quick doze to wait for tasks to finish
     */
    const val QUICK_DOZE_DELAY_TO =                 "quick_doze_delay_to"

    /**
     * Initial time to be in idle state before returning to pending idle
     */
    const val IDLE_TO =                             "idle_to"

    /**
     * Maximum IDLE_TO time
     */
    const val MAX_IDLE_TO =                         "max_idle_to"

    /**
     * Scale factor to apply to IDLE_TO after each cycle
     */
    const val IDLE_FACTOR =                         "idle_factor"

    /**
     * Minimum time until next alarm to enter idle mode
     */
    const val MIN_TIME_TO_ALARM =                   "min_time_to_alarm"

    /**
     * Max time to whitelist an app if it has high priority
     */
    const val MAX_TEMP_APP_WHITELIST_DURATION =     "max_temp_app_whitelist_duration"

    /**
     * Time to whitelist an app receiving SMS
     */
    const val SMS_TEMP_APP_WHITELIST_DURATION =     "sms_temp_app_whitelist_duration"

    /**
     * Time to whitelist an app receiving MMS
     */
    const val MMS_TEMP_APP_WHITELIST_DURATION =     "mms_temp_app_whitelist_duration"

    /**
     * Max time to whitelist an app that is handling pending intents
     */
    const val NOTIFICATION_WHITELIST_DURATION =     "notification_whitelist_duration"
}