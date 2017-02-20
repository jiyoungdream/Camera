package co.kr.timercamera.util;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by si on 2017. 2. 17..
 */

public class HashMapIO {

    public static void writeHahsMapIO(HashMap<String, ArrayList<String>> obj) {
        try {
            File sdCard = Environment.getExternalStorageDirectory();
            File fileOne = new File(sdCard.getAbsolutePath() + Constants.FILE_FOLDER_PATH + "/" + Constants.PIC_LIST_FILE_NAME);
            FileOutputStream fos = new FileOutputStream(fileOne);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(obj);
            oos.flush();
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeArrayListtoKey(String key, ArrayList list) {
        HashMap orgHashMap = readHashMapIO();
        orgHashMap.put(key, list);
        writeHahsMapIO(orgHashMap);
    }

    public static HashMap readHashMapIO() {

        HashMap obj = null;

        try {
            File sdCard = Environment.getExternalStorageDirectory();
            File toRead = new File(sdCard.getAbsolutePath() + Constants.FILE_FOLDER_PATH + "/" + Constants.PIC_LIST_FILE_NAME);

            if (toRead.exists() && toRead.length() > 0) {
                FileInputStream fis = new FileInputStream(toRead);
                ObjectInputStream ois = new ObjectInputStream(fis);
                HashMap<String, ArrayList<String>> mapInFile = (HashMap<String, ArrayList<String>>) ois.readObject();
                ois.close();
                fis.close();

                //print All data in MAP
                obj = mapInFile;
                for (Map.Entry<String, ArrayList<String>> m : mapInFile.entrySet()) {
                    Log.d(TAG, m.getKey() + " : " + m.getValue());
                    ArrayList<String> aa = new ArrayList<>(m.getValue());
                    Log.d(TAG, "readHashMapIO: " + aa.get(0));
                }
            }
            else {
                return new HashMap();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (OptionalDataException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static ArrayList getArrayFromHashMap(String key) {

        try {
            File sdCard = Environment.getExternalStorageDirectory();
            File toRead = new File(sdCard.getAbsolutePath() + Constants.FILE_FOLDER_PATH + "/" + Constants.PIC_LIST_FILE_NAME);

            if (toRead.exists() && toRead.length() > 0) {
                FileInputStream fis = new FileInputStream(toRead);
                ObjectInputStream ois = new ObjectInputStream(fis);

                HashMap<String, ArrayList<String>> mapInFile = (HashMap<String, ArrayList<String>>) ois.readObject();

                ois.close();
                fis.close();
                //print All data in MAP
                if (mapInFile.containsKey(key)) {
                    ArrayList<String> list = new ArrayList<>(mapInFile.get(key));
                    return list;
                }
            }
            else {
                return new ArrayList();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (OptionalDataException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> newList = new ArrayList<>();
        return newList;
    }
}
