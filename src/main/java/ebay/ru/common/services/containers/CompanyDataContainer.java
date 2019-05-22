package ebay.ru.common.services.containers;

import com.google.gson.JsonElement;
import lombok.Data;

@Data
public class CompanyDataContainer {
    private static CompanyDataContainer instance;

    private CompanyDataContainer() {
    }

    public static CompanyDataContainer getInstance() {
        if (instance == null) {
            instance = new CompanyDataContainer();
        }
        return instance;
    }

    // Company type (both, seller, buyer)
    private String companytype;
    // Company admin
    private String login;
    private String password;
    // Company GD
    private String GDlogin;
    private String GDpassword;
    // Activation links in dropmail
    private String actADMIN;
    private String actGD;
    private String actLP;
    // Company data
    private JsonElement companys;
    private int numbercompany;
//    private Company company;

    private String inn;
    private String kpp;
    private String ogrn;
    private String fullname;
    private String shortname;
    private String status;
    private String gdname;
    // domain
    private String domain;
    // bank R
    private String raschschet;
    private String korschet;
    private String bank;
    private String bikbank;
    //new person
    private String LPname;
    private String LPsurname;
    private String LPpatronymic;
    private String LPphone;
    private String LPworkphone;
    private String LPlogin;
    private String LPpassword;
}