package com.example.smalltobig;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean num = false;
    private ImageView mainImage;
    private float scaleHeight;
    private float scaleWidth;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainImage = (ImageView) findViewById(R.id.main_image);
        Display display = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.animation);
        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();
        int displayWidth = 1152;
        int displayHeight = 1920;
        scaleHeight = ((float) displayHeight) / imageHeight;
        scaleWidth = ((float) displayWidth) / imageWidth;
        mainImage.setImageBitmap(bitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (num == true) {
                    Matrix matrix = new Matrix();
                    matrix.postScale(scaleWidth, scaleHeight);
                    Bitmap newBitmap = Bitmap.createBitmap(this.bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    mainImage.setImageBitmap(newBitmap);
                    num = false;
                } else {
                    Matrix matrix = new Matrix();
                    matrix.postScale(1.0f, 1.0f);
                    Bitmap newBitmap = Bitmap.createBitmap(this.bitmap, 0, 0, this.bitmap.getWidth(), this.bitmap.getHeight(), matrix, true);
                    mainImage.setImageBitmap(newBitmap);
                    num = true;
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
