package com.noteapp.Controll;

public class ConnectivityHelper {

    public static boolean isConnected() {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = process.waitFor();
            return exitValue == 0;
        } catch (Exception ex) {
            return false;
        }

    }
}
