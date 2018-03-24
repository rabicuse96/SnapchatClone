package com.example.ahmad_zakir.snapchatclone;


import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.AndroidCharacter;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.List;


/**
 * Created by ahmad_zakir on 1/19/2018.
 */

public class CameraFragment extends Fragment implements SurfaceHolder.Callback {
    //Deklarasi variabel camera
    Camera camera;

    Camera.PictureCallback jpegCallback;

    //    Deklarasi surfaceview
    SurfaceView mSurfaceView;
    //    Deklarasi surfaceHolder ( Control and allow to add something in surfaceview )
    SurfaceHolder mSurfaceHolder;

    final int CAMERA_REQUEST_CODE = 1;

    public static CameraFragment newInstance() {
        CameraFragment fragment = new CameraFragment();
        return fragment;
    }

    //Menampilkan OnCreateView ketika objek kelas dipanggil
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentcamera, container, false);


        mSurfaceView = (SurfaceView) view.findViewById(R.id.surfaceView);
        mSurfaceHolder = mSurfaceView.getHolder();
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
        } else {
            mSurfaceHolder.addCallback(this);
            mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        }
        //Menghubungkan button layout dan activity
        Button mcapture = (Button) view.findViewById(R.id.imageCapture);
        Button mlogout = (Button) view.findViewById(R.id.logout);

        //Method ketika button di klik
        mlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogOut();
            }
        });

        mcapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage();
            }
        });

        //jpegcalback menerima picture dari button capture
        jpegCallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Intent intent = new Intent(getActivity(),ShowCaptureActivity.class);
                intent.putExtra("capture", data);
                startActivity(intent);
                return;
            }
        };


        return view;
    }

    private void captureImage() {
        camera.takePicture(null,null,jpegCallback);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //Membuka kamera
        camera = Camera.open();

        // Mendapatkan parameter camera
        Camera.Parameters parameters;
        parameters = camera.getParameters();

        //Posisi orientasi kamera
        camera.setDisplayOrientation(90);
        //Mengatur fps kamera
        parameters.setPreviewFrameRate(30);
        //Mengatur Autofocus camera
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);

        //Mencari size picture terbaik
        Camera.Size bestsize = null;
        List<Camera.Size> sizeList = camera.getParameters().getSupportedPreviewSizes();
        bestsize = sizeList.get(0);
        for (int i = 1; i <sizeList.size(); i++ ){
            if ((sizeList.get(i).width * sizeList.get(i).height)>(bestsize.width * bestsize.height)){
                bestsize = sizeList.get(i);
            }
        }

        parameters.setPreviewSize(bestsize.width, bestsize.height);

        camera.setParameters(parameters);
        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //mulai menampilkan kameran ke surface
        camera.startPreview();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    mSurfaceHolder.addCallback(this);
                    mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
                }else{
                    Toast.makeText(getContext(),"Please provide the permission", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    private  void LogOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getContext(), SplashScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return;
    }
}
