package datageneration;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 随机生成姓名、性别，民族，出生日期，身份证号，手机号，邮箱，身高，文化程度，地址，单位，时间，编码等
 */
public class CreateData {
    public static void main(String[] args) {
        //初始化
        int N = 1000; //生成数目
        Random random = new Random();
        GrxxUtil grxx = new GrxxUtil();
        TimeUtil time = new TimeUtil();
        BmUtil bm = new BmUtil();
        RandomNum randomnum = new RandomNum();
        String fg = "', '";
        int bm_len = "201398".length();
        for (int i = 0; i < N; i++) {
            String[] xmTmp = grxx.getName().split(",");
            String xm = xmTmp[1];//姓名
            String xb = xmTmp[0];//性别 1、2
            String mz = grxx.getDm(56); //民族 01-56
            String[] sfzTmp = grxx.getSfzxx("3502", "1966-1-1", "1990-12-31").split(",");
            String sr = sfzTmp[0]; //出生日期
            String sfzh = sfzTmp[1]; //身份证号
            String hm = grxx.getTel(); //手机号
            String ys = grxx.getEmail(6, 18); //邮箱
            String sg = String.valueOf(randomnum.getNum(150, 195)); //身高 150-195
            String wh = grxx.getDm(90); //文化程度 01-90
            String pcs = grxx.getPcs(); //派出所
            String xzqbm = grxx.getXzqbm(); //行政区编码
            String hjbm = "350203"; //户籍所在地
            String zy = grxx.getDm(99); //职业代码 01-99
            String dz = grxx.getRoad(); //地址
            String dw = grxx.getDw(); //单位
            String uuid = UUID.randomUUID().toString().replace("-", "");
            Date date = time.randomDate("2018-1-1", "2018-12-31"); //指定范围日期
            Date date_after = time.getDayAfter(date, 2); //指定日期后n天
            String sj = time.formaTime(date, "yyyy-MM-dd"); // 指定格式时间
            //String sjc = String.valueOf(time.timeToStam(sj)); //时间戳
            String bh = bm.getBm("", bm_len, 9); //编号开头，编号长度，编号数字取值范围

//            System.out.println(sfzh);
            System.out.println(xm);
            //System.out.println(sj + "T" + sj1 + ".000Z");
            //System.out.println("'" + (random.nextInt(2) + 1) + "', ''"); //性别
        }
    }
}