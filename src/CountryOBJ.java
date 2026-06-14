public class CountryOBJ {

    private String country; private String city;

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCity(String city) {
        if (city == null || city.isBlank()) {
            this.city = city == null ? null : city.trim();
            return;
        }

        String[] words = city.trim().split("\\s+");
        StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                formatted.append(' ');
            }
            String word = words[i];
            formatted.append(Character.toUpperCase(word.charAt(0)));
            if (word.length() > 1) {
                formatted.append(word.substring(1).toLowerCase());
            }
        }

        this.city = formatted.toString();
    }

    public String getCity() {
        return this.city;
    }

}
