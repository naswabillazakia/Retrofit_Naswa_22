package com.example.retrofit_naswa_22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofit_naswa_22.api.RetrofitClient
import com.example.retrofit_naswa_22.model.indonesiaresponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showindonesia()
    }


    private fun showindonesia() {
        RetrofitClient.instance.getIndonesia()
            .enqueue(object : Callback<ArrayList<indonesiaresponse>> {
                override fun onResponse(
                    call: Call<ArrayList<indonesiaresponse>>,
                    response: Response<ArrayList<indonesiaresponse>>
                ) {
                   val indonesia : indonesiaresponse? = response.body()?.get(0)
                    val positive :String? = indonesia?.positif
                    val hospitilized : String? = indonesia?.dirawat
                    val recover :String? = indonesia?.sembuh
                    val death: String? = indonesia?.meninggal


                    tvPositive.text = positive
                    tvHospitalized.text = hospitilized
                    tvRecover.text = recover
                    tvDeath.text = death
                }

                override fun onFailure(call: Call<ArrayList<indonesiaresponse>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }

            })

    }

}




