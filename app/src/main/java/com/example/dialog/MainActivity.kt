package com.example.dialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.dialog.databinding.ActivityMainBinding
import com.example.dialog.databinding.FragmentBlankBinding
import com.example.dialog.databinding.ItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import kotlin.time.Duration.Companion.days

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            alert.setOnClickListener {
                var dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setMessage("Alert bosildi")
                dialog.setPositiveButton("Ha", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Toast.makeText(this@MainActivity, "Ish bajarildi", Toast.LENGTH_SHORT).show()
                    }
                })

                dialog.setNegativeButton("Yoq", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Toast.makeText(this@MainActivity, "Bajarlmadi", Toast.LENGTH_SHORT).show()
                    }
                })
                dialog.show()
            }

            custom.setOnClickListener {
                var dialog = AlertDialog.Builder(this@MainActivity).create()
                var item = ItemBinding.inflate(layoutInflater)
                dialog.setView(item.root)
                item.qaytsh.setOnClickListener {
                    dialog.cancel()
                }
                dialog.show()
            }

            fragment.setOnClickListener {
                var fragment = BlankFragment()
                fragment.show(supportFragmentManager.beginTransaction(), "key")
            }

            datepicker.setOnClickListener {
                val datePickerDialog = DatePickerDialog(this@MainActivity)

                datePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
                    Toast.makeText(this@MainActivity, "${dayOfMonth}.${month+1}.$year", Toast.LENGTH_SHORT).show()
                }

                datePickerDialog.show()
            }

            timepicker.setOnClickListener {
                val timePickerDialog = TimePickerDialog(
                    this@MainActivity,
                    { view, hourOfDay, minute -> Toast.makeText(
                        this@MainActivity,
                        "$hourOfDay:$minute",
                        Toast.LENGTH_SHORT
                    ).show()
                    },
                    24,
                    60,
                    true
                )
                timePickerDialog.updateTime(12, 0)
                timePickerDialog.show()
            }

            bottomsheet.setOnClickListener {
                val bottomSheetDialog = BottomSheetDialog(this@MainActivity)
                var item = ItemBinding.inflate(layoutInflater)
                item.qaytsh.setOnClickListener {
                    bottomSheetDialog.cancel()
                }
                bottomSheetDialog.setContentView(item.root)
                bottomSheetDialog.show()
            }

            snackber.setOnClickListener {
                val snackbar = Snackbar.make(it, "Salom snackbar", Snackbar.LENGTH_LONG)

                snackbar.setAction("Click", object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        Toast.makeText(this@MainActivity, "Click", Toast.LENGTH_SHORT).show()
                    }
                })

                snackbar.show()
            }
        }
    }
}