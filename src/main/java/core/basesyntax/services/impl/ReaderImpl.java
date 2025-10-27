package core.basesyntax.services.impl;

import core.basesyntax.services.Reader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderImpl implements Reader {
    private File fileName;
    private FileReader reader;

    public ReaderImpl(String fileName) {
        File file = new File(fileName);
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't open file " + fileName, e);
        }
    }

    @Override
    public List<String> read() {
        List<String> list = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(reader)) {
            String header = bf.readLine();
            String line = bf.readLine();
            while (line != null) {
                list.add(line);
                line = bf.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file " + fileName, e);
        }
        return list;
    }
}
