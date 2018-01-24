package com.framework.utils.carme;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;

import com.framework.utils.XFileUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 相机、相册工具类
 */
public class CramUtils {

    public File saveFile;
    public String imgPath;
    //相册
    public final static int CAMERA = 0x0002;
    //裁剪
    public final static int CROP = 0x0003;
    //相机
    public final static int ALBUM = 0x0004;
    //路径
    public static final String photoPath = Environment
            .getExternalStorageDirectory().getPath() + "/DCIM/Camera";
    Activity activity;

    public CramUtils(Activity context) {
        this.activity = context;
        //版本判断
        if (Build.VERSION.SDK_INT >= 23) {
            //减少是否拥有权限
            int checkCallPhonePermission1 = context.checkSelfPermission(Manifest.permission.CAMERA);
            if (checkCallPhonePermission1 != PackageManager.PERMISSION_GRANTED) {
                //弹出对话框接收权限
                context.requestPermissions(new String[]{Manifest.permission.CAMERA}, 30);
                return;
            }
            int checkCallPhonePermission2 = context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (checkCallPhonePermission2 != PackageManager.PERMISSION_GRANTED) {
                //弹出对话框接收权限
                context.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 30);
                return;
            }
        }
    }

    //路径位置
    public static final String getPhotoPath() {
        File file = null;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            file = new File(photoPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return file.getAbsolutePath();
    }

    /**
     * 调用相机
     */
    public void camera() {
        //版本判断
        if (Build.VERSION.SDK_INT >= 23) {
            //减少是否拥有权限
            int checkCallPhonePermission1 = activity.checkSelfPermission(Manifest.permission.CAMERA);
            if (checkCallPhonePermission1 != PackageManager.PERMISSION_GRANTED) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("没有权限");
                builder.setMessage("是否去授予打开相机权限");
                builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
                        activity.startActivity(intent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                return;
            }
        }
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            String filePth = setFileName();
            File file = new File(getPhotoPath(), filePth + ".png");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            this.saveFile = file;
            startForResultActivity(intent, CramUtils.CAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 调用相册
     */
    public void album() {
        //版本判断
        if (Build.VERSION.SDK_INT >= 23) {
            //减少是否拥有权限
            int checkCallPhonePermission1 = activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (checkCallPhonePermission1 != PackageManager.PERMISSION_GRANTED) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("没有权限");
                builder.setMessage("是否去授予打开相机权限");
                builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
                        activity.startActivity(intent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                return;
            }
        }
        if (Build.VERSION.SDK_INT < 19) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startForResultActivity(intent, CramUtils.ALBUM);
        } else {
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startForResultActivity(i, CramUtils.ALBUM);
        }
    }

    public String fromAlbumGetFilePath(Activity context, Uri uri) {
        Uri originalUri = uri;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.managedQuery(originalUri, proj, null, null,
                null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);

        return path;
    }

    /**
     * 相册裁剪
     *
     * @param activity
     * @param uri
     * @param aspectX
     * @param aspectY
     * @param outputX
     * @param outputY
     * @param requestCode
     */
    public void cropImage(Activity activity, Uri uri, int aspectX, int aspectY,
                          int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");

        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);

        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);

        intent.putExtra("outputFormat", "PNG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startForResultActivity(intent, requestCode);
    }

    /**
     * @param data
     * @return
     */
    public static String setCropImage(Context context, Intent data) {
        Bitmap bitmap = null;
        Uri photoUri = data.getData();
        if (photoUri != null) {
            bitmap = BitmapFactory.decodeFile(photoUri.getPath());
        }
        String newfilename = "";
        if (bitmap == null) {
            Bundle extra = data.getExtras();
            if (extra != null) {
                bitmap = (Bitmap) extra.get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                newfilename = XFileUtil.getImageDownloadDir(context) + System.currentTimeMillis()
                        + ".png";
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] picData = stream.toByteArray();
                try {
                    BitmapUtils.writeFileToLocal(picData, newfilename);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return newfilename;
    }

    /**
     * Activity回调
     *
     * @param intent
     * @param reqest
     */
    private void startForResultActivity(Intent intent,
                                        int reqest) {
        activity.startActivityForResult(intent, reqest);
    }

    /**
     * 裁剪图片
     *
     * @param requestCode
     * @param resultCode
     * @param data
     * @return
     */
    public boolean onResultCropImage(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case CramUtils.CAMERA:
                    if (saveFile != null) {
                        Uri uri = Uri.fromFile(new File(saveFile
                                .getAbsolutePath()));
                        cropImage(activity, uri, 5, 5, 300,
                                300, CramUtils.CROP);
                    }
                    break;
                case CramUtils.ALBUM:
                    if (data != null) {
                        Uri uri = data.getData();
                        cropImage(activity, uri, 5, 5, 300,
                                300, CramUtils.CROP);
                    } else {
                        Uri originalUri = data.getData();
                        try {
                            imgPath = BitmapUtils.scaleImage(activity,
                                    fromAlbumGetFilePath(
                                            activity, originalUri));
                            return true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case CramUtils.CROP:
                    if (data != null) {
                        imgPath = CramUtils.setCropImage(activity, data);
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    /**
     * 原始图片
     *
     * @param requestCode
     * @param resultCode
     * @param data
     * @return
     */
    public boolean onResultImage(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case CramUtils.CAMERA:
                    if (saveFile != null) {
                        Uri uri = Uri.fromFile(new File(saveFile
                                .getAbsolutePath()));
                        imgPath = uri.getPath();
                        return true;
                    }
                    break;
                case CramUtils.ALBUM:
                    if (data != null) {
                        Uri uri = data.getData();
                        imgPath = uri.getPath();
                        if (!new File(imgPath).exists()) {
                            imgPath = XFileUtil.getRealFilePath(activity, uri);
                        }
                        return true;
                    } else {
                        Uri originalUri = data.getData();
                        imgPath = fromAlbumGetFilePath(
                                activity, originalUri);

                    }
                    break;
            }

        }
        return false;
    }

    private static String setFileName() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssMS");
        Date curDate = new Date(System.currentTimeMillis());// 系统时间
        String str = formatter.format(curDate);
        return str;
    }
}