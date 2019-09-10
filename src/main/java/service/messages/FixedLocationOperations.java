package service.messages;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public interface FixedLocationOperations {
    /**
     * 添加新固定地点
     * @param fixedName 固定地点，非中文
     */
    void uploadFixedLocation(String fixedName);

}
