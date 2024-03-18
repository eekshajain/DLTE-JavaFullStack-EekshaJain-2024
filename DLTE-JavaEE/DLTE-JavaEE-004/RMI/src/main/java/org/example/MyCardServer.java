package org.example;


import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.middleware.DatabaseTarget;
import org.example.services.AccountService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class MyCardServer extends UnicastRemoteObject implements MyCardInterface, Serializable {

    private static Context context;
    private Registry registry;
    private AccountService service;
    protected MyCardServer() throws RemoteException, NamingException {
        registry= LocateRegistry.createRegistry(3030);
       service=new AccountService(new DatabaseTarget());
        Hashtable properties=new Hashtable();
        properties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
        properties.put(Context.PROVIDER_URL,"rmi://localhost:3030");
        context=new InitialContext(properties);
    }


    @Override
    public List<String> fetchDetailsBasedOnBalance() throws RemoteException {
        List<Transaction> balance=service.callFindAll().stream().filter(each->each.getBalance()>=3000).collect(Collectors.toList());
        List<String> users =new ArrayList<>();
        for(Transaction transaction:balance){
            users.add(transaction.getUser());
        }
        return users;
    }
    public static void main(String[] args) throws NamingException, RemoteException {
        MyCardServer myCardServer=new MyCardServer();
        context.bind("java:/card-filter",myCardServer);
    }
}

