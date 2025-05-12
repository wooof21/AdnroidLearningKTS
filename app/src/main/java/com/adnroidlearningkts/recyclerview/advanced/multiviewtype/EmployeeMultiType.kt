package com.adnroidlearningkts.recyclerview.advanced.multiviewtype

// Base class (must be 'open' to be inherited from)
open class EmployeeMultiType(
    val name: String,
    val address: String
) {
    // might need to manually override equals, hashCode, toString if needed
    // for this base class, as it's not a data class.
}
