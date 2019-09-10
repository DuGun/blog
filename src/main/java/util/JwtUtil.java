package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import org.apache.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具
 * 2019-3-31
 */

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public class JwtUtil {
    /**
     * 创建一个32-byte的密匙
     * 密钥 苟且这样 后续更新
     */
    private static final byte[] secret = "geiwodiangasfdjsikolkjikolkijswe".getBytes();



    private static Logger logger = Logger.getLogger(JwtUtil.class);

    private final static Gson gson = new Gson();

    public static String creatToken(String Identification) throws JOSEException {

        if (Identification == null || Identification.trim().equals("")) {
            throw new NullPointerException(ResponseBean.MSG_PARAMETER_ERROR_MESSAGE);
        }
        //用于装载载体
        Map<String, Object> map = new HashMap<>();

        //建立载荷，这些数据根据业务，自己定义。
        map.put("Identification", Identification);
        //创建唯一标识
        map.put("Mark", PasswordUtil.getUUID());
        //生成时间
        map.put("TheStartTime", new Date().getTime());
        //过期时间
        map.put("ExpirationTime", new Date().getTime() * 1000);


        //建立一个头部Header
        // 设置算法法则,JWSAlgorithm类里面有所有的加密算法法则，直接调用。
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        //建立一个载荷Payload.
        Payload payload = new Payload(gson.toJson(map));

        //将头部和载荷结合在一起
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        //建立一个密匙
        JWSSigner jwsSigner = null;
        try {
            jwsSigner = new MACSigner(secret);
            //签名
            jwsObject.sign(jwsSigner);
        } catch (KeyLengthException e) {
            logger.error("-----------服务器制作签名异常-----------");
            throw new KeyLengthException(e.getMessage());
        } catch (JOSEException e) {
            logger.error("-----------服务器制作签名标识异常-----------");
            throw new JOSEException(e.getMessage());
        } catch (NullPointerException e) {
            logger.error("-----------秘钥为空异常-----------");
            throw new NullPointerException(e.getMessage());
        }

        //生成token
        return jwsObject.serialize();

    }


    /**
     * 用于校验令牌真否
     *
     * @param token 令牌
     * @return 令牌真假
     */
    public static String valid(String token) {
        JWSObject jwsObject = null;
        boolean tokenTrueOrFlase = false;
        JWSVerifier jwsVerifier = null;
        Payload payload = null;

        //解析令牌出错
        try {
            jwsObject = JWSObject.parse(token);
        } catch (ParseException e) {
            return "TokenError";
        }


        try {
            //获取到载荷
            payload = jwsObject.getPayload();
            //用户密钥生成校验器异常
            jwsVerifier = new MACVerifier(secret);
            //匹配客户端和服务端两者密钥是否一致
            tokenTrueOrFlase = jwsObject.verify(jwsVerifier);
        } catch (JOSEException e) {
            return "TokenError";
        }

        if (tokenTrueOrFlase) {
            //载荷的数据解析成Map对象 分析数据
            Map<String, Object> jsonObject = payload.toJSONObject();
            System.out.println("用户Identification是" + jsonObject.get("Identification"));
            if (jsonObject.containsKey("ExpirationTime")) {
                Long expTime = Long.valueOf(jsonObject.get("ExpirationTime").toString());
                Long nowTime = new Date().getTime();
                if (nowTime > expTime) {
                    return "TokenError";
                }
            }
            logger.info("-----------密钥匹配成功-----------");
            return "TokenCorrect";
        } else {
            logger.info("-----------密钥匹配失败-----------");
            return "TokenError";
        }
    }


    /**
     * @param request  请求
     * @param response 响应
     * @param code     状态码
     * @param content  Msg信息
     * @param data     Data数据内容
     * @throws IOException
     */
    public static void responseError(ServletRequest request, ServletResponse response, int code, String content, String data) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(code);
        responseBean.setMsg(content);
        responseBean.setData(data == null ? null : data);
        httpServletResponse.setStatus(code);

        PrintWriter writer = httpServletResponse.getWriter();

        writer.write(gson.toJson(responseBean));
        writer.flush();
        writer.close();

    }


    /**
     * 通过token 得到载体指定内容
     *
     * @param token   令牌
     * @param content 需要获取的载体Key
     * @return
     */
    public static String tokenCentos(String token, String content) {

        final Base64.Decoder decoder = Base64.getDecoder();

        String[] ss = token.split("\\.");
        String Json = null;
        try {
            Json = new String(decoder.decode(ss[1]), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();

        Map<String, Object> objectMap = gson.fromJson(Json, type);
        Object o = objectMap.get(content);
        return o.toString();
    }


}