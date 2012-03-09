/***************************************************************************
S/N. Date          Mod by          Important Notes
===========================================================================
1    Mar 9, 2012  	   Gunawan     	-  Baseline
 ****************************************************************************/

package com.gunawan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.gunawan.activity.FramedImageViewActivity;
import com.gunawan.activity.LoaderActivity;

/**
 * @author gunawandeng
 * 
 */
public class MainActivity extends FragmentActivity {

	private Button mLoaderButton;
	private Button mFramedImageViewButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mLoaderButton = (Button) findViewById(R.id.loaderBtn);
		mLoaderButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, LoaderActivity.class);
				startActivity(intent);

			}
		});

		mFramedImageViewButton = (Button) findViewById(R.id.framedImageViewBtn);
		mFramedImageViewButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, FramedImageViewActivity.class);
				startActivity(intent);

			}
		});
	}
}
