package cat.copernic.veterinaryapp


/**
 * ¡¡ De momento se ha decidido no utilizar esta clase!!
 * Utilizaremos un String simple para la dirección, dejo la clase por si en el futuro ampliamos
 *
 *
 * La clase DireccionClass almacenará los datos de la direccion, de momento se permiten los datos null por comodidad a la hora de crear la aplicacion
 * despues se podran poner datos obligatorios.
 *
 */
class DireccionClass(ciudad: String? = null, cp: String? = null, direc: String? = null, piso: Integer? = null, puerta: String? = null) {
    init {
        //Aqui por si hay que inicializar algun dato
    }
}