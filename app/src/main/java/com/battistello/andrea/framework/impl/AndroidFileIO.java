package com.battistello.andrea.framework.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import com.battistello.andrea.framework.FileIO;

public class AndroidFileIO implements FileIO {
    AssetManager assets;
    String externalStoragePath;
    String internalStoragePath;



    public AndroidFileIO(AssetManager assets) {
        this.assets = assets;
        this.internalStoragePath = Environment.getDataDirectory().getAbsolutePath() + File.separator;
        /*this.externalStoragePath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator;*/
    }

    @Override
    public InputStream readAsset(String fileName) throws IOException {
        return assets.open(fileName);
    }

    @Override
    public InputStream readFile(String fileName) throws IOException {
//        return new FileInputStream(externalStoragePath + fileName);
        return new FileInputStream(internalStoragePath + fileName);
    }

    @Override
    public OutputStream writeFile(String fileName) throws IOException {
//        return new FileOutputStream(externalStoragePath + fileName);
        return new FileOutputStream(internalStoragePath + fileName);
    }
}
