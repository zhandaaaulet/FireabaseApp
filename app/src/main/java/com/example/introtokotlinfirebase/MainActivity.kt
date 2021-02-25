package com.company.introtokotlinfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.introtokotlinfirebase.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity2 : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null;
    private var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var email: EditText? = null
        var password: EditText? = null;
        var button: Button = findViewById(R.id.button)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

        mAuth = FirebaseAuth.getInstance()

        email = findViewById(R.id.emailID)
        password = findViewById(R.id.passwordID)



        button.setOnClickListener{
            var email1 = email.text.toString()
            var pass = password.text.toString()
            mAuth!!.createUserWithEmailAndPassword(email1, pass)
                .addOnCompleteListener(this) { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "User:" + email1, Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                    }
                }
        }





       /* mAuth!!.signInWithEmailAndPassword("naruto", "123")
            .addOnCompleteListener {
                    task: Task<AuthResult> ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Signed In Successful", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "Signed In Unsuccessful", Toast.LENGTH_LONG).show()
                }
            }*/

//        var James = Person("James", 30)
        /*var employee = Employee(
            "James Bond", "Android developer?XD",
            "123 South Street", 51
        )
        myRef.setValue(employee)*/


//        myRef.setValue("Hello, From Android x Kotlin!")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value as HashMap<String, Any>
                Log.d("VALUE: ", value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("ERROR: ", error.message)
            }

        })
    }

    /*override fun onStart() {
        super.onStart()
        currentUser = mAuth!!.currentUser
        if(currentUser != null){
            Toast.makeText(this, "User is loggged in", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "User is loggged out", Toast.LENGTH_LONG).show()
        }
    }*/

    class Employee() {
        var name: String? = null
        var position: String? = null
        var homeAddress: String? = null
        var age: Int? = null

        constructor(name: String, position: String, homeAddress: String, age: Int): this(){
            this.name = name
            this.position = position
            this.homeAddress = homeAddress
            this.age = age
        }
    }
}