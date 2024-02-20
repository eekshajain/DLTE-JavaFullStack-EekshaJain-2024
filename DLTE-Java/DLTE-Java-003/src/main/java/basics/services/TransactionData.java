package basics.services;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;
import java.util.Date;
@Data
@AllArgsConstructor
public class TransactionData {
    private Date dateOfTransaction;
    private Integer amountOfTransaction;
    private String sentTo;
    private String remarks;
}
