package util;


import exception.customException.SystemInvalidParameterException;
import exception.customException.UserInvalidParameterException;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 *
 * 涉及数据库参数字段
 */
public class DatabaseParameter {

    //状态表级别
    public static int FIEXD_RANK_FIXED=0;

    public static int FIEXD_RANK_1=1;

    public static int FIEXD_RANK_2=2;

    public static int FIEXD_RANK_3=3;

    public static int FIEXD_RANK_4=4;

    public static int FIEXD_RANK_5=5;

    public static int FIEXD_RANK_6=6;

    public static int FIEXD_RANK_7=7;

    public static int FIEXD_RANK_LONGST=8;

    //文章状态参数
    public static int ARTICLE_STATUS_NO_DETERMINED=0;

    public static int ARTICLE_STATUS_PELEASE=1;

    public static int ARTICLE_STATUS_PRIVATE=2;



    /**
     * 用于获取固定级别
     * @param i 需要设置的固定级别
     * @return 固定级别
     * @throws SystemInvalidParameterException 固定级别不存在
     */
    public static int queryLevel(int i,boolean user)throws SystemInvalidParameterException {
        switch (i){
            case 0:
               return FIEXD_RANK_FIXED;
            case 1:
                return FIEXD_RANK_1;
            case 2:
                return FIEXD_RANK_2;
            case 3:
                return FIEXD_RANK_3;
            case 4:
                return FIEXD_RANK_4;
            case 5:
                return FIEXD_RANK_5;
            case 6:
                return FIEXD_RANK_6;
            case 7:
                return FIEXD_RANK_7;
            case 8:
                return FIEXD_RANK_LONGST;
                default:
                    if (user!=true){
                        throw new UserInvalidParameterException("管理员输入的固定级别不存在");
                    }else{
                        throw new SystemInvalidParameterException("服务器设置固定级别错误");

                    }
        }
    }

}
