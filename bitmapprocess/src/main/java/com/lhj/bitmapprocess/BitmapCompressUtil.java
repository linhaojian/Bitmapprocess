package com.lhj.bitmapprocess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 *   图片处理 ，压缩
 */
public class BitmapCompressUtil {

    /**
     *  质量压缩 ： 保持像素的前提下改变图片的位深及透明度等，来达到压缩图片的目的（文件大小变化，像素不变化）
     *  baos.toByteArray().length 会根据quality逐渐减少，适合使用在二进制图片数据传输上。
     * @param bitmap  需要压缩的bitmap
     * @param reqSize  需要压缩指定的文件大少
     * @return
     */
    public static Bitmap quality(Bitmap bitmap, int reqSize){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //这里100表示不压缩，把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        int options = 95;
        //如果压缩后的大小超出所要求的，继续压缩
        while (baos.toByteArray().length / 1024 > reqSize){
            baos.reset();
            boolean is = bitmap.compress(Bitmap.CompressFormat.JPEG,options,baos);
            Log.e("linhaojian",is+"   "+options+"  :  "+baos.toByteArray().length);
            //每次减少5%质量
            if (options>5){//避免出现options<=0
                options -=5;
            } else {
                break;
            }
        }
        bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(),0,baos.toByteArray().length);
        return bitmap;
    }

    /**
     *  采样率压缩：通过inSampleSize进行二次采样（文件大小与像素都变化）
     * @param bits
     * @param width
     * @param height
     * @return
     */
    public static Bitmap samplingRate(byte[] bits,int width,int height){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bits,0,bits.length,options);
        //获取采样率
        options.inSampleSize = calculateInSampleSize(options,width,height);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bits,0,bits.length,options);
    }

    /**
     * 获取采样率
     * @param options
     * @param reqWidth 目标view的宽
     * @param reqHeight 目标view的高
     * @return 采样率
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;
        int inSampleSize = 1;
        if (originalHeight > reqHeight || originalWidth > reqHeight){
            int halfHeight = originalHeight / 2;
            int halfWidth = originalWidth / 2;
            //压缩后的尺寸与所需的尺寸进行比较
            while ((halfWidth / inSampleSize) >= reqWidth && (halfHeight /inSampleSize)>=reqHeight){
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     *  缩放法压缩（文件大小与像素都变化）
     * @param bit  需要压缩的bitmap
     * @param scale 需要压缩的倍数
     * @return
     */
    public static Bitmap martix(Bitmap bit,float scale){
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(bit, 0, 0, bit.getWidth(),
                bit.getHeight(), matrix, true);
    }

    /**
     *  根据指定宽高进行压缩（文件大小与像素都变化）
     * @param bit
     * @param width
     * @param height
     * @return
     */
    public static Bitmap createScaledBitmap(Bitmap bit,int width,int height){
        return Bitmap.createScaledBitmap(bit,width,height,true);
    }

}
