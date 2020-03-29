package cn.gnw.cloud.provider.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisTemplate redisTemplate;

    public void setS(String key, String value) {
        setS(key, value, -1);
    }

    public void setS(String key, String value, long expireSeconds) {
        try {
            ValueOperations<String, String> valueOps =  redisTemplate.opsForValue();
            valueOps.set(key, value);
            if (expireSeconds > 0) redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + value + "]", t);
        }
    }


    public String getS(String key) {
        try {
            ValueOperations<String, String> valueOps =  redisTemplate.opsForValue();
            return valueOps.get(key);
        } catch (Throwable t) {
            logger.error("获取[" + key + "]失败", t);
        }
        return null;
    }

    public void setO(String key, Object value,long expireSeconds) {
        try {
            ValueOperations<String, Object> valueOps =  redisTemplate.opsForValue();
            valueOps.set(key, value);
            if (expireSeconds > 0) redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + value + "]", t);
        }
    }

    public Object getO(String key) {
        try {
            ValueOperations<String, Object> valueOps =  redisTemplate.opsForValue();
            return valueOps.get(key);
        } catch (Throwable t) {
            logger.error("获取[" + key + "]失败", t);
        }
        return null;
    }


    public void setL(String key, Long value,long expireSeconds) {
        try {
            ValueOperations<String, Long> valueOps =  redisTemplate.opsForValue();
            valueOps.set(key, value);
            redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + value + "]", t);
        }
    }

    public Long getL(String key) {
        try {
            ValueOperations<String, Long> valueOps =  redisTemplate.opsForValue();
            return valueOps.get(key);
        } catch (Throwable t) {
            logger.error("获取[" + key + "]失败", t);
        }
        return null;
    }


    public <T> void list(String key, T v) {
        try {
            ListOperations<String, T> listOps =  redisTemplate.opsForList();
            listOps.leftPush(key, v);
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
    }

    public <T> void listAll(String key, List<T> list) {
        listAll(key, list, -1);
    }

    public <T> void listAll(String key, List<T> list, long expireSeconds) {
        try {
            ListOperations<String, T> listOps =  redisTemplate.opsForList();
            listOps.rightPushAll(key, list);
            if (expireSeconds > 0) redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + list + "]", t);
        }
    }

    public <T> List<T> getList(String key) {
        try {
            ListOperations<String, T> listOps =  redisTemplate.opsForList();
            return listOps.range(key, 0, -1);
        } catch (Throwable t) {
            logger.error("获取缓存[" + key + "]失败", t);
        }
        return null;
    }

    public <T> void set(String key, T t) {
        try {
            SetOperations<String, T> setOps =  redisTemplate.opsForSet();
            setOps.add(key, t);
        } catch (Throwable throwable) {
            logger.error("缓存[" + key + "]失败, value[" + t + "]", throwable);
        }
    }

    public <T> void setAll(String key, Set<T> set) {
        setAll(key, set, -1);
    }

    public <T> void setAll(String key, Set<T> set, long expireSeconds) {
        try {
            SetOperations<String, T> setOps =  redisTemplate.opsForSet();
            T[] result = (T[]) set.toArray();
            setOps.add(key, result);
            if (expireSeconds > 0) redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + set + "]", t);
        }
    }

    public <T> Set<T> getSet(String key) {
        try {
            SetOperations<String, T> setOps = redisTemplate.opsForSet();
            return setOps.members(key);
        } catch (Throwable t) {
            logger.error("获取set缓存失败key[" + key + ", error[" + t + "]");
        }
        return null;
    }

    public <T> void zset(String key, Set<T> set) {
        zset(key, set, -1);
    }

    public <T> void zset(String key, Set<T> set, long expireSeconds) {
        try {
            ZSetOperations<String, T> zsetOps =  redisTemplate.opsForZSet();
            zsetOps.add(key, (Set<ZSetOperations.TypedTuple<T>>) set);
            if (expireSeconds > 0) redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
        } catch (Throwable throwable) {
            logger.error("缓存[" + key + "]失败, value[" + set + "]", throwable);
        }
    }

    public <T> Set<T> getZSet(String key) {
        try {
            ZSetOperations<String, T> zsetOps =  redisTemplate.opsForZSet();
            return zsetOps.range(key, 0, -1);
        } catch (Throwable throwable) {
            logger.error("缓存[" + key + "]失败", throwable);
        }
        return null;
    }

    public <T> void hmap(String key, Map<Object, T> rsmap) {
        hmap(key, rsmap,-1);
    }

    public <T> void hmap(String key, Map<Object, T> rsmap, long expireSeconds) {
        try {
            HashOperations hashOperations = redisTemplate.opsForHash();
            hashOperations.putAll(key, rsmap);
            if (expireSeconds > 0) redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
        } catch (Throwable throwable) {
            logger.error("缓存[" + key + "]失败", throwable);
        }
    }
    public <T> Map<Object,T> hmapGet(String key) {
        try {
            HashOperations hashOperations = redisTemplate.opsForHash();
            return hashOperations.entries(key);
        } catch (Exception e) {
            logger.error("获取set缓存失败key[" + key + ", error[" + e + "]");
        }
        return null;
    }


    public <T> void hmset(String key, Map<String, Set<T>> rsmap) {
        hmset(key, rsmap, -1);
    }

    public <T> void hmset(String key, Map<String, Set<T>> rsmap, long expireSeconds) {
        try {
            HashOperations<String, String, Set<T>> hashOperations = redisTemplate.opsForHash();
            hashOperations.putAll(key, rsmap);
            if (expireSeconds > 0) redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
        } catch (Throwable throwable) {
            logger.error("缓存[" + key + "]失败", throwable);
        }
    }


    public <T> Map<String, T> hmget(String key) {
        try {
            HashOperations<String, T, T> hashOperations = redisTemplate.opsForHash();
            hashOperations.entries(key);
        } catch (Throwable throwable) {
            logger.error("缓存[" + key + "]失败", throwable);
        }
        return null;
    }

    public <T> Map<String, Set<T>> hmsetGet(String key) {
        try {
            HashOperations<String, String, Set<T>> hashOperations = redisTemplate.opsForHash();
            hashOperations.entries(key);
        } catch (Throwable throwable) {
            logger.error("缓存[" + key + "]失败", throwable);
        }
        return null;
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }

    public void delPrefix(String key) {
        Set<String> keys = redisTemplate.keys(key + "*");
        redisTemplate.delete(keys);
    }

    /**
     *  加锁
     * @param key
     * @param value
     * @param timeout 超时时间-毫秒
     * @return
     */
    public boolean lock(String key, String value, long timeout) {
        //setIfAbsent相当于jedis中的setnx，如果能赋值就返回true，如果已经有值了，就返回false
        //即：在判断这个key是不是第一次进入这个方法
        try {
            if (redisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.SECONDS)) {
                return true;
            }
            String currentValue = this.getS(key);
            // 判断是不是超时
            if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
                // 获取上一个锁的时间
                String oldValue = (String) redisTemplate.opsForValue().getAndSet(key, value);
                if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                    // 考虑多线程并发的情况，只有一个线程的设置值和当前值相同，它才有权利加锁
                    return true;
                }
            }
        } catch (Exception e) {
            logger.info("[redis-加锁失败]，异常msg={}", e.getMessage());
            throw new RuntimeException("枷锁失败");
        }
        // 其他情况，一律返回加锁失败
        return false;
    }

    public void unlock(String key, String value) {
        try {
            if (!StringUtils.isEmpty(this.getS(key)) && this.getS(key).equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
                logger.info("[redis-解锁成功]， key={}, value={}", key, value);
            }
        } catch (Exception e) {
            logger.info("[redis-解锁失败]，异常msg={}", e);
       }
    }

    public void testArticle() {
        //文章时间 有序集合
        redisTemplate.opsForValue().getOperations().opsForZSet().add("zsort1", "wz:01", 87.11);
        redisTemplate.opsForValue().getOperations().opsForZSet().add("zsort1", "wz:02", 80.51);
        redisTemplate.opsForValue().getOperations().opsForZSet().add("zsort1", "wz:03", 89.54);

        // 文章评分 有序集合
        redisTemplate.opsForValue().getOperations().opsForZSet().add("zsort2", "wz:01", 2);
        redisTemplate.opsForValue().getOperations().opsForZSet().add("zsort2", "wz:02", 3);
        redisTemplate.opsForValue().getOperations().opsForZSet().add("zsort2", "wz:03", 2);


        // 文章用户ID 集合
        Long add = redisTemplate.opsForValue().getOperations().opsForSet().add("wz:01", "userId1");
        System.out.println("23432" + add);
        redisTemplate.opsForValue().getOperations().opsForSet().add("wz:01", "userId2");
        redisTemplate.opsForValue().getOperations().opsForSet().add("wz:01", "userId3");

        // 文章投票数量  散列
        redisTemplate.opsForValue().getOperations().opsForHash().put("hash1", "wz:01", 0L);
        redisTemplate.opsForValue().getOperations().opsForHash().put("hash1", "wz:02", 0L);
        redisTemplate.opsForValue().getOperations().opsForHash().put("hash1", "wz:03", 0L);
        redisTemplate.opsForValue().getOperations().opsForHash().put("hash1", "wz:04", 0L);

        Set set = redisTemplate.opsForValue().getOperations().opsForZSet().range("zsort1", 0, -1);
        Double da = redisTemplate.opsForValue().getOperations().opsForZSet().score("zsort1", "wz:01");
        if (da != null && da.compareTo(60d) > 0) {
            Long add2 =  redisTemplate.opsForValue().getOperations().opsForSet().add("wz:01", "userId4");
            if (add2 == 1) {
                //加评分
                redisTemplate.opsForValue().getOperations().opsForZSet().incrementScore("zsort2", "wz:01", 4);
                // 加投票数量
                redisTemplate.opsForValue().getOperations().opsForHash().increment("hash1", "wz:01", 1);
            }
        }
        System.out.println(JSON.toJSONString(set));
        System.out.println(da);
    }

    public void testWeb() {
        redisTemplate.opsForValue().getOperations().opsForHash().put("token-user", "token5", "user5");
        boolean flag = redisTemplate.opsForValue().getOperations().opsForHash().hasKey("token-user", "token5");
        Object value = redisTemplate.opsForValue().getOperations().opsForHash().get("token-user", "token5");
        Long size = redisTemplate.opsForValue().getOperations().opsForHash().size("token-user");
        System.out.println("size=" +size);
        System.out.println("flag=" +flag);
        System.out.println("value=" + value);

        Thread thread = new Thread(() -> {
            int i = 0;
            boolean flag1 = true;
            while (flag1) {

                if (redisTemplate.opsForValue().getOperations().opsForHash().hasKey("token-user", "token" + i)) {
                    System.out.println("33333");
                    try {
                        Thread.sleep(1000);//守护线程阻塞1秒后运行
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    flag1 = false;
                }
                System.out.println("aaaaa=" + i);
                i++;
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
