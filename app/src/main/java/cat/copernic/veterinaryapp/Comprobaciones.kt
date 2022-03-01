package cat.copernic.veterinaryapp

import java.util.regex.Pattern

class Comprobaciones {


    /**
    En esta clase podemos añadir varias comprobaciones
     * por ejemplo que una cadena contenga algo
     * que un némero este comprendido entre n valores, etc
     * */

    /**
     * Comprueba que una cadena de texto contiene algo
     *
     * @return verdadero si tiene contenido
     */
    fun contieneTexto(txt: String): Boolean {
        var resultado = false
        if (txt != null) {
            if (!txt.equals("")) {
                resultado = true
            } else {
                resultado = false
            }
        } else {
            resultado = false
        }
        return resultado
    }

    fun validaClave(key: String): Boolean {
        return key.length >= 6
    }

    fun validaCorreo(correo: String): Boolean {
        val pattern: Pattern = Pattern.compile("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+.[A-Za-z0-9]+")
        return pattern.matcher(correo).matches()
    }

    fun validaFecha(fecha: String): Boolean {
        val pattern: Pattern = Pattern.compile("[0-3][0-9]?\\/[0-1]?[0-9]\\/[0-9]{4}")
        return pattern.matcher(fecha).matches()
    }

    /**
     * Analiza si un número de telefono es valido
     *
     * @return verdadero si es valido
     */
    fun validarMovil(movil: String): Boolean {
        val pattern: Pattern = Pattern.compile("[0-9]{9}")
        return pattern.matcher(movil).matches()
    }

}