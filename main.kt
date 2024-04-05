import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    // Reference to the Firebase Database
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance()
        myRef = database.reference

        // Reference to the TextView in the XML layout
        val textView = findViewById<TextView>(R.id.textView)

        // Listen for changes on the "data" node in the Firebase Database
        myRef.child("data").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Handle data when there is a change in the Firebase Database
                val value = dataSnapshot.getValue(String::class.java)
                value?.let {
                    // Display the data received from Firebase in the TextView
                    textView.text = it
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle when there is an error
            }
        })
    }
}
