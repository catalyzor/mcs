package com.gm.util;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.gm.dao.SafePhoneNumberRepository;
import com.gm.model.SafePhoneNumber;
import com.sun.crypto.provider.HmacMD5;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
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

        StringBuilder stringBuilder = new StringBuilder("");
        propMap.keySet().stream().forEach(key -> stringBuilder.append(key).append(propMap.get(key)));
        String sid = generateSign(stringBuilder.toString());
        propMap.put("sid", sid);

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

    public String generateSign(String data){

        String algorithm="HmacMD5";
        Mac mac = null;
        try {
            mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(secret.getBytes("UTF8"), algorithm));
            return HexUtils.toHexString(mac.doFinal(data.getBytes("UTF8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    synchronized public SafePhoneNumber getUseableSafePhoneNumber(String phone){
        SafePhoneNumber safePhoneNumber = safePhoneNumberRepository.findFirstByOrderByBindTimeAsc();
        safePhoneNumber.setBindPhone(phone);
        safePhoneNumber.setBindTime(Calendar.getInstance().getTime());
        safePhoneNumberRepository.save(safePhoneNumber);
        return safePhoneNumber;
    }
}
