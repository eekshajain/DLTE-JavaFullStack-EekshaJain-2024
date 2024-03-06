package org.example;

import java.util.ArrayList;
import java.util.Date;

public interface MyBank {
ArrayList<Loan> loans=new ArrayList<>();
void filterBasedDates(Date Start,Date End);
}
