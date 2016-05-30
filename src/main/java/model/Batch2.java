package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class Batch2 {
    String code;
    String name;
    String description;
    String output;

    public Batch2(String code, String name, String description, String output) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.output = output;

    }

    public Batch2() {
    }
    
    public String getCode() { return code;}

    public String getName() { return name;}

    public String getDescription() { return description;}

    public String getOutput() { return output;}

    public void setCode(String code) { this.code = code;}

    public void setName(String name) { this.name = name;}

    public void setDescription(String description) { this.description = description;}

    public void setOutput(String output) { this.output = output;}

  
}
    /*
    public static class Input {
        
        public ArrayList<Param> params;
        
        public Input(ArrayList<Param> params) {
            this.params = params;
        }     
        
        public ArrayList<Param> getParams(){ return params;}
        
        public void setParams(ArrayList<Param> params){ this.params = params;}
    
    }*/