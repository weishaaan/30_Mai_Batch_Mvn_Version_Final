package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import noNamespace.BatchType;
import noNamespace.CATALOGDocument;
import noNamespace.ParamType;
import org.apache.xmlbeans.XmlException;

/*
    use XMLBean to get data from xml file.
*/    
public class batchXmlBean {
    private String filename = null;
    
    public batchXmlBean(String filename) {
        super();
        this.filename = filename;
    }
    
    public void batchReader() throws XmlException, IOException {

        File xmlFile = new File(filename);
        CATALOGDocument doc = CATALOGDocument.Factory.parse(xmlFile);
        BatchType[] batches = doc.getCATALOG().getBATCHArray();
        Map<Long,Batch> catalogue = BatchDatabase.getBatches();
              
        for (int i = 0; i < batches.length; i++) {
            BatchType batch = batches[i];
            Batch btc = new Batch();
            List<Batch.Param> lst = new ArrayList<>();
            btc.setCode(batch.getCODE());
            btc.setName(batch.getNAME());
            btc.setDescription(batch.getDESCRIPTION());
            btc.setOutput(batch.getOUTPUT());

            /*
            println("Batch#" + i);
            println("Code :" + batch.getCODE());
            println("Name :" + batch.getNAME());
            println("DESCRIPTION :" + batch.getDESCRIPTION());
            println("Output:" + batch.getOUTPUT());
            */
            // Input
            ParamType[] inputs = batch.getINPUT().getPARAMArray();

            for (int j = 0; j < inputs.length; j++) {
            ParamType param = inputs[j];
            Batch.Param prm = new Batch.Param();
            prm.setParamName(param.getPARAMNAME());
            prm.setParamDefaultValue(param.getDEFAULTVALUE());
            prm.setParamLabel(param.getLABEL());
            lst.add(prm);

            /*
            println("Param#" + j);
            println("Param Name :" + param.getPARAMNAME());
            println("Param Default Value :" + param.getDEFAULTVALUE());
            println("Param label" + param.getLABEL());
            */
            }
            btc.setInput(lst);
            catalogue.put(Long.valueOf(i), btc);
        }    
    }
    private void println(String str) {
        System.out.println(str);
    }
}

