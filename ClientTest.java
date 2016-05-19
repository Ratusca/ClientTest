/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import net.sf.json.JSONObject;

/**
 *
 * @author Alexandru
 */
public class ClientTest {

    private static Socket socket;

    public static void main(String[] args) {
        try {

            socket = new Socket("169.254.123.29", 63400);

            BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter write = new PrintWriter(socket.getOutputStream(), true);

            String option;

            System.out.println("Insert word: ");
            option = br.readLine();

            write.println(option);

            while (!"exit".equals(option)) {
                switch (option) {

                    case "login": {
                        String response;
                        write.println("Test-User");
                        write.println("password1");
                        response = read.readLine();
                        System.out.println(response);
                    }
                    break;

                    case "register": {
                        String response;
                        write.println("Rata");
                        write.println("Alexandru");
                        write.println("Test-UserNUEXISTA");
                        write.println("password1");
                        write.println("alexandru.rata@gmail.com");
                        write.println("0722");

                        response = read.readLine();
                        System.out.println(response);
                    }
                    break;

                    case "location": {
                        write.println("47.175859");
                        write.println("27.568566");
                        write.println("500");

                        String results = new String();
                        String input;
                        
                        while (!"".equals(input = read.readLine())) {
                            results += input + "\n";
                        }
                        System.out.println();
                        System.out.println(results);
                    }
                    break;

                    case "insertData": {
                        String response;
                        JSONObject insertData = new JSONObject()
                                .element("city", "Iasi")
                                .element("street_name", "Musatini")
                                .element("street_number", "27")
                                .element("block", "H12")
                                .element("suite", "A")
                                .element("username", "Test-User")
                                .element("picture", "www.google.ro")
                                .element("latitude", "47")
                                .element("longitude", "25");

                        write.println(insertData);

                        response = read.readLine();
                        System.out.println(response);
                    }
                    break;
                }
                System.out.println("Insert word: ");
                option = br.readLine();
                write.println(option);
            }
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
