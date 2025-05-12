package com.adnroidlearningkts.recyclerview.advanced.multiitemselection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adnroidlearningkts.recyclerview.advanced.singleitemselection.Employee
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MultiViewModel @Inject constructor(): ViewModel() {

    private val _employees = MutableLiveData<List<Employee>>()
    val employees: LiveData<List<Employee>> = _employees

    private val _selectedEmployees = MutableLiveData<HashMap<Int, Employee>>()
    val selectedEmployees: LiveData<HashMap<Int, Employee>> = _selectedEmployees

    fun getAllEmployees() = viewModelScope.launch(Dispatchers.IO) {
        val employeeList = mutableListOf<Employee>()
        for(i in 1..10) {
            val employee = Employee(name = "Employee $i")
            employeeList.add(employee)
        }
        _employees.postValue(employeeList)
    }

    fun multiSelectEmployees(position: Int, emp: Employee) = viewModelScope.launch(Dispatchers.IO) {
        val currentList = _employees.value ?: return@launch

        val updateList = currentList.map {
            when(it.name) {
                emp.name -> it.copy(isChecked = !it.isChecked)
                else -> it
            }
        }
        _employees.postValue(updateList)

        var empCopyMap = _selectedEmployees.value ?: hashMapOf<Int, Employee>()
        if(empCopyMap.contains(position)) {
            empCopyMap.remove(position)
        } else {
            empCopyMap.put(position, emp.copy(isChecked = !emp.isChecked))
        }
        _selectedEmployees.postValue(empCopyMap)
    }
}