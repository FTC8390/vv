package ftc8390.vv;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by jmgu3 on 11/7/2016.
 */
public class BeaconColorDetector {

    public void init(HardwareMap hardwareMap) {

    }

    static public int red(int pixel) {
        return (pixel >> 16) & 0xff;
    }

    static public int green(int pixel) {
        return (pixel >> 8) & 0xff;
    }

    static public int blue(int pixel) {
        return pixel & 0xff;
    }

    static public int gray(int pixel) {
        return (red(pixel) + green(pixel) + blue(pixel));
    }

    static public int highestColor(int red, int green, int blue) {
        int[] color = {red, green, blue};
        int value = 0;
        for (int i = 1; i < 3; i++) {
            if (color[value] < color[i]) {
                value = i;
            }
        }
        return value;
    }

    public boolean blueIsOnLeft(Bitmap rgbImage)
    {
        int leftXStart = (int)((double)rgbImage.getWidth() * 0.1);
        int rightXStart = (int)((double)rgbImage.getWidth() * 0.6);
        int leftXEnd = (int)((double)rgbImage.getWidth() * 0.4);
        int rightXEnd = (int)((double)rgbImage.getWidth() * 0.9);

        int yStart = (int)((double)rgbImage.getHeight() * 0.3);
        int yEnd = (int)((double)rgbImage.getHeight() * 0.8);

        int rightRedValue = 0;
        int rightBlueValue = 0;
        int leftRedValue = 0;
        int leftBlueValue = 0;

        for (int x = leftXStart; x < leftXEnd; x++) {
            for (int y = yStart; y < yEnd; y++) {
                int pixel = rgbImage.getPixel(x, y);
                leftRedValue += red(pixel);
                leftBlueValue += blue(pixel);
            }
        }

        for (int x = rightXStart; x < rightXEnd; x++) {
            for (int y = yStart; y < yEnd; y++) {
                int pixel = rgbImage.getPixel(x, y);
                rightRedValue += red(pixel);
                rightBlueValue += blue(pixel);
            }
        }

        if (leftRedValue - leftBlueValue > rightRedValue - rightBlueValue)
            return false;
        else
            return true;
    }

}
