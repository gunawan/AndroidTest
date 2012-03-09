/***************************************************************************
S/N. Date          Mod by          Important Notes
===========================================================================
1    Mar 9, 2012  	   Gunawan     	-  Baseline
 ****************************************************************************/

package com.gunawan.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.gunawan.R;

/**
 * @author gunawandeng
 * 
 */
public class FramedImageView extends ImageView {

	private static final String TAG = "FramedImageView";

	private Bitmap image;
	private Drawable placeHolder;
	private Bitmap framedPhoto;

	public FramedImageView(Context context) {
		super(context);
	}

	public FramedImageView(Context context, AttributeSet attrs) {
		super(context, attrs);

		Log.d(TAG, "FramedImageView placeHolder " + placeHolder);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FramedImageView, 0, 0);
		placeHolder = a.getDrawable(R.styleable.FramedImageView_placeHolder);

		a.recycle();
	}

	// public FramedImageView(Context context, AttributeSet attrs, int defStyle) {
	// super(context, attrs, defStyle);
	// }

	@Override
	protected void onDraw(Canvas canvas) {

		if (placeHolder == null && image == null) {
			return;
		}

		if (framedPhoto == null) {
			createFramedPhoto(Math.min(getWidth(), getHeight()));
		}

		canvas.drawBitmap(framedPhoto, 0, 0, null);

	}

	private void createFramedPhoto(int size) {
		Drawable imageDrawable = (image != null) ? new BitmapDrawable(image) : placeHolder;

		Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		RectF outerRect = new RectF(0, 0, size, size);
		float cornerRadius = size / 18f;

		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.RED);
		canvas.drawRoundRect(outerRect, cornerRadius, cornerRadius, paint);

		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		imageDrawable.setBounds(0, 0, size, size);

		// Save the layer to apply the paint
		canvas.saveLayer(outerRect, paint, Canvas.ALL_SAVE_FLAG);
		imageDrawable.draw(canvas);
		canvas.restore();

		// FRAMING THE PHOTO
		float border = size / 15f;

		// 1. Create offscreen bitmap link: http://www.youtube.com/watch?feature=player_detailpage&v=jF6Ad4GYjRU#t=1035s
		Bitmap framedOutput = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
		Canvas framedCanvas = new Canvas(framedOutput);
		// End of Step 1

		// Start - TODO IMPORTANT - this section shouldn't be included in the final code
		// It's needed here to differentiate step 2 (red) with the background color of the activity
		// It's should be commented out after the codes includes step 3 onwards
		// Paint squaredPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		// squaredPaint.setColor(Color.BLUE);
		// framedCanvas.drawRoundRect(outerRect, 0f, 0f, squaredPaint);
		// End

		// 2. Draw an opaque rounded rectangle link:
		// http://www.youtube.com/watch?feature=player_detailpage&v=jF6Ad4GYjRU#t=1044s
		RectF innerRect = new RectF(border, border, size - border, size - border);

		Paint innerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		innerPaint.setColor(Color.RED);
		framedCanvas.drawRoundRect(innerRect, cornerRadius, cornerRadius, innerPaint);

		// 3. Set the Power Duff mode link:
		// http://www.youtube.com/watch?feature=player_detailpage&v=jF6Ad4GYjRU#t=1056s
		Paint outerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		outerPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

		// 4. Draw a translucent rounded rectangle link:
		// http://www.youtube.com/watch?feature=player_detailpage&v=jF6Ad4GYjRU
		outerPaint.setColor(Color.argb(100, 0, 0, 0));
		framedCanvas.drawRoundRect(outerRect, cornerRadius, cornerRadius, outerPaint);

		// 5. Draw the frame on top of original bitmap
		canvas.drawBitmap(framedOutput, 0f, 0f, null);

		framedPhoto = output;
	}

	@Override
	public void setImageBitmap(Bitmap image) {
		this.image = image;
		this.framedPhoto = null;
	}
}
