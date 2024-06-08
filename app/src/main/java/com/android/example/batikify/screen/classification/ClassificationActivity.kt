package com.android.example.batikify.screen.classification

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.android.example.batikify.databinding.ActivityClassificationBinding
import com.android.example.batikify.factory.ViewModelFactory
import com.android.example.batikify.helper.ImageClassifierHelper
import com.android.example.batikify.screen.Camera.getImageUri
import com.android.example.batikify.screen.detail.DetailActivity
import org.tensorflow.lite.task.vision.classifier.Classifications

class ClassificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClassificationBinding
    private var currentImageUri: Uri? = null
    private val classificationViewModel by viewModels<ClassificationViewModel> {
        ViewModelFactory.getInstance(application)
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClassificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        classificationViewModel.isLoading.observe(this){
            showLoading(it)
        }

        binding.galeryButton.setOnClickListener { startGallery() }
        binding.cameraButton.setOnClickListener { startCamera() }
        binding.uploadButton.setOnClickListener { analyzeImage() }


    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun startCamera() {
        currentImageUri = getImageUri(this)
        launcherIntentCamera.launch(currentImageUri!!)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }


    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.previewImageView.setImageURI(it)
        }
    }

    private fun analyzeImage() {
        currentImageUri?.let { uri ->
            val imageClassifierHelper = ImageClassifierHelper(
                context = this,
                classifierListener = object : ImageClassifierHelper.ClassifierListener {
                    override fun onError(error: String) {
                        showToast(error)
                    }

                    override fun onResults(results: List<Classifications>?, inferenceTime: Long) {
                        results?.let { classificationList ->
                            if (classificationList.isNotEmpty()) {
                                val topResult = classificationList[0]
                                val categories = topResult.categories
                                if (categories.isNotEmpty()) {
                                    val topCategory = categories[0]
                                    val className = topCategory.label
                                    val confidence = topCategory.score

                                    moveToResult(className)
                                    showToast("Class Type: $className, Confidence Score: $confidence")
                                } else {
                                    showToast("No categories found")
                                }
                            } else {
                                showToast("No results found")
                            }
                        } ?: showToast("Null results")
                    }
                }
            )
            imageClassifierHelper.classifyStaticImage(contentResolver, uri)
        } ?: showToast("No Image Selected")
    }

    private fun moveToResult(batikName : String) {
        val intent = Intent(this@ClassificationActivity, DetailActivity::class.java)
        intent.putExtra("BATIK_NAME",batikName)
        startActivity(intent)
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressIndicator.visibility = View.VISIBLE
        } else {
            binding.progressIndicator.visibility = View.GONE
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
        const val CAMERAX_RESULT = 200
    }

}