package com.example.contactsmanagerapp.ViewModel

//Use observable of the data-binding library not the android one.
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsmanagerapp.room.User
import com.example.contactsmanagerapp.room.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository): ViewModel(), Observable {
    val users = repository.users
    private var isUpdateOrDelete = false
    private lateinit var userToUpdateOrDelete : User

    //Getter accessor method of observable class.
    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate(){
        val name = inputName.value!!
        val email = inputEmail.value!!

        insert(User(0, name, email))

        inputName.value = null
        inputEmail.value = null
    }

    fun clearAllorDelete(){
        clearAll()
    }

    fun insert(user:User) = viewModelScope.launch{
        repository.insert(user)
    }
    fun clearAll() = viewModelScope.launch{
        repository.deleteAll()
    }
    fun update(user: User) = viewModelScope.launch{
        repository.update(user)
    }
    fun delete(user: User) = viewModelScope.launch{
        repository.delete(user)
    }

    //Not doing anything because we have used the nullable.
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}