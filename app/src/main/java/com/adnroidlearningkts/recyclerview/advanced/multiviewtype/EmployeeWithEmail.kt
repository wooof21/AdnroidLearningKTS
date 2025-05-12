package com.adnroidlearningkts.recyclerview.advanced.multiviewtype

/**
 * Data class inheritance
 *
 * Kotlin data classes cannot directly inherit from other classes.
 *
 * Therefore, cannot create a data class EmployeeWithEmail that extends the
 *  data class EmployeeMultiType in the traditional sense.
 *
 * To make the EmployeeWithEmail the subclass of EmployeeMultiType
 *
 *  EmployeeWithEmail class inheriting from EmployeeMultiType (cannot be a data class)
 *
 *  To initialize:
 *      val baseEmployee: Employee = Employee("Charlie Brown", "789 Pine St")
 *      val employeeWithEmail: Employee = EmployeeWithEmail( // Can be assigned to an Employee variable
 *         "Alice Smith",
 *         "123 Main St",
 *         "alice.smith@example.com"
 *      )
 *      println(employeeWithEmail is Employee) // true (EmployeeWithEmail is a subtype of Employee)
 */
class EmployeeWithEmail(
    name: String,
    address: String,
    val email: String): EmployeeMultiType(name, address) {
    // might need to manually override equals, hashCode, toString if needed
    // for EmployeeWithEmail if want structural equality behavior.
    }
