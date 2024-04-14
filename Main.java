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
                    .url("https://v6.exchangerate-api.com/v6/" + apiKey).build();

            // Creat json response
            Response response = client.newCall(request).execute();
            JSONObject jsonResponse = new JSONObject(response.body().string());

            // Finally, lets do the conversation :)
            double exchangeRate = jsonResponse.getJSONObject("rates").getDouble(targetCurrency);

        }
        catch (Exception error) {
            System.err.println("Error fetching exchange rates: " + error.getMessage());
        }
    }
}