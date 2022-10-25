package md4;
import java.io.*;

public class FileWork {
    static void reportCreate(String file_name) throws  Exception{
        FileWriter nFile = new FileWriter(file_name);

        nFile.write("");

        nFile.close();
    }
    static void reportWrite(String file_name,String message) throws Exception{
        FileWriter nFile = new FileWriter(file_name,true);

            nFile.write(message + "\n");

        nFile.close();
    }

}
