package com.draco.comatose.repositories.profiles

import com.draco.comatose.repositories.constants.DeviceIdleConstants

object DeviceIdleConstantsProfiles {
    val DEFAULT =             null
    const val LIGHT =         ""
    const val MODERATE =      ""
    const val HIGH =          ""
    const val EXTREME =       "${DeviceIdleConstants.LIGHT_AFTER_INACTIVE_TO}=0"
}