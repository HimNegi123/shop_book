package com.example.shop_book

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Query
import com.google.common.graph.ElementOrder.insertion
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_homeactivity.*
import kotlinx.android.synthetic.main.dilogbox.*
import kotlinx.android.synthetic.main.dilogbox.view.*
import kotlinx.coroutines.launch
import java.lang.NumberFormatException
import java.util.*
import kotlin.collections.ArrayList

class Homeactivity : AppCompatActivity() {
    lateinit var particular: String
    lateinit var mrp: String
    lateinit var buyon: String
    lateinit var saleon: String
    lateinit var user: User

    lateinit var reference: DatabaseReference
    lateinit var ref: DatabaseReference
    lateinit var userArray: ArrayList<User>
    lateinit var myAdapter: MyAdapter
    lateinit var newArrayList: ArrayList<User>
    lateinit var tempAdapter: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homeactivity)
        supportActionBar?.hide()
        recycle.layoutManager = LinearLayoutManager(this)
        userArray = arrayListOf<User>()
        myAdapter = MyAdapter(userArray, this@Homeactivity)
        recycle.adapter = myAdapter
        search_button.setOnClickListener {
            var value: String?
            value = search_bar.text.toString()
            myAdapter.searchbuttonwork(value)
            keyboardclose()

        }
        back.setOnClickListener {
            getuserdata()
            keyboardclose()

        }
        imageButton.setOnClickListener {

            val dilogview = layoutInflater.inflate(R.layout.dilogbox, null)
            val Dilog = AlertDialog.Builder(this)
                .setView(dilogview).show()
            dilogview.summit.setOnClickListener {
                particular = dilogview.parti.text.toString()
                mrp = dilogview.mrp.text.toString()
                buyon = dilogview.buyon.text.toString()
                saleon = dilogview.saleon.text.toString()
                user = User(particular, mrp, buyon, saleon)
                try {
                    if (buyon.toDouble() > saleon.toDouble()) {
                        Toast.makeText(this, "CHECK CAREFULLY", Toast.LENGTH_SHORT).show()
                    } else {
                        val User = User(particular, mrp, buyon, saleon)
                        ref =
                            FirebaseDatabase.getInstance("https://shop-s-rate-default-rtdb.asia-southeast1.firebasedatabase.app/")
                                .getReference("Items")
                        ref.child(particular).setValue(User)
                        Dilog.dismiss()
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "FILL THE ALL SECTION", Toast.LENGTH_SHORT).show()
                }
            }
        }
        getuserdata()
    }

    private fun keyboardclose() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun getuserdata() {
        reference =
            FirebaseDatabase.getInstance("https://shop-s-rate-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Items")
         reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userArray.clear()
                if (snapshot.exists()){}
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Homeactivity, "SOMETHING WENT WRONG", Toast.LENGTH_SHORT)
            }

        })
    }
}




