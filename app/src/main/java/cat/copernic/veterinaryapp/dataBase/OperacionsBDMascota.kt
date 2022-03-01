package cat.copernic.veterinaryapp.dataBase

import cat.copernic.veterinaryapp.modelos.Mascota

interface OperacionsBDMascota {

    fun guardar(mascota: Mascota): Boolean
    fun eliminar(mascota: Mascota): Boolean
    fun modificar(mascota: Mascota): Boolean
    fun buscar(mascota: Mascota): Boolean
}