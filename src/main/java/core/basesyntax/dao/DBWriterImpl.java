package core.basesyntax.dao;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class DBWriterImpl  implements DBWriter {
    File fileName;
    FileWriter fw;
    BufferedWriter bw;

    public DBWriterImpl(String textName) {
        File file = new File(textName);
        try
        {
            fw = new FileWriter(file);
            bw =  new BufferedWriter(fw);
        } catch (IOException e) {
            throw new RuntimeException("Can't open file: " + fileName + e);
        }
    }

    @Override
    public boolean write(String text) {
        try {
            fw.write(text);
            fw.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName + e);
        }
    }
}
