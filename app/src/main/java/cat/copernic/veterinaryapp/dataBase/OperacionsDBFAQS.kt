package cat.copernic.veterinaryapp.dataBase

import cat.copernic.veterinaryapp.modelos.FAQS

interface OperacionsDBFAQS {

    fun guardar(faqs: FAQS): Boolean
    fun eliminar(faqs: FAQS): Boolean
    fun modificar(faqs: FAQS): Boolean
    fun buscar(faqs: FAQS): Boolean
}