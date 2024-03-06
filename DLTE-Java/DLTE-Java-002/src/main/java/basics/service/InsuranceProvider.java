package basics.service;

import java.util.Scanner;

public class InsuranceProvider {
    public static void main(String[] args) {
  String StarHealthInsurance[]={"hospitalisationCoverages","complimentaryCheckups","cashlessTreatment"};
  String AdithyaBirlaHealthInsurance[]={"daycareProcedures","hospitalisationCoverages", "accidentalHospitalization"};
  String RelianceGeneral[]={"criticalIllnessCover","complimentaryCheckups","maternityBenefits"};

for(String each:args){
    for(String select:StarHealthInsurance){
        if(each.equalsIgnoreCase(select)) System.out.println("StarHealthInsurance Offers: "+select);
    }
}
 for(String each:args){
     for(String select:AdithyaBirlaHealthInsurance){
             if(each.equalsIgnoreCase(select)) System.out.println("AdithyaBirlaHealthInsurance Offers: "+select);
          }
        }
   for(String each:args){
        for(String select:RelianceGeneral){
                if(each.equalsIgnoreCase(select)) System.out.println("RelianceGeneral Offers: "+select);
            }
        }

    }
}
