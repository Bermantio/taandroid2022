package com.example.lazismu;

import android.os.Handler;
import android.os.Looper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConnectionManager {
    public static boolean isInternetAvailable() {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        Future<Boolean> future = exec.submit(() -> {
            try {
                InetAddress address = InetAddress.getByName("www.google.com");
                return !address.equals("");
            } catch (UnknownHostException e) {
                return false;
            }
        });

        try {
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            return false;
        }
    }
}
