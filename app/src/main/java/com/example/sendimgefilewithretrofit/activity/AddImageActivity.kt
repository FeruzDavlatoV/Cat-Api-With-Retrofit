package com.example.sendimgefilewithretrofit.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.sendimgefilewithretrofit.R

class AddImageActivity : AppCompatActivity() {

    var SELECT_KEY = 999
    lateinit var selectImage: ImageView
    lateinit var ivAdded: ImageView
    lateinit var addImage: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_image)
        selectImage = findViewById(R.id.image)
        initViews()
    }

    private fun initViews() {
//        takePhoto()


        addImage = findViewById(R.id.addBtn)
        ivAdded = findViewById(R.id.ivAdded)
        selectImage.setOnClickListener {
            fromGallery()
        }
    }

    private fun fromGallery() {
        var fromGallery = Intent()
        fromGallery.setType("image/*")
        fromGallery.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(fromGallery, "Select Picture"), SELECT_KEY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_KEY) {
                val selectedImageUri:Uri? = data!!.data
                if (null != selectedImageUri) {
                    ivAdded.setImageURI(selectedImageUri)
                }
            }
        }
    }

//    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == RESULT_OK) {
//
//            // compare the resultCode with the
//            // SELECT_PICTURE constant
//            if (requestCode == SELECT_PICTURE) {
//                // Get the url of the image from data
//                val selectedImageUri = data.data
//                if (null != selectedImageUri) {
//                    // update the preview image in the layout
//                    IVPreviewImage.setImageURI(selectedImageUri)
//                }
//            }
//        }
//    }
}