package chandan.satyendra.prasad.viewmodescreenapplication

import android.content.ContentResolver
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import chandan.satyendra.prasad.viewmodescreenapplication.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var photoUri: Uri
    lateinit var soundUri: Uri
    lateinit var mediaPlayer: MediaPlayer
    var accepttotouche: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        photoUri =
            Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator + File.separator + packageName + "/drawable/lama")
        soundUri =
            Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator + File.separator + packageName + "/raw/scream2")
        Toast.makeText(this, "GoOoOooOooo", Toast.LENGTH_SHORT).show()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun playSoundClip() {
        mediaPlayer = MediaPlayer.create(this, soundUri)
        mediaPlayer.setOnCompletionListener {
            finish()
        }
        mediaPlayer.start()
    }

    private fun loadandshow() {
        // Load image into imageView
        Glide.with(this)
            .load(photoUri)
            .into(binding.imageView)
        binding.imageView.visibility = View.VISIBLE

    }

    private fun userTriggredAction() {
        if (!accepttotouche) {
            return
        }
        accepttotouche = false
        loadandshow()
        playSoundClip()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        userTriggredAction()
        return super.onTouchEvent(event)
    }
}