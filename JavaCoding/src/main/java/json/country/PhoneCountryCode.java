package json.country;


public class PhoneCountryCode {
  private CountryName countryName;
  private String countryCode;
  private String isoCode;

  public CountryName getCountryName() {
    return countryName;
  }

  public void setCountryName(CountryName countryName) {
    this.countryName = countryName;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getIsoCode() {
    return isoCode;
  }

  public void setIsoCode(String isoCode) {
    this.isoCode = isoCode;
  }
}

class CountryName {
  private String en;
  private String de;
  private String fr;
  private String it;

  public String getEn() {
    return en;
  }

  public void setEn(String en) {
    this.en = en;
  }

  public String getDe() {
    return de;
  }

  public void setDe(String de) {
    this.de = de;
  }

  public String getFr() {
    return fr;
  }

  public void setFr(String fr) {
    this.fr = fr;
  }

  public String getIt() {
    return it;
  }

  public void setIt(String it) {
    this.it = it;
  }
}