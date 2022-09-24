package json.country;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = { "isoCode", "alpha2Code", "countryCode", "countryName" })
public class PhoneCountryCodeAlpha {
  private CountryName countryName;
  private String countryCode;
  private String isoCode;
  private String alpha2Code;

  public String getAlpha2Code() {
    return alpha2Code;
  }

  public void setAlpha2Code(String alpha2Code) {
    this.alpha2Code = alpha2Code;
  }

  public PhoneCountryCodeAlpha() {
  }

  public PhoneCountryCodeAlpha(CountryName countryName, String countryCode, String isoCode) {
    this.countryName = countryName;
    this.countryCode = countryCode;
    this.isoCode = isoCode;
  }

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
