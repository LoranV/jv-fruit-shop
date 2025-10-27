package core.basesyntax.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterImpl implements Writer {
    private File fileName;
    private final FileWriter fw;

    public WriterImpl(String textName) {
        File file = new File(textName);
        try {
            fw = new FileWriter(file);
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
