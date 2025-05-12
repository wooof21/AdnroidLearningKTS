package com.adnroidlearningkts.recyclerview.advanced.singleitemselection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleViewModel @Inject constructor(): ViewModel() {

    private val _employees = MutableLiveData<List<Employee>>()
    val employees: LiveData<List<Employee>> = _employees

    private val _selectedEmployee = MutableLiveData<Employee?>()
    val selectedEmployee: LiveData<Employee?> = _selectedEmployee

    fun getAllEmployees() = viewModelScope.launch(Dispatchers.IO) {
        val employeeList = mutableListOf<Employee>()
        for(i in 1..10) {
            val employee = Employee(name = "Employee $i")
            employeeList.add(employee)
        }
        _employees.postValue(employeeList)
    }

    fun singleSelectEmployee(selEmp: Employee) = viewModelScope.launch(Dispatchers.IO) {
        // Get the current list or return if null
        val currentList = _employees.value ?: return@launch

        /**
         * map{}: iterates over the original list and applies a transformation function
         *      to each element to produce a new list. It does not modify the original list or
         *      the original objects within the list in place.
         *
         * Data Class copy: The correct way to create a modified version of a data class object
         *      when using functional transformations like map is to use the copy() method.
         *      The copy() method creates a new instance of the data class with specified properties changed,
         *      while keeping the other properties the same. This new instance is then returned
         *      by the map lambda and included in the updateList.
         */
        val updateList = currentList.map {

            when(it.name) {
                selEmp.name -> it.copy(isChecked = !it.isChecked)
                _selectedEmployee.value?.name -> it.copy(isChecked = !it.isChecked)
                else -> it
            }
        }

        _employees.postValue(updateList)

        // clear the selectedEmployee if the click Employee is the same, otherwise, post the new value
        if(_selectedEmployee.value?.name == selEmp.name) {
            _selectedEmployee.postValue(null)
        } else {
            _selectedEmployee.postValue(selEmp)
        }
    }
}