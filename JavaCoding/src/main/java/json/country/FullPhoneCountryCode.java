package json.country;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.List;

//class FullPhoneCountryCode {
//    List<Root> phoneCountryCodes;
//
//    public List<Root> getPhoneCountryCodes() {
//        return phoneCountryCodes;
//    }
//
//    public void setPhoneCountryCodes(List<Root> phoneCountryCodes) {
//        this.phoneCountryCodes = phoneCountryCodes;
//    }
//}

class FullPhoneCountryCode {
    public String name;
    @JsonProperty("alpha-2")
    public String alpha2;
    @JsonProperty("alpha-3")
    public String alpha3;
    @JsonProperty("country-code")
    public String countryCode;
    @JsonProperty("iso_3166-2")
    public String iso31662;
    public String region;
    @JsonProperty("sub-region")
    public String subRegion;
    @JsonProperty("intermediate-region")
    public String intermediateRegion;
    @JsonProperty("region-code")
    public String regionCode;
    @JsonProperty("sub-region-code")
    public String subRegionCode;
    @JsonProperty("intermediate-region-code")
    public String intermediateRegionCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(String alpha2) {
        this.alpha2 = alpha2;
    }

    public String getAlpha3() {
        return alpha3;
    }

    public void setAlpha3(String alpha3) {
        this.alpha3 = alpha3;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getIso31662() {
        return iso31662;
    }

    public void setIso31662(String iso31662) {
        this.iso31662 = iso31662;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public String getIntermediateRegion() {
        return intermediateRegion;
    }

    public void setIntermediateRegion(String intermediateRegion) {
        this.intermediateRegion = intermediateRegion;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getSubRegionCode() {
        return subRegionCode;
    }

    public void setSubRegionCode(String subRegionCode) {
        this.subRegionCode = subRegionCode;
    }

    public String getIntermediateRegionCode() {
        return intermediateRegionCode;
    }

    public void setIntermediateRegionCode(String intermediateRegionCode) {
        this.intermediateRegionCode = intermediateRegionCode;
    }
}
