package core.basesyntax.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DBReaderImpl implements DBReader {
    File fileName;
    FileReader reader;
    BufferedReader bf;

    public DBReaderImpl(String fileName) {
        File file = new File(fileName);
        try {
            reader = new FileReader(file);
            bf = new BufferedReader(reader);
        } catch(FileNotFoundException e) {
            throw new RuntimeException("Can't open file " + fileName, e);}
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
