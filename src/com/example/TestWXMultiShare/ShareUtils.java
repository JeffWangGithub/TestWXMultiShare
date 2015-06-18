package com.example.TestWXMultiShare;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * @title:
 * @description:
 * @company: 美丽说（北京）网络科技有限公司
 * Created by Glan.Wang on 15/6/16.
 */
public class ShareUtils {
    /**
     * 测试分享多张图片到朋友圈
     * @param context
     */
    public static void shareMultiPicToWXCircle(Context context,String Kdescription,File... files){
        //检查是否安装微信
        boolean isInstallWeChart = isInstallWeChart(context);
        if(!isInstallWeChart){
            Toast.makeText(context,"没有安装微信",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        ComponentName comp = new ComponentName("com.tencent.mm",
                "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        intent.setComponent(comp);
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");
        intent.putExtra("sns", "Text");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.ACTION_ATTACH_DATA, "aaaaa");
		ArrayList<Uri> imageUris = new ArrayList<Uri>();
		for (File f : files) {
			imageUris.add(Uri.fromFile(f));
		}
        intent.putExtra("Kdescription", Kdescription);
		intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        context.startActivity(intent);
    }



    public static void shareMultiPicToWXCircle(Context context,String... paths){
        //检查是否安装微信
        boolean isInstallWeChart = isInstallWeChart(context);
        if(!isInstallWeChart){
            Toast.makeText(context,"没有安装微信",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        ComponentName comp = new ComponentName("com.tencent.mm",
                "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        intent.setComponent(comp);
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_TEXT,"我是文字");
        ArrayList<CharSequence> imagePathList = new ArrayList<CharSequence>();
        for(String picPath: paths){
            imagePathList.add(picPath);
        }
        intent.putCharSequenceArrayListExtra(Intent.EXTRA_STREAM, imagePathList);
        context.startActivity(intent);
    }

    /**检查是否安装微信
     * @param context
     * @return
     */
    public static boolean isInstallWeChart(Context context){
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo("com.tencent.mm", 0);
        } catch (Exception e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;
        }
    }

}
