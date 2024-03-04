package multi.threading;

public interface TransactionAnalysisFunctionality {
    void filterBasedOnDate(Transaction[] myData,Integer startDate,Integer endDate);
    void leastAmountTransferred(Transaction[] myData);
    void maximumAmountTransferred(Transaction[] myData);
    void numberOfTransaction(Transaction[] myData,String beneficiary);
    void filterBasedRemarks(Transaction[] myData,String remarks);
    void list(Transaction[] myData);
    void sortBasedOnBeneficiary(Transaction[] myData);
    void sortBasedOnAmount(Transaction[] myData);
}
