package com.gm.util;

import com.gm.dao.SafePhoneNumberRepository;
import com.gm.model.SafePhoneNumber;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by michael on 9/3/17.
 */
@Component
public class SafePhoneNumberManager {

    @Value("${safePhoneNumber.secret}")
    private String secret;
    @Value("${safePhoneNumber.appkey}")
    private String appkey;
    @Value("${safePhoneNumber.unitID}")
    private String unitID;
    @Value("${safePhoneNumber.host}")
    private String host;
//    private String expiredTime;
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private SafePhoneNumberRepository safePhoneNumberRepository;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public boolean bindNumber(String phone, String safePhoneNumber){

        Date time = Calendar.getInstance().getTime();
        SortedMap<String, String> propMap = new TreeMap<>();
        propMap.put("ver", "2.0");
        propMap.put("msgid", UUID.randomUUID().toString());
        propMap.put("ts", simpleDateFormat.format(time));
        propMap.put("service", "SafeNumber");
        propMap.put("msgtype", "binding_Relation");
        propMap.put("appkey", appkey);

        propMap.put("unitID", unitID);
        propMap.put("prtms", phone);
        propMap.put("smbms", safePhoneNumber);
        propMap.put("subts", simpleDateFormat.format(time));
        propMap.put("uidType", "0");
        propMap.put("validitytime", "1");
        propMap.put("calldisplay", "2,2");

        StringBuilder stringBuilder = new StringBuilder("");
        propMap.keySet().stream().forEach(key -> stringBuilder.append(key).append(propMap.get(key)));
//        String sid = generateSign(stringBuilder.toString());
        try {
            String sid = SignUtils.signTopRequest(propMap, secret, "MD5");
            propMap.put("sid", sid);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String url = host + "/safenumberservicessm/api/manage/dataManage";
//            HttpClient httpClient = HttpClient.New(new URL(url));
            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
            multiValueMap.setAll(propMap);
            HttpEntity<MultiValueMap<String, String>> requestEntity  = new HttpEntity<MultiValueMap<String, String>>(multiValueMap, new HttpHeaders());
            String jsonpObject = restTemplate.postForObject(url, requestEntity, String.class);
            System.out.println(jsonpObject);

            return jsonpObject.indexOf("safenumber-in-bind") < 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String generateSign(String data){

        String algorithm="HmacMD5";
        Mac mac = null;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes("UTF8"), algorithm);
            mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            mac.init(secretKeySpec);
            return byte2hex(mac.doFinal(data.getBytes("UTF8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }
    @Transactional
    synchronized public SafePhoneNumber getUseableSafePhoneNumber(String phone){
        SafePhoneNumber safePhoneNumber = safePhoneNumberRepository.findFirstByBindPhone(phone);
//                safePhoneNumberRepository.findFirstByOrderByBindTimeAsc();
        if(safePhoneNumber != null && safePhoneNumber.getBindTime() != null){
            DateTime dt = new DateTime(safePhoneNumber.getBindTime());
            if(dt.plusMinutes(30).isBeforeNow()){
//                unBindNumber(safePhoneNumber.getNumber());
                return safePhoneNumber;
            }
        }
        safePhoneNumber = safePhoneNumberRepository.findFirstByOrderByBindTimeAsc();
        if(safePhoneNumber != null){
            unBindNumber(safePhoneNumber.getNumber());
        }
        safePhoneNumber.setBindPhone(phone);
        safePhoneNumber.setBindTime(Calendar.getInstance().getTime());
        safePhoneNumberRepository.save(safePhoneNumber);
        return safePhoneNumber;
    }

    public boolean unBindNumber(String safePhoneNumber) {

        Date time = Calendar.getInstance().getTime();
        SortedMap<String, String> propMap = new TreeMap<>();
        propMap.put("ver", "2.0");
        propMap.put("msgid", UUID.randomUUID().toString());
        propMap.put("ts", simpleDateFormat.format(time));
        propMap.put("service", "SafeNumber");
        propMap.put("msgtype", "remove_Relation");
        propMap.put("appkey", appkey);

        propMap.put("unitID", unitID);
//        propMap.put("prtms", phone);
        propMap.put("smbms", safePhoneNumber);
//        propMap.put("subts", simpleDateFormat.format(time));
//        propMap.put("uidType", "0");
//        propMap.put("validitytime", "1");

        StringBuilder stringBuilder = new StringBuilder("");
        propMap.keySet().stream().forEach(key -> stringBuilder.append(key).append(propMap.get(key)));
//        String sid = generateSign(stringBuilder.toString());
        try {
            String sid = SignUtils.signTopRequest(propMap, secret, "MD5");
            propMap.put("sid", sid);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String url = host + "/safenumberservicessm/api/manage/dataManage";
//            HttpClient httpClient = HttpClient.New(new URL(url));
            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
            multiValueMap.setAll(propMap);
            HttpEntity<MultiValueMap<String, String>> requestEntity  = new HttpEntity<MultiValueMap<String, String>>(multiValueMap, new HttpHeaders());
            String jsonpObject = restTemplate.postForObject(url, requestEntity, String.class);
            System.out.println(jsonpObject);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
