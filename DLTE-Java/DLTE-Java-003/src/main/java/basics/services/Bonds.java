package basics.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bonds {
    private Integer maturityAmount;
    private Double interestRate;
    private String taxStatus;
    private String bondHolder;
    private Integer period;

    public static void main(String[] args) {
        Bonds bondFund[]={
             new Bonds(350000,12.5,"Tax-Exempt","Yastika",7),
             new Bonds(1500000,10.0,"Taxable","Ankitha",11),
             new Bonds(250000,10.9,"Tax-Exempt","Amit",6),
             new Bonds(2300000,15.2,"Taxable","Anuj",18),
             new Bonds(2500000,17.0,"Taxable","Rita",14),
        };
    Bonds bond = new Bonds();
    bond.sortAndView(bondFund);
    }

public void sortAndView(Bonds[] bond){
    Bonds temp=null;
    for(int index=0;index<bond.length-1;index++){
        for (int select=0;select<bond.length-index-1;select++){
            if(bond[select].getInterestRate().compareTo(bond[select+1].getInterestRate())<0){
                temp=bond[select];
                bond[select]=bond[select+1];
                bond[select+1]=temp;
            }
        }
    }
    System.out.println("Available bonds in descending order");
    for(Bonds each:bond){
        System.out.println(each);
    }
}

}
