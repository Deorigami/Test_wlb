package com.ardinata.test.wlb.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

fun ImageView.customSetImage(
    imageSource: Any?,
    defaultErrorImageUrl: Drawable? = null,
    onErrorLoadImage: () -> Unit = {},
    onSuccessLoadImage: () -> Unit = {},
    saveCache: Boolean = true,
    invalidate: Boolean = false
) {
    when (imageSource) {
        is String -> {
            if (invalidate) {
                val picasso = Picasso.get()
                picasso.load(imageSource.ifEmpty { "null" }).noFade().noPlaceholder()
                    .setMemoryPolicy(saveCache).into(this)
                picasso.invalidate(imageSource.ifEmpty { "null" })
            } else {
                if (imageSource.isNotEmpty()) {
                    Picasso.get()
                        .load(imageSource)
                        .resize(1200, 0)
                        .onlyScaleDown()
                        .setMemoryPolicy(true)
                        .noFade()
                        .into(
                            this,
                            object : Callback {
                                override fun onSuccess() {
                                    onSuccessLoadImage()
                                }

                                override fun onError(e: java.lang.Exception?) {
                                    onErrorLoadImage()
                                    defaultErrorImageUrl?.let {
                                        this@customSetImage.setImageDrawable(it)
                                    }
                                }
                            }
                        )
                }
            }
        }
        is Int -> {
            try {
                this.setImageDrawable(ContextCompat.getDrawable(context, imageSource))
            } catch (e: Exception) {

            }
        }
        is Drawable -> {
            this.setImageDrawable(imageSource)
        }
        is Bitmap -> this.setImageBitmap(imageSource)
    }
}

fun RequestCreator.setMemoryPolicy(saveCache: Boolean): RequestCreator {
    return if (!saveCache) memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE) else this
}