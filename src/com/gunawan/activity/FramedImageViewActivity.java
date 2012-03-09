/***************************************************************************
S/N. Date          Mod by          Important Notes
===========================================================================
1    Mar 9, 2012  	   Gunawan     	-  Baseline
 ****************************************************************************/

package com.gunawan.activity;

import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.gunawan.R;
import com.gunawan.widget.FramedImageView;

/**
 * @author gunawandeng
 * 
 */
public class FramedImageViewActivity extends FragmentActivity {

	private static final String TAG = "FramedImageViewActivity";

	// mNoImgVw is to demonstrate the place holder will be used when there is no image
	private FramedImageView mNoImgVw;
	private FramedImageView mImgVw;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_framed_image_view);

		mNoImgVw = (FramedImageView) findViewById(R.id.noImgVw);
		mImgVw = (FramedImageView) findViewById(R.id.imgVw);

		loadImage();
	}

	/**
	 * Load dr-evil.jpg into FramedImageView mImgVw
	 * 
	 */
	private void loadImage() {
		Bitmap bitmap = null;

		try {
			bitmap = BitmapFactory.decodeStream(getAssets().open("dr-evil.jpg"));
		} catch (IOException e) {
			Log.e(TAG, "Error: ", e);
		}

		mImgVw.setImageBitmap(bitmap);

	}
}
