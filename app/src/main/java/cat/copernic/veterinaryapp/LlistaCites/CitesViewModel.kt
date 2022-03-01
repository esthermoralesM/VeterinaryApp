package cat.copernic.veterinaryapp.LlistaCites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cat.copernic.veterinaryapp.Objects.Cita

class CitesViewModel: ViewModel() {

    private val repo = ReposCitesData()
    fun fetchUsersData(diaSelec: String): LiveData<MutableList<Cita>> {
        val mutabledata = MutableLiveData<MutableList<Cita>>()
        repo.getUserData(diaSelec).observeForever{
            mutabledata.value = it
        }
        return mutabledata
    }
}