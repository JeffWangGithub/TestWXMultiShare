package com.example.TestWXMultiShare;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileFilter;

public class MyActivity extends Activity implements View.OnClickListener{

    private File[] files;
    private String[] paths;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // 遍历 SD 卡下 .png 文件通过微信分享，保证SD卡根目录下有.png的文件
        File root = Environment.getExternalStorageDirectory();
        files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().endsWith(".jpg"))
                    return true;
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share_btn1:
                if(files == null || files.length == 0){
                    Toast.makeText(this,"SD卡根目录下无.png格式照片",Toast.LENGTH_SHORT).show();
                }else{
                    ShareUtils.shareMultiPicToWXCircle(this, "你好，成功的分享了多张照片到微信",files);
                }
                break;
        }
    }
}
