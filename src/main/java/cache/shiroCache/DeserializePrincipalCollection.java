package cache.shiroCache;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public class DeserializePrincipalCollection implements JsonDeserializer<PrincipalCollection> , JsonSerializer<PrincipalCollection>{
    @Override
    public PrincipalCollection deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        /**
         *    因为使用Gson序列化SimpleAuthenticationInfo可以，但是反序列就出现SimpleAuthenticationInfo对象中的protected PrincipalCollection principals 属性无法进行序列化，或者反序列化该属性内容已经丢失为null
         *      所以对于SimpleAuthenticationInfo类中PrincipalCollection之外的属性交给Gson 而我们只需要自定义PrincipalCollection的自定义TypeAdpater即可，待反序列化的时候让Gson在反序列化PrincipalCollection使用我们的TypeAdpater。
         *      内容如下：
         */

        /**
         * 注意：
         *  1：我们这里的TypeAdpater是单独对于PrincipalCollection属性接口的PrincipalCollection。
         *  2：PrincipalCollection接口的实现类为：SimplePrincipalCollection
         */

        //因为经测试得知PrincipalCollection接口的实现类为：SimplePrincipalCollection 所以通过反射进行创建对象SimplePrincipalCollection
        SimplePrincipalCollection simplePrincipalCollection = jsonDeserializationContext.deserialize(jsonElement, SimplePrincipalCollection.class);

        //获取SimplePrincipalCollection对象中realmPrincipals属性的Gson JsonObject对象
        JsonObject jsonObject = jsonElement.getAsJsonObject().getAsJsonObject("realmPrincipals");

        /**
         *  注意：
         *      SimplePrincipalCollection实现类中我们仅仅需要处理的是： private Map<String, Set> realmPrincipals，其他属性不重要所以不处理了
         *      因为realmPrincipals属性存在泛型，又因为Gson提供了泛型处理，所以我直接如下处理
         */
        LinkedHashMap<String, LinkedHashSet<String>> linkedHashSetLinkedHashMap = jsonDeserializationContext.deserialize(jsonObject, new TypeToken<LinkedHashMap<String, LinkedHashSet<String>>>() {
        }.getType());

        /**
         *  已经拿到了realmPrincipals属性的原本的内容，接下来进行赋值且返回
         */

        //下面开始获取
        Field field = null;
        try {
            field = simplePrincipalCollection.getClass().getDeclaredField("realmPrincipals");

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        field.setAccessible(true);
        try {
            field.set(simplePrincipalCollection, linkedHashSetLinkedHashMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        /**
         * 返回，接下来进行反序列化到PrincipalCollection属性，就会被调用到该TypeAdpater
         */
        return simplePrincipalCollection;
    }


    @Override
    public JsonElement serialize(PrincipalCollection principalCollection, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonElement = jsonSerializationContext.serialize(principalCollection, SimplePrincipalCollection.class);
        return jsonElement;
    }
}
