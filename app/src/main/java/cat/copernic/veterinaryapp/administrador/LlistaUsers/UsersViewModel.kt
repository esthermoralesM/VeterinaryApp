package cat.copernic.veterinaryapp.administrador.LlistaUsers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UsersViewModel: ViewModel() {

    private val repo = ReposUserData()
    fun fetchUsersData(): LiveData<MutableList<UserView>> {
        val mutabledata = MutableLiveData<MutableList<UserView>>()
        repo.getUserData().observeForever{
            mutabledata.value = it
        }
        return mutabledata
    }
}