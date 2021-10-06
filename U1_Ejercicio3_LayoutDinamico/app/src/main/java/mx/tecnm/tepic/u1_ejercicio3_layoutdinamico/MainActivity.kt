package mx.tecnm.tepic.u1_ejercicio3_layoutdinamico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        borrarObjetos()



    }

    fun borrarObjetos(){
        linearl.removeAllViews()
        val limpiar = Button(this)
        val volver = Button(this)

        limpiar.setText("Limpiar ")
        volver.setText("Generar campos")

        linearl.addView(limpiar)
        linearl.addView(volver)

        limpiar.setOnClickListener{
            //Toast.makeText(this,"Mensaje Dinamico ${campoTexto.text.toString()}",Toast.LENGTH_LONG).show()
            borrarObjetos()
        }

        volver.setOnClickListener{
            pideObjetos()
        }

    }

    fun pideObjetos(){
        val cantidad = EditText(this)
        cantidad.inputType = InputType.TYPE_CLASS_NUMBER
        cantidad.setHint("Introduce un numero")

        AlertDialog.Builder(this)
            .setTitle("Atención")
            .setMessage("¿Cuantos campos quiere agregar?")
            .setView(cantidad)
            .setPositiveButton("Generar"){d,i->
                generarCamposTextoDinamicos(cantidad.text.toString().toInt())
                d.dismiss()
            }
            .setNegativeButton("Cancelar"){d,i->
                d.cancel()
            }
            .show()
    }//Pide objetos


    fun generarCamposTextoDinamicos(valor:Int){
        val etiqueta = TextView(this)
        etiqueta.setText("Capture los valores")
        linearl.addView(etiqueta)

        val vectorCamposTexto = ArrayList<EditText>()
        var total = (valor -1).toInt()
        (0..total).forEach{
            val campoTexto = TextView(this)
            campoTexto.text
            vectorCamposTexto.add(campoTexto)
            campoTexto.setHint("Valor ${it+1}")
            linearl.addView(campoTexto)
        }

        val botonSumar = Button(this)
        botonSumar.setText("Sumar")

        botonSumar.setOnClickListener{
            var nerror = 0
            try {
                var suma = 0
                (0..total).forEach {
                    var campo = vectorCamposTexto.get(it)
                    nerror = 0
                    nerror = it+1
                    suma = suma + campo.text.toString().toInt()

                }
                Toast.makeText(this ,"La suma es: ${suma}", Toast.LENGTH_LONG).show()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "El campo ${nerror} está vacío", Toast.LENGTH_SHORT).show()
            }


        }
        linearl.addView(botonSumar)

        val botonRegresar = Button(this)
        botonRegresar.setText("Regresar")
        botonRegresar.setOnClickListener{finish()}

    }




}



