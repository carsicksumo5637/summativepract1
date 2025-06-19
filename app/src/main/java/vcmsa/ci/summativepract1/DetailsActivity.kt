package vcmsa.ci.summativepract1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
    private lateinit var songTitles: Array<String>
    private lateinit var artists: Array<String>
    private lateinit var comments: Array<String>
    private lateinit var ratings: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        songTitles = intent.getStringArrayExtra("songTitles") ?: Array(4) { "" }
        artists = intent.getStringArrayExtra("artists") ?: Array(4) { "" }
        comments = intent.getStringArrayExtra("comments") ?: Array(4) { "" }
        ratings = intent.getIntArrayExtra("ratings") ?: IntArray(4)

        findViewById<Button>(R.id.btnDisplaySongs).setOnClickListener {
            displaySongs()
        }

        findViewById<Button>(R.id.btnAverageRating).setOnClickListener {
            calculateAverageRating()
        }

        findViewById<Button>(R.id.btnBackToMain).setOnClickListener {
            finish()
        }
    }

    private fun displaySongs() {
        val result = StringBuilder()
        for (i in songTitles.indices) {
            result.append("Song ${i + 1}:\n")
            result.append("Title: ${songTitles[i]}\n")
            result.append("Artist: ${artists[i]}\n")
            result.append("Rating: ${ratings[i]}\n")
            result.append("Comments: ${comments[i]}\n\n")
        }
        findViewById<TextView>(R.id.txtDetails).text = result.toString()
    }

    private fun calculateAverageRating() {
        val average = ratings.average()
        findViewById<TextView>(R.id.txtDetails).text = "Average Rating: %.2f".format(average)
    }
}
