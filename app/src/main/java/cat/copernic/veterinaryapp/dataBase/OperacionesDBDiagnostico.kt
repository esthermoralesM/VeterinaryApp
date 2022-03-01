package cat.copernic.veterinaryapp.dataBase

import cat.copernic.veterinaryapp.modelos.Diagnostico

interface OperacionesDBDiagnostico{
    fun guardar(diagnostico: Diagnostico): Boolean //Le enviamos un diagnostico para guardar
    fun eliminar(diagnostico: Diagnostico): Boolean //Enviamos un diagnostico, en el que tendra el dato a buscar, ejemplo un diagnostico
    fun modificar(diagnostico: Diagnostico): Boolean //Los nuevos datos, el id no se deveria modificar
    fun buscar(diagnostico: Diagnostico): Boolean //ya especificaremos por que datos buscar...
}