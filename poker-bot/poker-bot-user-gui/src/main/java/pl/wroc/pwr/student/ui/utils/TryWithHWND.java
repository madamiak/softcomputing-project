package pl.wroc.pwr.student.ui.utils;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import com.sun.jna.win32.StdCallLibrary;

import java.util.ArrayList;
import java.util.List;

public class TryWithHWND {
    private final String RECOGNIZE_BY = "Hyper";
    private List<HWND> handles;
    private List<String> names;
    public interface User32 extends StdCallLibrary {
        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
        boolean EnumWindows(WinUser.WNDENUMPROC lpEnumFunc, Pointer arg);
        int GetWindowTextA(HWND hWnd, byte[] lpString, int nMaxCount);
    }

    public List<HWND> getHandles() {
        refreshLists();
        return handles;
    }

    public List<String> getLastNames() {
        return names;
    }
    private void refreshLists(){
        handles = new ArrayList<HWND>();
        names = new ArrayList<String>();
        final User32 user32 = User32.INSTANCE;
        user32.EnumWindows(new WNDENUMPROC() {
            int count = 0;
            @Override
            public boolean callback(HWND hWnd, Pointer arg1) {
                byte[] windowText = new byte[512];
                user32.GetWindowTextA(hWnd, windowText, 512);
                String wText = Native.toString(windowText);

                // get rid of this if block if you want all windows regardless of whether
                // or not they have text
                if (wText.isEmpty()) {
                    return true;
                }

                if(wText.contains(RECOGNIZE_BY)){
                    handles.add(hWnd);
                    names.add(wText);
                }
                return true;
            }
        }, null);
    }
}