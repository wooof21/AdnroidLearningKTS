package com.adnroidlearningkts.recyclerview.advanced.singleitemselection

data class Employee(val name: String, var isChecked: Boolean = false) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true // Same object reference
        if (javaClass != other?.javaClass) return false // Not the same class

        other as Employee // Cast 'other' to Employee

        if (name != other.name) return false // Compare 'name'
        if (isChecked != other.isChecked) return false // Compare 'isChecked'

        return true // All relevant properties are equal
    }

    /**
     * The implementation follows a common pattern to generate a hash code based on the object's
     * relevant properties (name and isChecked).
     *
     * var result = name.hashCode(): Start with the hash code of the first property.
     *
     * result = 31 * result + isChecked.hashCode(): Combine the hash code with the hash code of the next property,
     *  using a multiplier (31 is a common prime number used for this purpose).
     *  This process is repeated for all properties that are considered in the equals() method.
     *
     * Returning a consistent hash code for equal objects is crucial for
     * collections like HashSet and HashMap to function correctly.
     * If two objects are equal according to equals(), their hashCode() must return the same value.
     */
    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + isChecked.hashCode()
        return result
    }
}
