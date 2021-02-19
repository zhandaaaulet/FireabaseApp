package com.example.introtokotlinfirebase

import android.app.Person
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var currentUser: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = FirebaseDatabase.getInstance()
        val databaseRef = database.getReference("message").push()

        mAuth = FirebaseAuth.getInstance()

        mAuth!!.signInWithEmailAndPassword("paulo@me.com", "password")
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this, "Signed in Succesful",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this, "Signed in Unsuccesful",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
//        var James = Person("James", 30)
        var employee = Employee(
            "Bonni D", "Professor ",
            "123 South Street", 25
        )
        //databaseRef.setValue(employee)


//        myRef.setValue("Hello, From Android x Kotlin!")

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot!!.value as HashMap<String, Any>
//                Log.d("VALUE: ", value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("ERROR: ", error!!.message)
            }
        })


    }

    override fun onStart() {
        super.onStart()

        currentUser = mAuth!!.currentUser
        if (currentUser != null) {
            Toast.makeText(this, "User is logged in", Toast.LENGTH_LONG).show()
        }

    }


    class Employee() {
        var name: String? = null
        var position: String? = null
        var homeAddress: String? = null
        var age: Int? = null

        constructor(name: String, position: String, homeAdress: String, age: Int) : this() {
            this.name = name
            this.position = position
            this.homeAddress = homeAddress
            this.age = age
        }

    }
}