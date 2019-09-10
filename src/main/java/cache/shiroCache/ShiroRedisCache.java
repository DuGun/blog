package cache.shiroCache;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import util.JwtUtil;

import java.util.Collection;
import java.util.Set;


/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */

/**
 * Shiro使用Redis缓存
 * 该接口负责实际的缓存逻辑
 *
 * @param <K>
 * @param <V>
 */
public class ShiroRedisCache<K, V, KryoRedisSerializer> implements Cache<K, V> {

    private static Logger logger = LoggerFactory.getLogger(ShiroRedisCache.class);

    /**
     * 认证前缀
     */
    private static final String SHIRO_AUTHENTICATION = "shiro_Authentication_";

    /**
     * 授权前缀
     */
    private static final String SHIRO_AUTHORIZATION = "shiro_Authorization_";

    /**
     * 缓存名字
     */
    private String name;

    /**
     * jedis 连接工厂
     */
    private JedisConnectionFactory jedisConnectionFactory;
    private final Gson gson= new GsonBuilder()
            .registerTypeAdapter(PrincipalCollection.class,new DeserializePrincipalCollection())
            .create();


    /**
     * 序列化工具
     */
    private StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

    private RedisTemplate redisTemplate;

    public ShiroRedisCache() {
        super();
    }

    public ShiroRedisCache(String name, JedisConnectionFactory jedisConnectionFactory) {
        this.name = name;
        this.jedisConnectionFactory = jedisConnectionFactory;
    }

    public ShiroRedisCache(String name, JedisConnectionFactory jedisConnectionFactory, RedisTemplate redisTemplate) {
        this.name = name;
        this.jedisConnectionFactory = jedisConnectionFactory;
        this.redisTemplate = redisTemplate;


    }


    @Override
    public V get(K k) throws CacheException {
        JwtUtil.tokenCentos((String) k, "Identification");
        Object o=null;
        o = redisTemplate.execute(new RedisCallback<SimpleAuthenticationInfo>() {
            @Override
            public SimpleAuthenticationInfo doInRedis(RedisConnection connection) throws DataAccessException {
                //解析对象
                //解析token
                String Identification = JwtUtil.tokenCentos((String) k, "Identification");
                //查询数据
                byte[] bytes= connection.get((SHIRO_AUTHORIZATION+Identification).getBytes());
                String jsonString = stringRedisSerializer.deserialize(bytes);
                SimpleAuthenticationInfo simpleAuthenticationInfo = gson.fromJson(jsonString, SimpleAuthenticationInfo.class);
                return simpleAuthenticationInfo;
            }
        });


        return o==null?null:(V) o;

    }


    /*
    k:token 字符串
    v:SimpleAuthenticationInfo对象
    * */
    @Override
    public V put(K k, V v) throws CacheException {

        System.out.println("Shiro缓存存储");
        Object o=null;
        if(v instanceof  SimpleAuthenticationInfo){
            o = redisTemplate.execute(new RedisCallback<SimpleAuthenticationInfo>() {
                @Override
                public SimpleAuthenticationInfo doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    //解析对象
                    String Identification = JwtUtil.tokenCentos((String) k, "Identification");
                    SimpleAuthenticationInfo simpleAuthenticationInfo = (SimpleAuthenticationInfo) v;

                    //插入数据
                    redisConnection.set((SHIRO_AUTHORIZATION+Identification).getBytes(),gson.toJson(simpleAuthenticationInfo).getBytes());
                    return simpleAuthenticationInfo;
                }
            });
        }

        return (V) o;
    }



    @Override
    public V remove(K k) throws CacheException {

        return null;

    }

    @Override
    public void clear() throws CacheException {
        logger.debug("----------------ShiroRedisCache_clear----------------");

        RedisConnection redisConnection = jedisConnectionFactory.getConnection();

        //删除全部认证
        Long l1 = redisConnection.hDel(SHIRO_AUTHORIZATION.getBytes());

        //删除全部授权
        Long l2 = redisConnection.hDel(SHIRO_AUTHENTICATION.getBytes());


    }

    @Override
    public int size() {
        logger.debug("----------------ShiroRedisCache_size----------------");

        RedisConnection redisConnection = jedisConnectionFactory.getConnection();

        //暂且不知道怎么做

        return 0;
    }

    @Override
    public Set<K> keys() {
        logger.debug("----------------ShiroRedisCache_keys----------------");

        RedisConnection redisConnection = jedisConnectionFactory.getConnection();

        //暂且不知道怎么做


        return null;
    }

    @Override
    public Collection<V> values() {
        logger.debug("----------------ShiroRedisCache_values----------------");

        return null;
    }

}
