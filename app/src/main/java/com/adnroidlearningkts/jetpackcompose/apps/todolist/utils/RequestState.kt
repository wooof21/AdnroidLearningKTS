package com.adnroidlearningkts.jetpackcompose.apps.todolist.utils

/**
 * sealed class: used for representing states or results where want to ensure all possible outcomes are handled.
 *  Core Concept: Restricted Class Hierarchies
 *      - The primary purpose of a sealed class (or interface) is to restrict inheritance.
 *      - When declare a class as sealed, its telling the Kotlin compiler that all its direct subclasses
 *          are known at compile time and are defined within the same module and package
 *      - No other subclasses can be declared elsewhere.
 *  * Exhaustiveness in when Expressions: This is the most significant benefit.
 *      When use a `when` expression to handle an instance of a sealed class,
 *      the compiler can check if all possible direct subclasses were covered.
 *      - If handled all cases: the `when` expression doesn't need an else branch.
 *          The compiler knows there are no other possibilities.
 *      - If missed a case: The compiler will send a warning (or error out, depending on settings)
 *          that the `when` expression is not exhaustive.
 *          This is incredibly helpful for preventing bugs when add new subclasses to the sealed hierarchy later
 *              – the compiler will remind to update the `when` statements.
 *
 * <out T>: generic type parameter T
 *  - It indicates that a generic type can only be produced (output) by a class or interface, not consumed (input)
 *
 * object Idle: RequestState<Nothing>(): a singleton object Idle which is a direct subclass of RequestState.
 *  - Nothing: a special type in Kotlin that has no instances and is a subtype of all other types.
 *      * used since Idle doesn't carry any specific data
 *
 * data class Success<T>(val data: T): RequestState<T>():
 *  - The Success state holds the actual data of type T that was successfully fetched or processed.
 *
 * To use: e.g.
 *
 *      fun handleRequestState(state: RequestState<String>) {
 *         when (state) {
 *             is RequestState.Idle -> { /* Handle idle state */ }
 *             is RequestState.Loading -> { /* Handle loading state */ }
 *             is RequestState.Success -> {
 *                 // Access state.data (which is a String here)
 *                 println("Success: ${state.data}")
 *             }
 *             is RequestState.Error -> {
 *                 // Access state.error
 *                 println("Error: ${state.error.message}")
 *             }
 *         }
 *     }
 */
sealed class RequestState<out T> {

    object Idle: RequestState<Nothing>()
    object Loading: RequestState<Nothing>()

    data class Success<T>(val data: T): RequestState<T>()
    data class Error(val error: Throwable): RequestState<Nothing>()
}