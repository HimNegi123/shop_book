package com.example.shop_book

import android.app.AlertDialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.delete.*
import kotlinx.android.synthetic.main.delete.view.*
import kotlinx.android.synthetic.main.item_view.view.*
import java.lang.Math.abs


class MyAdapter(var alldata:ArrayList<User>,val context: Context): RecyclerView.Adapter<MyviewModel>() {
    lateinit var reference: DatabaseReference
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewModel {
   val inflate=LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)

        return MyviewModel(inflate)
    }
fun searchbuttonwork(value:String){
    var arr:ArrayList<User> =ArrayList()
    var temp:ArrayList<User> =ArrayList()
    temp=alldata
    arr.clear()
    for(user in alldata){
        try {

            if(user.particular.toString().substring(0,value.length).equals(value, ignoreCase = true)) {
                arr.add(user)
            }
        }
        catch (e:StringIndexOutOfBoundsException){
            Toast.makeText(context,"out of bond n",Toast.LENGTH_SHORT).show()
        }
    }
    if(arr.isEmpty()){
        alldata.clear()
        notifyDataSetChanged()
        val toast = Toast.makeText(
           context,
            "TOP RIGHT!",
            Toast.LENGTH_LONG
        )
        toast.show()

    }
    else{
        alldata=arr
        notifyDataSetChanged()

    }
}
    override fun onBindViewHolder(holder: MyviewModel, position: Int) {
        holder.item.text=alldata[position].particular
        holder.num1.text=alldata[position].mrp
        holder.num2.text=alldata[position].buyon
        holder.num3.text=alldata[position].sellon
        holder.num4.text=
            (alldata[position].buyon?.toInt())?.minus((alldata[position].sellon?.toInt()!!))
                ?.let { abs(it).toString() }
     holder.card.setOnLongClickListener {
         val dilog=LayoutInflater.from(context).inflate(R.layout.delete,null)
         val Dilog=AlertDialog.Builder(context).setTitle(" DO YOU WANT TO DELETE ").setView(dilog).show()
         Dilog.cancel.setOnClickListener {
             Dilog.dismiss()
         }
         Dilog.ok.setOnClickListener {
             reference=FirebaseDatabase.getInstance("\"https://shop-book-3deb5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Items")
             reference.child(alldata[position].particular.toString()).removeValue().addOnSuccessListener {
                 notifyDataSetChanged()
                 Toast.makeText(context,"delete successfully",Toast.LENGTH_SHORT).show()
             }.addOnFailureListener {
                 Toast.makeText(context,"something went wrong",Toast.LENGTH_SHORT).show()
             }
             Dilog.dismiss()

         }
         return@setOnLongClickListener true

     }





    }


    override fun getItemCount(): Int {
return alldata.size
    }
    fun update(array:ArrayList<User>){
        alldata.clear()
        alldata.addAll(array)
        notifyDataSetChanged()
    }

}
class MyviewModel(itemView: View): RecyclerView.ViewHolder(itemView) {
val item= itemView.findViewById<TextView>(R.id.item)!!
    val num1=itemView.findViewById<TextView>(R.id.num1)
    val num2=itemView.findViewById<TextView>(R.id.num2)
    val num3=itemView.findViewById<TextView>(R.id.num3)
    val num4=itemView.findViewById<TextView>(R.id.num4)
    val card=itemView.findViewById<CardView>(R.id.cardView)
}