package org.example;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Creat a scanner
        Scanner input = new Scanner(System.in);

        // The API key
        String apiKey = "cb4befcbbdbac3a5b9c31f98";

        // Take the inputs from user: base and target currencies, and the amount to be converted

        System.out.print("Base Currency (Ex: SAR): ");
        String baseCurrency = input.next();

        System.out.print("Target Currency (Ex: USD): ");
        String targetCurrency = input.next();

        System.out.print("Amount (Ex: 100): ");
        Double amountToConvert = input.nextDouble();

        try{

            // initializing OkHttpClient
            OkHttpClient client = new OkHttpClient();

            // Making Http Requests
            Request request = new Request.Builder()
                    .url("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency).build();

            // Excute the request and parse the json response
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string(); //read the entire response

            //check if the response is valid
            if (responseBody.startsWith("{")) {

                JSONObject jsonResponse = new JSONObject(responseBody);

                // Extract the exchange rate
                double exchangeRate = jsonResponse.getJSONObject("conversion_rates")
                        .getDouble(targetCurrency);

                // Finally, lets do the conversation :)
                double convertedAmount = amountToConvert * exchangeRate;

                //Print the results
                System.out.println("The exchange rate is: " + exchangeRate);
                System.out.printf("The converted amount is %.2f %S", convertedAmount, targetCurrency);

            } else System.out.println("Error");
        }
        catch (Exception error) {
            System.err.println("Error fetching exchange rates: " + error.getMessage());
        }
    }
}
