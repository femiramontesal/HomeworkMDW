package mx.tecnm.tepic.u1_ejercicio_inputsdinamicos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    //declaramos los ArL
    val vector = ArrayList<Data>()
    val nombres = ArrayList<String>()

    var id = campoId.text.toString().toInt()
    var nombre = campoNombre.text.toString()
    var domicilio = campoDomicilio.text.toString()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        btnEliminar.setOnClickListener {
            eliminar()
        }


        btnInsertar.setOnClickListener{
            insertar()
        }

        btnGuardar.setOnClickListener{
           //
        }



    }

    fun insertar(){




        //cualquiera de las 2 jala
        //val datos: Data = Data(id,nombre,domicilio)
        //vector.add(datos)

        vector.add(Data(id,nombre,domicilio))


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
                // aquí va la función que elimina
                d.dismiss()
            }
            .setNegativeButton("Cancelar"){d,i->
                d.cancel()
            }
            .show()

    }



}