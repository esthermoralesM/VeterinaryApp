package cat.copernic.veterinaryapp.client.LlistaMascotas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MascotasViewModel: ViewModel() {
    private val repo = ReposMascotaData()
        fun fetchUsersData(): LiveData<MutableList<MascotaView>> {
            val mutabledata = MutableLiveData<MutableList<MascotaView>>()
            repo.getMascotaData().observeForever{
                mutabledata.value = it
            }
            return mutabledata
        }


}