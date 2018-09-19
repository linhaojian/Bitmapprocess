package com.lhj.bitmapprocess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;

import java.io.ByteArrayOutputStream;

public class BitmapUtil {

    /**
     *  bitmap 转为byte[]
     * @param bitmap
     * @return
     */
    public static byte[] getBytesByBitmap(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(bitmap.getByteCount());
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }

    /**
     * 在bitmap中进行指定位置截取
     * @param bimtap
     * @param rect
     * @return
     */
    public static Bitmap ImageCropWithRect(Bitmap bimtap, Rect rect){
        return Bitmap.createBitmap(bimtap,rect.left,rect.top,rect.width(),rect.height(),null,false);
    }

    /**
     * 将NV21的图片格式转换成bitmap
     * @param bytes
     * @param w
     * @param h
     * @return
     */
    public static Bitmap nv21ToBitmap(byte[] bytes,int w,int h,int imageFormat){
        final YuvImage image = new YuvImage(bytes,imageFormat , w, h, null);
        ByteArrayOutputStream os = new ByteArrayOutputStream(bytes.length);
        image.compressToJpeg(new Rect(0, 0, w, h), 100, os);
        byte[] tmp = os.toByteArray();
        return BitmapFactory.decodeByteArray(tmp, 0,tmp.length);
    }

    // untested function
    public static byte[] bitmapToNV21(int inputWidth, int inputHeight, Bitmap scaled) {
        int [] argb = new int[inputWidth * inputHeight];
        scaled.getPixels(argb, 0, inputWidth, 0, 0, inputWidth, inputHeight);
        byte [] yuv = new byte[inputWidth*inputHeight*2];
        encodeYUV420SP(yuv, argb, inputWidth, inputHeight);
        return yuv;
    }

    private static void encodeYUV420SP(byte[] yuv420sp, int[] argb, int width, int height) {
        final int frameSize = width * height;
        int yIndex = 0;
        int uvIndex = frameSize;
        int a, R, G, B, Y, U, V;
        int index = 0;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                a = (argb[index] & 0xff000000) >> 24; // a is not used obviously
                R = (argb[index] & 0xff0000) >> 16;
                G = (argb[index] & 0xff00) >> 8;
                B = (argb[index] & 0xff) >> 0;
                // well known RGB to YUV algorithm
                Y = ( (  66 * R + 129 * G +  25 * B + 128) >> 8) +  16;
                U = ( ( -38 * R -  74 * G + 112 * B + 128) >> 8) + 128;
                V = ( ( 112 * R -  94 * G -  18 * B + 128) >> 8) + 128;
                // NV21 has a plane of Y and interleaved planes of VU each sampled by a factor of 2
                //    meaning for every 4 Y pixels there are 1 V and 1 U.  Note the sampling is every other
                //    pixel AND every other scanline.
                yuv420sp[yIndex++] = (byte) ((Y < 0) ? 0 : ((Y > 255) ? 255 : Y));
                if (j % 2 == 0 && index % 2 == 0) {
                    yuv420sp[uvIndex++] = (byte)((V<0) ? 0 : ((V > 255) ? 255 : V));
                    yuv420sp[uvIndex++] = (byte)((U<0) ? 0 : ((U > 255) ? 255 : U));
                }
                index ++;
            }
        }
    }

}
