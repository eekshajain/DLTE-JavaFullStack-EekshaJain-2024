package basics.service;

import java.util.Scanner;

public class InsuranceProvider {
    public static void main(String[] args) {
  String StarHealthInsurance[]={"hospitalisation coverages","complimentary checkups","cashless treatment"};
  String AdithyaBirlaHealthInsurance[]={"daycare procedures","hospitalisation coverages", "accidental hospitalization"};
  String RelianceGeneral[]={"critical illness cover","complimentary checkups","maternity benefits"};
  Scanner scanner=new Scanner(System.in);
  String featureOne="";
  System.out.println("Enter Feature");
  featureOne=scanner.nextLine();
  String companyOffer="";
 for(int index=0;index<StarHealthInsurance.length;index++){
  if(featureOne.toLowerCase().equals(StarHealthInsurance[index]))
      companyOffer+="StarHealthInsurance";
 }
 for(int index=0;index<AdithyaBirlaHealthInsurance.length;index++){
            if(featureOne.toLowerCase().equals(AdithyaBirlaHealthInsurance[index]))
                companyOffer+="AdithyaBirlaHealthInsurance";
        }
 for(int index=0;index<RelianceGeneral.length;index++){
            if(featureOne.toLowerCase().equals(RelianceGeneral[index]))
                companyOffer+="RelianceGeneral";
        }

        System.out.println("Companies that offer features are "+companyOffer);
    }
}
