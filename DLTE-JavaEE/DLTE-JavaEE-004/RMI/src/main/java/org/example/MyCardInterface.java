package org.example;

import org.example.entity.Account;
import org.example.entity.Transaction;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MyCardInterface extends Remote {
   List<String> fetchDetailsBasedOnBalance() throws RemoteException;
}
