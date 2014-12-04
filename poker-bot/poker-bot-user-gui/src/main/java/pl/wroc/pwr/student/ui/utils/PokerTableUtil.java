package pl.wroc.pwr.student.ui.utils;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.GDI32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HBITMAP;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinGDI;
import com.sun.jna.platform.win32.WinGDI.BITMAPINFO;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import jna.extra.GDI32Extra;
import jna.extra.User32Extra;
import jna.extra.WinGDIExtra;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by RaV on 07.09.14.
 */
public class PokerTableUtil {
    private static final TryWithHWND tryWithHWND = new TryWithHWND();;
    private PokerTableUtil() {
    }
    public static BufferedImage capture (){
        List<HWND> handles = tryWithHWND.getHandles();
        if (handles.size()<1){
            System.out.println("Cannot capture: No poker tables opened.");
            return null;
        }

        return capture(handles.get(0));
    }

    public static String getLastCaptureName(){
        List<String> names = tryWithHWND.getLastNames();
        if (names.size()<1){
            System.out.println("Cannot get name: No poker tables opened.");
            return null;
        }
        return names.get(0);
    }

    public static int getLastCaptureBb(){
        return Integer.parseInt(getLastCaptureName().split("\\d/\\$?")[1].split(" Ante")[0]);
    }

    public static void focusPokerTable() {
        List<HWND> handles = new TryWithHWND().getHandles();
        if (handles.size()<1){
            System.out.println("Cannot focus: No poker tables opened.");
            return;
        }
        User32.INSTANCE.SetForegroundWindow(handles.get(0));
    }

    private static BufferedImage capture(HWND hWnd) {

        HDC hdcWindow = User32.INSTANCE.GetDC(hWnd);
        HDC hdcMemDC = GDI32.INSTANCE.CreateCompatibleDC(hdcWindow);

        RECT bounds = new RECT();
        User32Extra.INSTANCE.GetClientRect(hWnd, bounds);

        int width = bounds.right - bounds.left;
        int height = bounds.bottom - bounds.top;

        HBITMAP hBitmap = GDI32.INSTANCE.CreateCompatibleBitmap(hdcWindow, width, height);

        HANDLE hOld = GDI32.INSTANCE.SelectObject(hdcMemDC, hBitmap);
        GDI32Extra.INSTANCE.BitBlt(hdcMemDC, 0, 0, width, height, hdcWindow, 0, 0, WinGDIExtra.SRCCOPY);

        GDI32.INSTANCE.SelectObject(hdcMemDC, hOld);
        GDI32.INSTANCE.DeleteDC(hdcMemDC);

        BITMAPINFO bmi = new BITMAPINFO();
        bmi.bmiHeader.biWidth = width;
        bmi.bmiHeader.biHeight = -height;
        bmi.bmiHeader.biPlanes = 1;
        bmi.bmiHeader.biBitCount = 32;
        bmi.bmiHeader.biCompression = WinGDI.BI_RGB;

        Memory buffer = new Memory(width * height * 4);
        GDI32.INSTANCE.GetDIBits(hdcWindow, hBitmap, 0, height, buffer, bmi, WinGDI.DIB_RGB_COLORS);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, width, height, buffer.getIntArray(0, width * height), 0, width);

        GDI32.INSTANCE.DeleteObject(hBitmap);
        User32.INSTANCE.ReleaseDC(hWnd, hdcWindow);

        return image;

    }
}
