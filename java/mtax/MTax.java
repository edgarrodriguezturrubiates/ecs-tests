import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MTax implements Constant {
    
    public MTax(){
        
    }
    
    public static List<String> validate(List<X_Tax> xTaxList) {
        
        List<String> errorList = new ArrayList<>(); //declares a list of errors
        List<String> validIds = new ArrayList<>(); //declares a list of valids Ids
        int cont = 0; //declaration of a count variable
        List<X_Tax> xt = TaxsByListId(validIds, false); //Declares a new list of taxes
        HashMap<String, X_Tax> map_taxs = new HashMap<String, X_Tax>(); //HashMap with taxId as key and TaxObject as values 
        
        
        //Check if the xTaxList has data
        if(xTaxList != null && xTaxList.size() > 0) {
            
            for (X_Tax tax : xTaxList) {
                
                //Check if the id exist
                if(tax.getId() != null){
                    validIds.add(tax.getId().toString());
                }

                //Check if the tax is declared
                if(tax.getTax() == null) {
                    errorList.add("El impuesto es obligatorio");
                }

                //Check if the tax is local
                if(tax.isLocal()){
                    //Is local
                    //Check if the tax is trasladado and if exist a value in the amount
                    if(tax.isTrasladado() && tax.getAmount() == null ) {
                        errorList.add("El importe es obligatorio");
                    }
                } 
                else {
                    //Is not local
                    //Check if exist a value in the amount
                    if(tax.getAmount() == null ) {
                        errorList.add("El importe es obligatorio");
                    }

                    //add 1 to the count if is not local
                    cont++;
                }
            }


            //Check if it has at least one not local tasa
            if(cont<=0){
                errorList.add("Debe de incluir al menos una tasa no local");
            }

            //Check if it exist any validIds
            if(validIds.size() > 0){

                //Check if all the data in validIds is in xt 
                if(xt.size() != validIds.size()){
                    errorList.add("Existen datos no guardados previamente");
                }
                else {

                    //Filling the hashMap with the values in the list Xt
                    for(X_Tax tax : xt){
                        map_taxs.put(tax.getId().toString(), tax);
                    }

                    for(int i = 0; i < xTaxList.size(); i++){

                        //Filling the CreatedDate with the dates from the hashMap
                        if(xTaxList.get(i).getId() != null){
                            xTaxList.get(i).setCreated(
                                map_taxs.get(xTaxList.get(i).getId().toString())
                                .getCreated());
                        }
                    }
                }
            }
        }
        else {
            errorList.add("El documento no tiene tasas");
        }

        //return a list of strings with the errors
        return errorList;
    }
  