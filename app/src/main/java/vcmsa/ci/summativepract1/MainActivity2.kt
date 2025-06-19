package vcmsa.ci.summativepract1
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    private val songTitles = Array(4) { "" }
    private val artists = Array(4) { "" }
    private val ratings = IntArray(4)
    private val comments = Array(4) { "" }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // assume this layout has the correct views

        val songIndexSpinner = findViewById<Spinner>(R.id.spinnerSongIndex)
        songIndexSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayOf("0", "1", "2", "3"))

        findViewById<Button>(R.id.btnAddSong).setOnClickListener {
            addSong()
        }

        findViewById<Button>(R.id.btnViewDetails).setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("songNames", songTitles)
            intent.putExtra("artists", artists)
            intent.putExtra("ratings", ratings)
            intent.putExtra("comments", comments)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnExit).setOnClickListener {
            finish()
        }
    }

    private fun addSong() {
        val index = findViewById<Spinner>(R.id.spinnerSongIndex).selectedItemPosition
        val title = findViewById<EditText>(R.id.inputSongTitle).text.toString()
        val artist = findViewById<EditText>(R.id.inputArtistName).text.toString()
        val ratingInput = findViewById<EditText>(R.id.inputRating).text.toString()
        val comment = findViewById<EditText>(R.id.inputComment).text.toString()

        try {
            val rating = ratingInput.toInt()
            if (rating !in 1..5) {
                Toast.makeText(this, "Rating must be between 1 and 5", Toast.LENGTH_SHORT).show()
                return
            }

            songTitles[index] = title
            artists[index] = artist
            ratings[index] = rating
            comments[index] = comment
            Toast.makeText(this, "Song added!", Toast.LENGTH_SHORT).show()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Invalid rating. Enter a number 1-5.", Toast.LENGTH_SHORT).show()
        }
    }
}
