package com.ur.tb.listed.util;


import java.awt.Color;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.TimeZone;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.lang.Nullable;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.mail.internet.InternetAddress;
@Slf4j
public class CommonUtil {

    public static String getPrintStackTrace(Exception e) {
        StringWriter error = new StringWriter();
        e.printStackTrace(new PrintWriter(error));
        return error.toString();
    }

    public static JSONObject objectToJSONObject(Object object){
        Object json = null;
        JSONObject jsonObject = null;
        try {
            json = new JSONTokener(object.toString()).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (json instanceof JSONObject) {
            jsonObject = (JSONObject) json;
        }
        return jsonObject;
    }

    public static JSONArray objectToJSONArray(Object object){
        Object json = null;
        JSONArray jsonArray = null;
        try {
            json = new JSONTokener(object.toString()).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (json instanceof JSONArray) {
            jsonArray = (JSONArray) json;
        }
        return jsonArray;
    }


    public static Map<String, Object> convertToMap(Object obj) throws IllegalAccessException, InstantiationException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        if (obj == null) {
            return Collections.emptyMap();
        }

        Map<String, Object> convertMap = new HashMap<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            convertMap.put(field.getName(), field.get(obj));
        }
        return convertMap;
    }

    public static <T> T convertToValueObject(Map<String, Object> map, Class<T> type)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (type == null) {
            throw new NullPointerException("Class cannot be null");
        }

        T instance = type.getConstructor().newInstance();

        if (map == null || map.isEmpty()) {
            return instance;
        }

        for (Map.Entry<String, Object> entrySet : map.entrySet()) {
            Field[] fields = type.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

                String fieldName = field.getName();

                boolean isSameType = entrySet.getValue().getClass().equals(field.getType());
                boolean isSameName = entrySet.getKey().equals(fieldName);

                if (isSameType && isSameName) {
                    field.set(instance, map.get(fieldName));
                }
            }
        }
        return instance;
    }

    public static List<Map<String, Object>> convertToMaps(List<?> list)
            throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }

        List<Map<String, Object>> convertList = new ArrayList<>();

        for (Object obj : list) {
            convertList.add(CommonUtil.convertToMap(obj));
        }
        return convertList;
    }

    public static <T> List<T> convertToValueObjects(List<Map<String, Object>> list, Class<T> type)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }

        List<T> convertList = new ArrayList<>();

        for (Map<String, Object> map : list) {
            convertList.add(CommonUtil.convertToValueObject(map, type));
        }
        return convertList;
    }

    public static String getMktDivCd(String ltgmktdivcd) {
        if(ltgmktdivcd.equalsIgnoreCase("11")) {
            return "KOSPI";
        } else if(ltgmktdivcd.equalsIgnoreCase("12")) {
            return "KOSDAQ";
        } else if(ltgmktdivcd.equalsIgnoreCase("14")) {
            return "KONEX";
        } else if(ltgmktdivcd.equalsIgnoreCase("15")) {
            return "ETC";
        } else {
            return "ETC";
        }
    }

    public static String getRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color color = new Color(r,g,b);
        return "#"+Integer.toHexString(color.getRGB()).substring(2);
    }

    public static boolean isEmpty(@Nullable Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof Optional) {
            return !((Optional)obj).isPresent();
        } else if (obj instanceof CharSequence) {
            return ((CharSequence)obj).length() == 0;
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        } else if (obj instanceof Collection) {
            return ((Collection)obj).isEmpty();
        } else {
            return obj instanceof Map ? ((Map)obj).isEmpty() : false;
        }
    }

    public static String getUriStr(String url, Object obj)  throws  Exception{
        Map<String, Object> params = convertToMap(obj);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for(Map.Entry<String,Object> entry : params.entrySet()) {
            if(!CommonUtil.isEmpty(entry.getValue())) {
                builder.queryParam(entry.getKey(),entry.getValue());
            }
        }
        return URLDecoder.decode(builder.build().encode().toUri().toString(),"UTF-8");
    }

    public static String getRtvStr(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String rtv = mapper.writeValueAsString(obj);
        return rtv.replaceAll("\\\\","");
    }

    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String dateResult = sdf.format(date);
        return dateResult;
    }

    public static String getMonthAgoDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        Date date = calendar.getTime();
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String dateResult = sdf.format(date);
        return dateResult;
    }
    
    public static String getSectorColor(String val) {
    	String rtv = "";
    	switch (val) {
		case "1":
			rtv = "#1b4872";
			break;
		case "2":
			rtv = "#20499b";
			break;
		case "3":
			rtv = "#3b85c2";
			break;
		case "4":
			rtv = "#4ac4c9";
			break;
		case "5":
			rtv = "#99c0db";
			break;
		case "6":
			rtv = "#f1592a";
			break;
		case "7":
			rtv = "#9aca3a";
			break;
		case "8":
			rtv = "#75cde1";
			break;
		case "9":
			rtv = "#3ecad1";
			break;
		case "10":
			rtv = "#b71755";
			break;
		case "11":
			rtv = "#fbd54e";
			break;
		case "12":
			rtv = "#0095a7";
			break;
		case "13":
			rtv = "#53285e";
			break;
		case "14":
			rtv = "#9b59a1";
			break;
		case "15":
			rtv = "#8d643e";
			break;
		case "16":
			rtv = "#634b47";
			break;
		case "17":
			rtv = "#d14244";
			break;
		case "18":
			rtv = "#e47178";
			break;
		case "19":
			rtv = "#017949";
			break;
		case "20":
			rtv = "#f4c5bf";
			break;
		case "21":
			rtv = "#ec902a";
			break;
		case "22":
			rtv = "#20499b";
			break;
		default:
			rtv = "#979fa9";
			break;
		}
    	return rtv;
    }

    public static InternetAddress[] mailListToArray(List<String> addressList) throws  Exception{

        InternetAddress[] address = new InternetAddress[addressList.size()];
        for(int i =0; i < addressList.size(); i++) {
            address[i] = new InternetAddress(addressList.get(i));
        }
        return address;
    }


}
