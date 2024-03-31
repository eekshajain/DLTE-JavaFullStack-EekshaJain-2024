package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Activity {
    void create(List<CreditCard> creditCardArrayList) throws IOException, ClassNotFoundException;
}
