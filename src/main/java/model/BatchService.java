package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import noNamespace.BatchType;
import noNamespace.CATALOGDocument;
import noNamespace.ParamType;
import org.apache.xmlbeans.XmlException;

public class BatchService {
    
    private Map<Long,Batch> catalogue = BatchDatabase.getBatches();
    
    public BatchService() throws XmlException, IOException{
        String filename = "src/main/java/Resource/batch_catalogue.xml";
        File xmlFile = new File(filename);
        CATALOGDocument doc = CATALOGDocument.Factory.parse(xmlFile);
        BatchType[] batches = doc.getCATALOG().getBATCHArray();
              
        for (int i = 0; i < batches.length; i++) {
            BatchType batch = batches[i];
            Batch btc = new Batch();
            btc.setCode(batch.getCODE());
            btc.setName(batch.getNAME());
            btc.setDescription(batch.getDESCRIPTION());
            btc.setOutput(batch.getOUTPUT());
            ParamType[] inputs = batch.getINPUT().getPARAMArray();
            List<Batch.Param> lst = new ArrayList<>();
            for (int j = 0; j < inputs.length; j++) {
                ParamType param = inputs[j];
                Batch.Param prm = new Batch.Param();
                prm.setParamName(param.getPARAMNAME());
                prm.setParamDefaultValue(param.getDEFAULTVALUE());
                prm.setParamLabel(param.getLABEL());
                lst.add(prm);

            }
            btc.setInput(lst);
            catalogue.put(Long.valueOf(i), btc);
        }
    }
    public List<Batch> getAllBatches(){
        return new ArrayList<Batch>(catalogue.values());
    }
    public Batch getBatch(String code) throws XmlException, IOException{
        List<Batch> bts = new ArrayList<Batch>(catalogue.values());
        for(int i=0;i<bts.size();i++){
            Batch batch_ = bts.get(i);
            if((batch_.getCode()).equals(code))
            return batch_;
        }
        return null;
    }
    
    /*
    public List<Batch> addBatch(Batch batch){
        catalogue.put(Long.valueOf(batch.getCode()), batch);
        return new ArrayList<Batch>(catalogue.values());
    }
    
    public List<Batch> removeBatch(String code){
        catalogue.remove(Long.valueOf(code));     
        return new ArrayList<Batch>(catalogue.values());
    }
    */
}
