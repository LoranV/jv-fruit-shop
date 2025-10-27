package core.basesyntax.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderImpl implements Reader {
    private File fileName;
    private final BufferedReader bf;

    public ReaderImpl(String fileName) {
        File file = new File(fileName);
        try {
            FileReader reader = new FileReader(file);
            bf = new BufferedReader(reader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't open file " + fileName, e);
        }
    }

    @Override
    public List<String> read() {
        List<String> list = new ArrayList<>();
        try {
            String header = bf.readLine();
            String line = bf.readLine();
            while (line != null) {
                list.add(line);
                line = bf.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error while reading file " + fileName);
        }
        return list;
    }
}
