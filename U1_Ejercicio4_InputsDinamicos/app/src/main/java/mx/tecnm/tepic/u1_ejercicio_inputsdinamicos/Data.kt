package mx.tecnm.tepic.u1_ejercicio_inputsdinamicos

class Data(id: Int, nombre: String, domicilio: String) {


    var id=0
    var nombre =""
    var domicilio=""

    fun contenido(): String {
        return id.toString()+"&&C"+nombre+"&&C"+domicilio
    }

    fun construir(renglon: String) {
        val contenido = renglon.split("&&C")
        id = contenido[0].toString().toInt()
        nombre = contenido[1]
        domicilio = contenido[2]
    }


}