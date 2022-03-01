import cat.copernic.veterinaryapp.modelos.Perfil

/**
 * Clase interface para trabajar con la base de datos
 * Por si en el futuro queremos conectar a otra base de datos tendremos la coherencia de la interfaz con los metodos necesarios
 *
 * se le podra tambien añadir un listar, pero de momento lo dejo de esta manera.
 */
interface OperacionesDBPerfil {
    fun crear(perfil: Perfil): Boolean
    fun guardar(perfil: Perfil): Boolean //Le enviamos un perfil para guardar
    fun eliminar(perfil: Perfil): Boolean //Enviamos un perfil, en el que tendra el dato a buscar, ejemplo un perfil vacio con el dni por si la busqueda es por dni
    fun modificar(perfil: Perfil): Boolean //Los nuevos datos, el dni y datos que no varian no se deberian poder modificar
    fun buscar(perfil: Perfil): Perfil //ya especificaremos por que datos buscar...
}