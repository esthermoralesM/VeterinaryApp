package cat.copernic.veterinaryapp.Objects

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Cita(
    var veterinari: String?,
    var dia: String,
    var hora: String,
    var client: String,
    var animal: String
) : Parcelable