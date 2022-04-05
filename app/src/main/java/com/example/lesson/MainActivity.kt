package com.example.lesson

import android.app.*
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*


class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calendar = Calendar.getInstance()

        /**Создание DatePickerDialog */
        val y = calendar.get(Calendar.YEAR)
        val m = calendar.get(Calendar.MONTH)
        val d = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                Snackbar.make(binding.root, "year: $year, month: $month, day: $day", Snackbar.LENGTH_SHORT).show()
            }, y, m, d)


        /**Создание AlertDialog */
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.apply {
            setTitle("Подтвердите действие")
            setMessage("Вы точно хотите удалить данный файл?")
            setPositiveButton("Удалить",
                DialogInterface.OnClickListener { dialogInterface, id ->
                    Snackbar.make(binding.root, "Вы удалили файл", Snackbar.LENGTH_SHORT).show()
                }
            )
            setNegativeButton("Отмена",
                DialogInterface.OnClickListener{dialogInterface, id ->
                    Snackbar.make(binding.root, "Вы отменили действие", Snackbar.LENGTH_SHORT).show()
                }
            )
            setNeutralButton("Напомнить позже",
                DialogInterface.OnClickListener{dialogInterface, id ->
                    Snackbar.make(binding.root, "Мы напомним позже", Snackbar.LENGTH_SHORT).show()
                }
            )
            setOnDismissListener {
            }
            setIcon(R.drawable.ic_baseline_settings_24)
            create()
        }

        /** Создание TimePickerDialog*/
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this,
            TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                Snackbar.make(binding.root, "hour: $hour, minute: $minute", Snackbar.LENGTH_SHORT).show()
            }, hour, min, true
        )


        binding.apply {
            dateBtn.setOnClickListener {
                datePickerDialog.show()
            }
            alertBtn.setOnClickListener {
                alertBuilder.show()
            }
            timeBtn.setOnClickListener {
                timePickerDialog.show()
            }
            customBtn.setOnClickListener {
                showCustomDialog()
            }
        }
    }

    private fun showCustomDialog(){
        val customDialog = CustomDialog.newInstance()
        customDialog.show(supportFragmentManager.beginTransaction(), "custom")
    }

}

