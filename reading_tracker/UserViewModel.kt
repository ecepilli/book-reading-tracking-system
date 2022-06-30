package com.example.reading_tracker

import androidx.lifecycle.*
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class UserViewModel(val dao: UserDao) : ViewModel() {
    var newUserName = ""
    var newUserMail = ""
    var newUserPassword = ""
    var userEmail = ""
    var userPasswordd = ""
    private val users = dao.getAll()
    private val _isNull = MutableLiveData<Boolean>()
    val isNull: LiveData<Boolean>
        get() = _isNull



    val usersString = Transformations.map(users) { users ->
        formatUsers(users)
    }

    fun addUser() {
        viewModelScope.launch {
            val user = Users()
            user.userName = newUserName
            user.userMail = newUserMail
            user.user_password = newUserPassword
            dao.insert(user)
        }
    }

   fun formatUsers(users: List<Users>): String {
        return users.fold("") { str, item ->

        str + '\n' + formatUser(item)
        }
    }

    fun formatUser(users: Users): String {
        var str = "ID: ${users.userId}"
        str += '\n' + "Username: ${users.userName}"
        str += '\n' + "Mail: ${users.userMail}"
        str += '\n' + "Password: ${users.user_password}" + '\n'
        return str
    }
    fun checkUser() {
        viewModelScope.launch {
            val user = dao.getUser(userEmail, userPasswordd)

            if (user == null) {
                _isNull.value = true
            } else {
                _isNull.value = false
            }
        }
    }

}

