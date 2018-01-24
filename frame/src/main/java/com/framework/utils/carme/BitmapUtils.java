package com.framework.utils.carme;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 图片处理工具
 */
public class BitmapUtils {

    private static byte[] compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        int options = 100;
        while (baos.toByteArray().length / 1024 > 500) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.PNG, options, baos);
            options -= 10;
        }
        return baos.toByteArray();
    }

    /**
     * @param context
     * @param filename
     * @return
     * @throws Exception
     */
    public static String scaleImage(Context context, String filename)
            throws Exception {
        Bitmap image = BitmapFactory.decodeFile(filename);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;

        float hh = 800f;
        float ww = 480f;
        int be = 1;
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        byte[] picData = compressImage(bitmap);
        String newfilename = "/sdcard/Men/" + System.currentTimeMillis()
                + ".jpg";
        writeFileToLocal(picData, newfilename);
        return newfilename;
    }

    /**
     * @param datas
     * @param filename
     * @return
     */
    public static void writeFileToLocal(byte[] datas, String filename)
            throws Exception {
        File file = new File(filename);
        if (file.exists())
            file.delete();
        if (file.getParentFile().isDirectory() || file.getParentFile().mkdirs()) {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(datas, 0, datas.length);
            fos.close();
        } else {
            throw new Exception("Cannot create new file " + filename);
        }
    }
}