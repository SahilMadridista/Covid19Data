package listClass;

public class ListItem {

    private String countryName,totalCases,totalrecovered,totalDeaths;

    public ListItem(){

    }

    public ListItem(String countryName, String totalCases, String totalrecovered, String totalDeaths) {
        this.countryName = countryName;
        this.totalCases = totalCases;
        this.totalrecovered = totalrecovered;
        this.totalDeaths = totalDeaths;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    public String getTotalrecovered() {
        return totalrecovered;
    }

    public void setTotalrecovered(String totalrecovered) {
        this.totalrecovered = totalrecovered;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }
}
