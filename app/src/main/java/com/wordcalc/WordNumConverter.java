package com.wordcalc;

public class WordNumConverter {
    private static final String[] ones = {"Zero ", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine ", "Ten ",
            "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ",
            "Nineteen ", "Twenty "};
    private static final String[] tens = {"Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};
    private static final String[] huger = {"", "Thousand ", "Million ", "Billion ", "Trillion ", "Quadrillion ", "quintillion ",
            "Sextillion ", "Septillion ", "Octillion ", "quintillion ", "decillion "};

    public String number_to_words(String input) {
        int n = input.length(), set, tensPlace, hundredsPlace, onesPlace, sum;
        StringBuilder prefix = new StringBuilder();
        StringBuilder partial = new StringBuilder();
        int padding = n % 3;
        if (padding > 0) {
            prefix.append("0".repeat(3 - padding));
            n += (3 - padding);
        }
        String number = prefix + input;
        for (int i = 0; i < n; i += 3) {
            set = (n - i) / 3 - 1;
            onesPlace = Integer.parseInt(number.substring(i + 2, i + 3));
            tensPlace = Integer.parseInt(number.substring(i + 1, i + 2));
            hundredsPlace = Integer.parseInt(number.substring(i, i + 1));
            if (hundredsPlace > 0) {
                partial.append(ones[hundredsPlace]).append("Hundred ");
            }
            sum = tensPlace * 10 + onesPlace;
            if (sum <= 20) {
                partial.append(ones[tensPlace * 10 + onesPlace]);
            } else {
                partial.append(tens[tensPlace - 2]).append(ones[onesPlace]);
            }
            partial.append(huger[set]);
        }
        return partial.substring(0, partial.length() - 1);
    }
}
