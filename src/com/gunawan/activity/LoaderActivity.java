package com.gunawan.activity;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.widget.Toast;

import com.gunawan.R;
import com.gunawan.loader.AsyncResult;
import com.gunawan.loader.MyAsyncTaskLoader;

/**
 * @author gunawandeng
 * 
 */
public class LoaderActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loader);

	}

	private final LoaderCallbacks<AsyncResult<List<String>>> loaderCallbacks = new LoaderCallbacks<AsyncResult<List<String>>>() {

		@Override
		public Loader<AsyncResult<List<String>>> onCreateLoader(int id, Bundle args) {
			MyAsyncTaskLoader loader = new MyAsyncTaskLoader(LoaderActivity.this);
			loader.setUpdateThrottle(1000);

			return loader;
		}

		@Override
		public void onLoadFinished(Loader<AsyncResult<List<String>>> loader, final AsyncResult<List<String>> result) {

			Exception exception = result.getException();
			if (exception != null) {
				Toast.makeText(LoaderActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
			} else {
				// process the result
			}
		}

		@Override
		public void onLoaderReset(Loader<AsyncResult<List<String>>> loader) {
			loader.reset();
		}
	};
}
