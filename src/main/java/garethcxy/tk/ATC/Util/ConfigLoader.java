package garethcxy.tk.ATC.Util;

import org.ini4j.Ini;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ConfigLoader {
    private Ini configIni = null;

    public ConfigLoader(){
        try{
            configIni = LoadIni();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public<T> T getContent(String section, String option, Class<T> type){
        return configIni.get(section, option, type);
    }

    private void setDefault(Ini iniFile) throws IOException {
        setContent(iniFile, "Mail", "username", "");
        setContent(iniFile, "Mail", "password", "");
        setContent(iniFile, "Mail", "addressList", "");
        setContent(iniFile, "Properties", "taxRate", 0.13);
        setContent(iniFile, "Properties", "OrderThreshold", 10);
        setContent(iniFile, "ShopRate", "JNC", 0.05);
        setContent(iniFile, "ShopRate", "NORMAL", 0);
        setContent(iniFile, "ShopRate", "UBER", 0.113);
        iniFile.store();
    }

    private void setContent(Ini iniFile, String section, String option, Object value){
        iniFile.put(section, option, value);
    }

    private Ini LoadIni() throws IOException {
        Ini iniFile = new Ini();

        try{
            iniFile.load(FindFile());
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return iniFile;
    }

    private File FindFile() throws IOException {
        File newFile = new File("config.ini");
        if(newFile.createNewFile()){
            // Create new ini file.
            Ini ini = new Ini(newFile);
            try{
                setDefault(ini);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return newFile;
    }
}
