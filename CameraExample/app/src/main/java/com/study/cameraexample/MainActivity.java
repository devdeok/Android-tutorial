package com.study.cameraexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 672;
    private String imageFilePath;
    private Uri photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //권한 체크
        TedPermission.with(getApplicationContext())
                .setPermissionListener(permissionListener)
                .setRationaleMessage("카메라 권한이 필요합니다.")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();

        //btn 촬영 버튼을 눌렀을 때 Action 
        findViewById(R.id.btn_capture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 기본 카메라 앱을 실행
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager())!=null){
                    File photoFile = null;
                    try{
                        photoFile = createImageFile();
                    }catch(IOException e){

                    }
                    if(photoFile != null){
                        photoUri = FileProvider.getUriForFile(getApplicationContext(),getPackageName(),photoFile);
                        // putExtra를 통해 실행함
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                        // 다음 intent로 화면 전환이 일어났을 때 그쪽 다음 Activity로부터 값을 넘겨 받음
                        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            }
        });

    }

    private File createImageFile() throws IOException{
        // timestamp에 년월일 시분초 단위로 file이름을 중복되지 않게 생성
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "TEST_" + timeStamp + "_";
        File stroageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                stroageDir
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
            ExifInterface exif = null;

            try {
                exif = new ExifInterface(imageFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int exifOrientation;
            int exifDegree;

            if (exif != null) {
                exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                exifDegree = exifOrientationToDegress(exifOrientation);
            } else {
                exifDegree = 0;
            }

            ((ImageView) findViewById(R.id.iv_result)).setImageBitmap(rotate(bitmap, exifDegree));
        }
    }

    private int exifOrientationToDegress(int exifOrientation) {
        // 화면 돌아갈 때 image를 rotate시켜줌
        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90){
            return 90;
        }else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180){
            return 180;
        }else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270){
            return 270;
        }
        return 0;
    }

    private Bitmap rotate (Bitmap bitmap,float degree){
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            // permission 승인
            Toast.makeText(getApplicationContext(),"권한이 허용됨",Toast.LENGTH_SHORT);
        }
        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            // permission 거절
            Toast.makeText(getApplicationContext(),"권한이 거부됨",Toast.LENGTH_SHORT);
        }
    };
}