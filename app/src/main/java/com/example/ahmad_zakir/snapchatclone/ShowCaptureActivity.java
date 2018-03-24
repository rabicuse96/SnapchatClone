package com.example.ahmad_zakir.snapchatclone;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowCaptureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_capture);

        //Mendapatkan data bytes yang dikirim dari camera fragment
        Bundle extras = getIntent().getExtras();
        byte[] b = extras.getByteArray("capture");

        if (b != null){
            ImageView image = (ImageView) findViewById(R.id.imagecaptured);
            Bitmap decodebitmap = BitmapFactory.decodeByteArray(b, 0, b.length);

            Bitmap rotateBitmap = rotate(decodebitmap);
            image.setImageBitmap(decodebitmap);
        }
    }

    //Method untuk merotasi picture
    private Bitmap rotate(Bitmap decodebitmap) {
        int w = decodebitmap.getWidth();
        int h = decodebitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.setRotate(90);

        return Bitmap.createBitmap(decodebitmap, 0, 0,w,h, matrix,true);
    };
}
