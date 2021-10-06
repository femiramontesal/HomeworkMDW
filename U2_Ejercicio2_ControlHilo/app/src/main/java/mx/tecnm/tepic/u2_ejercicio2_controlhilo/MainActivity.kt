package mx.tecnm.tepic.u2_ejercicio2_controlhilo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var hilo = Hilo(this)



        button1.setOnClickListener {
            hilo.start()
        }

        button2.setOnClickListener {
            hilo.pausar()
        }


        button3.setOnClickListener {
            hilo.terminar()
        }






    }
}

class Hilo(p:MainActivity):Thread(){
    var contador =1
    val puntero = p
    var pausado = false
    var continuarCiclo = true

    fun pausar(){
        pausado = !pausado
    }

    fun terminar(){
        continuarCiclo = false
    }

    override fun run() {
        super.run()
        while (continuarCiclo){

            if (!pausado){
                puntero.runOnUiThread {
                    puntero.textView.text = "Valor hilo ${contador}"
                }
                contador++
            }//if
            sleep(200)
        }//while

    }//run
}//clase