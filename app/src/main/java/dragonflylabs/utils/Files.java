package dragonflylabs.utils;

import android.os.Environment;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by caprinet on 10/14/14.
 */
public class Files {

    public static String FILES_PATH = Environment.getExternalStorageDirectory()+"/GCC/Woolworth/";
    public static String STORE_PATH = "tiendas.json";


    @SuppressWarnings("resource")
    public static Object loadJSON(String path,String name, Class<?> clss){
        File file = new File(path, name);
        if(file.exists()){
            try{
                Gson gson = new Gson();
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String cad = "";
                StringBuilder sb = new StringBuilder();
                while((cad = br.readLine()) != null){
                    sb.append(cad);
                }
                return gson.fromJson(sb.toString(), clss);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }else{
            return null;
        }
    }

    public static void saveJSON(Object response){
        File dir = new File(FILES_PATH);
        if(!dir.exists())
            dir.mkdir();
        try{
            File file = new File(FILES_PATH+STORE_PATH);
            if(!file.exists()){
                file.createNewFile();
            }
            OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(file, false));
            Gson gson = new Gson();
            String json = gson.toJson(response);
            fout.write(json);
            fout.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
