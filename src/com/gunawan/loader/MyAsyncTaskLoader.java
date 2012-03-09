package com.gunawan.loader;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class MyAsyncTaskLoader extends AsyncTaskLoader<AsyncResult<List<String>>> {
	private AsyncResult<List<String>> data;

	public MyAsyncTaskLoader(Context context) {
		super(context);
	}

	@Override
	public void deliverResult(AsyncResult<List<String>> data) {
		if (isReset()) {
			// a query came in while the loader is stopped
			return;
		}

		this.data = data;

		super.deliverResult(data);
	}

	@Override
	public AsyncResult<List<String>> loadInBackground() {
		AsyncResult<List<String>> result = new AsyncResult<List<String>>();

		List<String> dataList = null;

		try {
			dataList = new ArrayList<String>();

			// load data in background
			// when exception occurs, it should be caught

		} catch (Exception ex) {
			result.setException(ex);
		}

		result.setData(dataList);

		return result;
	}

	@Override
	protected void onStartLoading() {
		if (data != null) {
			deliverResult(data);
		}

		if (takeContentChanged() || data == null) {
			forceLoad();
		}
	}

	@Override
	protected void onStopLoading() {
		cancelLoad();
	}

	@Override
	protected void onReset() {
		super.onReset();

		onStopLoading();

		data = null;
	}
}
