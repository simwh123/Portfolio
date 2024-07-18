package com.simwo.spring.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import org.apache.commons.lang3.StringUtils;

@Service
public class MapleService {

    public String ocid(String Id) {
        try {
            String API_KEY = "test_68643fe47ebc4f80be9a76906395cb7580d3655074fa330812117bb8cc1b74d4efe8d04e6d233bd35cf2fabdeb93fb0d";
            String characterName = URLEncoder.encode(Id, StandardCharsets.UTF_8);

            String urlString = "https://open.api.nexon.com/maplestory/v1/id?character_name=" + characterName;
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-nxopen-api-key", API_KEY);

            int responseCode = connection.getResponseCode();

            BufferedReader in;
            if (responseCode == 200) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String jsonString = response.toString();
            JSONObject jsonObject = new JSONObject(jsonString);
            String ocid = jsonObject.getString("ocid");

            return ocid;
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return "";
    }

    public ArrayList<String[]> item(String Id) {
        ArrayList<String[]> objects = new ArrayList<>();
        try {
            String API_KEY = "test_68643fe47ebc4f80be9a76906395cb7580d3655074fa330812117bb8cc1b74d4efe8d04e6d233bd35cf2fabdeb93fb0d";
            String ocid = URLEncoder.encode(ocid(Id), StandardCharsets.UTF_8);

            String urlString = "https://open.api.nexon.com/maplestory/v1/character/item-equipment?ocid="
                    + ocid;

            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-nxopen-api-key", API_KEY);

            int responseCode = connection.getResponseCode();

            BufferedReader in;
            if (responseCode == 200) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // JSON 문자열을 String으로 변환
            String jsonString = response.toString();

            // 문자열을 JSONObject로 변환
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray item_equipment = jsonObject.getJSONArray("item_equipment");

            if (item_equipment != null) {
                for (int i = 0; i < item_equipment.length(); i++) {

                    JSONObject potential_option = item_equipment.getJSONObject(i);
                    String item_equipment_part = potential_option.optString("item_equipment_part");
                    String item_name = potential_option.optString("item_name");
                    String potential_option1 = potential_option.optString("potential_option_1");
                    String potential_option2 = potential_option.optString("potential_option_2");
                    String potential_option3 = potential_option.optString("potential_option_3");
                    String additional_potential_option1 = potential_option.optString("additional_potential_option_1");
                    String additional_potential_option2 = potential_option.optString("additional_potential_option_2");
                    String additional_potential_option3 = potential_option.optString("additional_potential_option_3");
                    String item_icon = potential_option.optString("item_icon");
                    if (item_name == null)
                        item_name = "";
                    if (item_equipment_part == null)
                        item_equipment_part = "";
                    if (potential_option1 == null)
                        potential_option1 = "";
                    if (potential_option2 == null)
                        potential_option2 = "";
                    if (potential_option3 == null)
                        potential_option3 = "";
                    if (additional_potential_option1 == null)
                        additional_potential_option1 = "";
                    if (additional_potential_option2 == null)
                        additional_potential_option2 = "";
                    if (additional_potential_option3 == null)
                        additional_potential_option3 = "";
                    if (item_icon == null)
                        item_icon = "";
                    objects.add(new String[] {
                            item_equipment_part,
                            potential_option1,
                            potential_option2,
                            potential_option3,
                            additional_potential_option1,
                            additional_potential_option2,
                            additional_potential_option3,
                            item_icon,
                            item_name
                    });

                }
            }
            return objects;

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return objects;
    }

    public String[] character(String Id) {
        try {
            String API_KEY = "test_68643fe47ebc4f80be9a76906395cb7580d3655074fa330812117bb8cc1b74d4efe8d04e6d233bd35cf2fabdeb93fb0d";
            String ocid = URLEncoder.encode(ocid(Id), StandardCharsets.UTF_8);

            String urlString = "https://open.api.nexon.com/maplestory/v1/character/basic?ocid=" + ocid;
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-nxopen-api-key", API_KEY);

            int responseCode = connection.getResponseCode();

            BufferedReader in;
            if (responseCode == 200) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String jsonString = response.toString();
            JSONObject jsonObject = new JSONObject(jsonString);
            String img = jsonObject.getString("character_image");
            String worldName = jsonObject.getString("world_name");
            return new String[] { img, worldName };
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return new String[] {};
    }

    public String stringSplit(String Item) {

        String numbersOnly = StringUtils.getDigits(Item);

        return numbersOnly;
    }

}
