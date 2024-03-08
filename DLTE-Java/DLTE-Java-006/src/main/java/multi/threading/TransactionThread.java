package multi.threading;

public class TransactionThread {
    public static void main(String[] args) {
        TransactionAnalysis transactionAnalysisFunctionality=new TransactionAnalysis();
        TransactionAnalysis transactionAnalysis=new TransactionAnalysis();
        Thread vanita=new Thread(transactionAnalysisFunctionality::displayRemarks,"Vanita");
        vanita.start();
        Thread sunita=new Thread(transactionAnalysisFunctionality::displaySentTo,"Sunita");
        sunita.start();
        Thread rani=new Thread(transactionAnalysisFunctionality::totalAmountOfTransaction,"Rani");
        rani.start();
        Thread radha=new Thread(transactionAnalysis,"Radha");
        Thread rita=new Thread(transactionAnalysis,"Rita");
        Thread sonu=new Thread(transactionAnalysis,"Sonu");
        Thread tapu=new Thread(transactionAnalysis,"Tapu");
        Thread goli=new Thread(transactionAnalysis,"Goli");
        Thread pinku=new Thread(transactionAnalysis,"Pinku");
        Thread gogi=new Thread(transactionAnalysis,"Gogi");
        radha.start();
        rita.start();
        sonu.start();
        tapu.start();
        gogi.start();
        pinku.start();
        goli.start();

    }
}
