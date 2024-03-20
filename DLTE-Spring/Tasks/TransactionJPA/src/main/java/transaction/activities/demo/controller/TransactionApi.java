package transaction.activities.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import transaction.activities.demo.model.Transaction;
import transaction.activities.demo.services.TransactionServices;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionApi {
    @Autowired
    TransactionServices transactionServices;
    /*
    To add new Transaction as xml
    <Transaction>
        <date>2024-03-02</date>
        <user>Eeksha</user>
        <amount>200.0</amount>
        <balance>45000.0</balance>
</Transaction>
     */
  @PostMapping(value = "/" ,consumes = "application/xml",produces = "application/xml")
    public Transaction callNewTransaction(@RequestBody Transaction transaction){
        return transactionServices.newTransaction(transaction);
    }
    @GetMapping(value = "/",produces = "application/xml")
    public List<Transaction> callDisplayAll(){
        return transactionServices.displayAll();
    }
    /*
    using native query
    make sure that ddl auto is update
    http://localhost:8085/transaction/find/Divija/withdrawal
    {
    "transactionID": 12346,
    "date": "2024-03-06",
    "user": "Divija",
    "amount": 3000.0,
    "balance": 50000.0
}
     */
    @GetMapping("/find/{name}/{type}")
    public Transaction callFindDetails(@PathVariable("name") String name,@PathVariable("type") String type){
      return transactionServices.findDetailsByUserAndType(name, type);
    }
    /*
    http://localhost:8085/transaction/findByAmount/250/5000
    [
    {
        "transactionID": 12346,
        "date": "2024-03-06",
        "user": "Divija",
        "amount": 3000.0,
        "balance": 50000.0
    },
    {
        "transactionID": 12347,
        "date": "2024-03-08",
        "user": "Anktha",
        "amount": 300.0,
        "balance": 10000.0
    }
]
in xml(@GetMapping(value="/findByAmount/{amount1}/{amount2}",produces = "application/xml"))
<List>
    <item>
        <transactionID>12346</transactionID>
        <date>2024-03-06</date>
        <user>Divija</user>
        <amount>3000.0</amount>
        <balance>50000.0</balance>
    </item>
    <item>
        <transactionID>12347</transactionID>
        <date>2024-03-08</date>
        <user>Anktha</user>
        <amount>300.0</amount>
        <balance>10000.0</balance>
    </item>
</List>
     */

    @GetMapping("/findByAmount/{amount1}/{amount2}")
    public List<Transaction> callFindByAmount(@PathVariable("amount1") double amount1,@PathVariable("amount2") double amount2){
        return transactionServices.findDetailsByAmountOfTransaction(amount1,amount2);
    }
}
