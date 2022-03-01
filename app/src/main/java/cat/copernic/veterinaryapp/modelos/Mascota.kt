package cat.copernic.veterinaryapp.modelos

data class Mascota (
    var nom: String = "",
    var rassa: String = "",
    var dataNaixement: String?="",
    var numCgip:String="",
    var pes:String="",
    var duenyo: String= ""
)