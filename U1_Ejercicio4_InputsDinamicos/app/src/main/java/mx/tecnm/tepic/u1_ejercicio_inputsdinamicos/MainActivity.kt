package mx.tecnm.tepic.u1_ejercicio_inputsdinamicos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.InputType
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity() {
    //declaramos los ArL
    val vector = ArrayList<Data>() //Guardar los vectores a manera de objeto
    val nombres = ArrayList<String>() //mostrar en el list view




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnInsertar.setOnClickListener{
            insertar()
        }


        btnEliminar.setOnClickListener {
            eliminar()
        }



        btnGuardar.setOnClickListener{
           guardar()
        }

        btnAbrir.setOnClickListener{
           var linea = abrir()
            expandirContenido(linea)
        }



    }

    private fun expandirContenido(contenido: String) {
        var renglones = contenido.split("&&R")

        vector.clear()
        for (renglon in renglones){
            var objetoData = Data(0,"","")
            objetoData.construir(renglon)
            vector.add(objetoData)
            actualizarLista()
        }
    }

    private fun abrir():String {
        try {
            val tarjetaSD = getExternalFilesDir(null)
            val file = File(tarjetaSD!!.absolutePath,"archivo.txt")
            val flujoArchivo = BufferedReader(InputStreamReader(FileInputStream(file)))

            var linea = flujoArchivo.readLine()
            flujoArchivo.close()
            return linea

        }catch (io:IOException){
            AlertDialog.Builder(this).setTitle("Error")
                .setMessage("Mensaje ${io.message}")
                .setPositiveButton("Aceptar"){d,i->d.dismiss()}
                .show()
            return ""
        }
    }

    private fun guardar() {
        var dataVector =""
        var total = vector.size-1

        (0..total).forEach {
            val objetoData = vector.get(it)
            dataVector+= objetoData.contenido()+"&&R"
        }
        dataVector = dataVector.substring(0,dataVector.lastIndexOf("&&R"))

        try {
            val tarjetaSD = getExternalFilesDir(null)
            val file = File(tarjetaSD!!.absolutePath,"archivo.txt")
            val flujoArchivo = OutputStreamWriter(FileOutputStream(file))

            flujoArchivo.write(dataVector)
            flujoArchivo.flush()
            flujoArchivo.close()

            AlertDialog.Builder(this).setTitle("Exito")
                .setMessage("Si se gaurdo")
                .setPositiveButton("Aceptar"){d,i->d.dismiss()}
                .show()
        }catch (io:IOException){
            Toast.makeText(this,"Error ${io.message}",Toast.LENGTH_LONG).show()
        }
    }

    private fun insertar(){

        var objetoDatos = Data(0,"","")

        objetoDatos.id   = campoId.text.toString().toInt()
        objetoDatos.nombre = campoNombre.text.toString()
        objetoDatos.domicilio = campoDomicilio.text.toString()


        vector.add(objetoDatos)

        actualizarLista()
        campoDomicilio.setText("")
        campoId.setText("")
        campoNombre.setText("")

    }

    private fun actualizarLista() {
        nombres.clear()
        var total = vector.size-1

        (0..total).forEach {
            nombres.add(vector[it].nombre)
        }

        lista.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres)


        lista.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this,"Diste click en ${vector[i].id}",Toast.LENGTH_LONG).show()
        }
    }

    fun eliminar(){

        val cantidad = EditText(this)
        cantidad.inputType = InputType.TYPE_CLASS_NUMBER
        cantidad.setHint("Introduce un numero")

        AlertDialog.Builder(this)
            .setTitle("Atención")
            .setMessage("¿Cuál campo quiere eliminar?")
            .setView(cantidad)
            .setPositiveButton("Eliminar"){d,i->
                vector.removeAt(cantidad.text.toString().toInt()-1)
                actualizarLista()
                d.dismiss()
            }
            .setNegativeButton("Cancelar"){d,i->
                d.cancel()
            }
            .show()

    }



}