package com.adnroidlearningkts.recyclerview.advanced.multiviewtype

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MultiTypeViewModel @Inject constructor(): ViewModel() {

    private val _employees = MutableLiveData<List<EmployeeMultiType>>()
    val employees: LiveData<List<EmployeeMultiType>> = _employees

    fun getAllEmployees() = viewModelScope.launch(Dispatchers.IO) {
        val emp1 = EmployeeWithPhone("John", "Toronto", "+123423453456")
        val emp2 = EmployeeWithEmail("jack", "New York", "jack@gmail.com")
        val emp3 = EmployeeWithPhone("Ryan", "London", "+0123456789")
        val emp4 = EmployeeWithEmail("Joe", "Calgary", "joe@gmail.com")
        val employeeList = listOf(emp1, emp2, emp3, emp4)

        _employees.postValue(employeeList)
    }
}