package datageneration;

import java.util.*;

/**
 * 随机生成中文姓名，性别，Email，手机号，住址
 *
 * @author
 */
public class GrxxUtil {
    public static String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    //姓
    private static String firstName = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福百家姓续";
    //女孩名
    private static String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
    //男孩名
    private static String boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
    //寓意好的字
    private static String hz = "乐飞福皇嘉达佰美元致春帅亮名欧特辰康讯鹏腾澜津启博扬索蓝昂兴聚鸿略卡姬安众汇圣卓宇国普绿斯业媛意盛雄琛钧冠策毓楠榕伊铭齐风航弘义昭良纨彬富颜麒韬鸣朋斌行时泰娜磊民琴芳芯友志清坚庆若德彪宏芝萍霄伟刚勇毅俊峰强军平丽苑芸保东文辉力明玲健世彩朗郎旺融誉际巨骄为诚妙顺领迅英虹芬馨尼迈群拓建秋倩宸江雷天仪优聪垒蕾瀚玫琪淑骁永吉先君依昌哲营惠羽希舒曙廷渲梦瑜菏凤叶芃霭凝立玉静同颖宜林奇政冰影红尚川州帝悦情洁滋祥艳珊薰滟禾竹多帆秀贝仑青枫琰波笑宗雨涵纪亭甜禹垚园娟琳金新中加亚信华豪奥珠翠雅凯和鑫创宝星联晨百蔓莓曼尔海瑞科锦易威玛日柔淞渺伦道发唯一才月丹涟欢薇泽诗香鼎碧麦邦克凡利思滢萱盈泓品庭展朔轩育晓眉通骏振聆翌迎常浩益杰枝途丰壹智婷越岚超驰云淼蓓慧微菡资湘会菁萌语芊赫寒茗珂爽阳进臻燕霖霏爱灿蓉景馥筠露鹤荔厅湾菲可巧飘漪琬瑗";
    //private static String[] road="重庆大厦,黑龙江路,十梅庵街,遵义路,湘潭街,瑞金广场,仙山街,仙山东路,仙山西大厦,白沙河路,赵红广场,机场路,民航街,长城南路,流亭立交桥,虹桥广场,长城大厦,礼阳路,风岗街,中川路,白塔广场,兴阳路,文阳街,绣城路,河城大厦,锦城广场,崇阳街,华城路,康城街,正阳路,和阳广场,中城路,江城大厦,顺城路,安城街,山城广场,春城街,国城路,泰城街,德阳路,明阳大厦,春阳路,艳阳街,秋阳路,硕阳街,青威高速,瑞阳街,丰海路,双元大厦,惜福镇街道,夏庄街道,古庙工业园,中山街,太平路,广西街,潍县广场,博山大厦,湖南路,济宁街,芝罘路,易州广场,荷泽四路,荷泽二街,荷泽一路,荷泽三大厦,观海二广场,广西支街,观海一路,济宁支街,莒县路,平度广场,明水路,蒙阴大厦,青岛路,湖北街,江宁广场,郯城街,天津路,保定街,安徽路,河北大厦,黄岛路,北京街,莘县路,济南街,宁阳广场,日照街,德县路,新泰大厦,荷泽路,山西广场,沂水路,肥城街,兰山路,四方街,平原广场,泗水大厦,浙江路,曲阜街,寿康路,河南广场,泰安路,大沽街,红山峡支路,西陵峡一大厦,台西纬一广场,台西纬四街,台西纬二路,西陵峡二街,西陵峡三路,台西纬三广场,台西纬五路,明月峡大厦,青铜峡路,台西二街,观音峡广场,瞿塘峡街,团岛二路,团岛一街,台西三路,台西一大厦,郓城南路,团岛三街,刘家峡路,西藏二街,西藏一广场,台西四街,三门峡路,城武支大厦,红山峡路,郓城北广场,龙羊峡路,西陵峡街,台西五路,团岛四街,石村广场,巫峡大厦,四川路,寿张街,嘉祥路,南村广场,范县路,西康街,云南路,巨野大厦,西江广场,鱼台街,单县路,定陶街,滕县路,钜野广场,观城路,汶上大厦,朝城路,滋阳街,邹县广场,濮县街,磁山路,汶水街,西藏路,城武大厦,团岛路,南阳街,广州路,东平街,枣庄广场,贵州街,费县路,南海大厦,登州路,文登广场,信号山支路,延安一街,信号山路,兴安支街,福山支广场,红岛支大厦,莱芜二路,吴县一街,金口三路,金口一广场,伏龙山路,鱼山支街,观象二路,吴县二大厦,莱芜一广场,金口二街,海阳路,龙口街,恒山路,鱼山广场,掖县路,福山大厦,红岛路,常州街,大学广场,龙华街,齐河路,莱阳街,黄县路,张店大厦,祚山路,苏州街,华山路,伏龙街,江苏广场,龙江街,王村路,琴屿大厦,齐东路,京山广场,龙山路,牟平街,延安三路,延吉街,南京广场,东海东大厦,银川西路,海口街,山东路,绍兴广场,芝泉路,东海中街,宁夏路,香港西大厦,隆德广场,扬州街,郧阳路,太平角一街,宁国二支路,太平角二广场,天台东一路,太平角三大厦,漳州路一路,漳州街二街,宁国一支广场,太平角六街,太平角四路,天台东二街,太平角五路,宁国三大厦,澳门三路,江西支街,澳门二路,宁国四街,大尧一广场,咸阳支街,洪泽湖路,吴兴二大厦,澄海三路,天台一广场,新湛二路,三明北街,新湛支路,湛山五街,泰州三广场,湛山四大厦,闽江三路,澳门四街,南海支路,吴兴三广场,三明南路,湛山二街,二轻新村镇,江南大厦,吴兴一广场,珠海二街,嘉峪关路,高邮湖街,湛山三路,澳门六广场,泰州二路,东海一大厦,天台二路,微山湖街,洞庭湖广场,珠海支街,福州南路,澄海二街,泰州四路,香港中大厦,澳门五路,新湛三街,澳门一路,正阳关街,宁武关广场,闽江四街,新湛一路,宁国一大厦,王家麦岛,澳门七广场,泰州一路,泰州六街,大尧二路,青大一街,闽江二广场,闽江一大厦,屏东支路,湛山一街,东海西路,徐家麦岛函谷关广场,大尧三路,晓望支街,秀湛二路,逍遥三大厦,澳门九广场,泰州五街,澄海一路,澳门八街,福州北路,珠海一广场,宁国二路,临淮关大厦,燕儿岛路,紫荆关街,武胜关广场,逍遥一街,秀湛四路,居庸关街,山海关路,鄱阳湖大厦,新湛路,漳州街,仙游路,花莲街,乐清广场,巢湖街,台南路,吴兴大厦,新田路,福清广场,澄海路,莆田街,海游路,镇江街,石岛广场,宜兴大厦,三明路,仰口街,沛县路,漳浦广场,大麦岛,台湾街,天台路,金湖大厦,高雄广场,海江街,岳阳路,善化街,荣成路,澳门广场,武昌路,闽江大厦,台北路,龙岩街,咸阳广场,宁德街,龙泉路,丽水街,海川路,彰化大厦,金田路,泰州街,太湖路,江西街,泰兴广场,青大街,金门路,南通大厦,旌德路,汇泉广场,宁国路,泉州街,如东路,奉化街,鹊山广场,莲岛大厦,华严路,嘉义街,古田路,南平广场,秀湛路,长汀街,湛山路,徐州大厦,丰县广场,汕头街,新竹路,黄海街,安庆路,基隆广场,韶关路,云霄大厦,新安路,仙居街,屏东广场,晓望街,海门路,珠海街,上杭路,永嘉大厦,漳平路,盐城街,新浦路,新昌街,高田广场,市场三街,金乡东路,市场二大厦,上海支路,李村支广场,惠民南路,市场纬街,长安南路,陵县支街,冠县支广场,小港一大厦,市场一路,小港二街,清平路,广东广场,新疆路,博平街,港通路,小港沿,福建广场,高唐街,茌平路,港青街,高密路,阳谷广场,平阴路,夏津大厦,邱县路,渤海街,恩县广场,旅顺街,堂邑路,李村街,即墨路,港华大厦,港环路,馆陶街,普集路,朝阳街,甘肃广场,港夏街,港联路,陵县大厦,上海路,宝山广场,武定路,长清街,长安路,惠民街,武城广场,聊城大厦,海泊路,沧口街,宁波路,胶州广场,莱州路,招远街,冠县路,六码头,金乡广场,禹城街,临清路,东阿街,吴淞路,大港沿,辽宁路,棣纬二大厦,大港纬一路,贮水山支街,无棣纬一广场,大港纬三街,大港纬五路,大港纬四街,大港纬二路,无棣二大厦,吉林支路,大港四街,普集支路,无棣三街,黄台支广场,大港三街,无棣一路,贮水山大厦,泰山支路,大港一广场,无棣四路,大连支街,大港二路,锦州支街,德平广场,高苑大厦,长山路,乐陵街,临邑路,嫩江广场,合江路,大连街,博兴路,蒲台大厦,黄台广场,城阳街,临淄路,安邱街,临朐路,青城广场,商河路,热河大厦,济阳路,承德街,淄川广场,辽北街,阳信路,益都街,松江路,流亭大厦,吉林路,恒台街,包头路,无棣街,铁山广场,锦州街,桓台路,兴安大厦,邹平路,胶东广场,章丘路,丹东街,华阳路,青海街,泰山广场,周村大厦,四平路,台东西七街,台东东二路,台东东七广场,台东西二路,东五街,云门二路,芙蓉山村,延安二广场,云门一街,台东四路,台东一街,台东二路,杭州支广场,内蒙古路,台东七大厦,台东六路,广饶支街,台东八广场,台东三街,四平支路,郭口东街,青海支路,沈阳支大厦,菜市二路,菜市一街,北仲三路,瑞云街,滨县广场,庆祥街,万寿路,大成大厦,芙蓉路,历城广场,大名路,昌平街,平定路,长兴街,浦口广场,诸城大厦,和兴路,德盛街,宁海路,威海广场,东山路,清和街,姜沟路,雒口大厦,松山广场,长春街,昆明路,顺兴街,利津路,阳明广场,人和路,郭口大厦,营口路,昌邑街,孟庄广场,丰盛街,埕口路,丹阳街,汉口路,洮南大厦,桑梓路,沾化街,山口路,沈阳街,南口广场,振兴街,通化路,福寺大厦,峄县路,寿光广场,曹县路,昌乐街,道口路,南九水街,台湛广场,东光大厦,驼峰路,太平山,标山路,云溪广场,太清路".split(",");
    //路
    private static String[] road = "厦禾路,思明东路,思明西路,思明南路,思明北路,中山路,湖滨东路,湖滨西路,湖滨南路,公园东路,公园西路,公园南路,公园北路,鹭江道,莲前东路,莲前西路,前埔东路,前埔西路,前埔南路,前埔北路,环岛南路,环岛北路,环岛凤屿路,莲花南路,莲花北路,东浦路,市府大道,白鹭洲路,建新路,西堤路,后埭西路,同安路,大学路,小学路,仙岳路,镇海路,新华路,鼓新路,鼓声路,兆和路,龙头路,泉州路,福州路,三明路,禾祥东路,禾祥西路,金榜路,漳州路,港后路,溪岸路,将军祠路,万寿路,角尾路,体育路,屿后路,岩路,晃岩路,环岛高速,嘉禾路,吕岭路,台湾街,斗西路,幸福路,成功大道,东渡路,开禾路,金尚路,云顶南路,云顶北路,会展南路,东坪山路,文园路,文屏路,文曾路,演武路,民族路,西村路,电台山路,气象台路,滨榔路,升平路,湖光路,湖明路,莲岳路,谊爱路".split(",");
    //酒店旅馆名字
    private static String[] lg = "缥缈、羽裳、轩辕、紫萱、韶华、浮光、烟雨、蝶舞、缠绵、绝恋、碧影、星愿、落霞、忘忧、幻真、翩飞、惊鸿、星月、情动、化羽、绝影、醉梦、波澜、山岚、春华、星雨、浩瀚、风萧、浮波、逐风、沧澜、鸿鹄、如梦、入画、青衣、流影、舒荷、霓裳、清曲、醉月、风和、瑞雪、沐宇、舞纱、夜渺、无微、晨阳、佳容、宛碧、纹香、梵音、静晓、润玉、嬛绵、明秀、归云、春媱、夏露、秋颜、冬耀、缱绻、涟漪、若溪、微凉、暖阳、半夏、崖悔、洛尘、矜柔、绚烂、真淳、明媚、迷离、隐忍、灼热、幻灭、落拓、锦瑟、邪殇、离殇、恋夏".split("、");
    //脸型
    private static String[] tz = "圆脸,椭圆脸,菱形脸,长方脸,方脸,倒大脸,三角脸,狭长脸,畸形脸".split(",");
    //派出所
    private static String[] pcs = "碧山派出所,开元派出所,滨海派出所,鼓浪屿派出所,鹭江派出所,中华派出所,滨北派出所,梧村派出所,莲前派出所,曾厝垵边防派出所,何厝边防派出所,筼筜派出所".split(",");
    //行政区编码
    private static String[] xzqbm = "350203001000,350203003000,350203005000,350203006000,350203007000,350203008000,350203009000,350203010000,350203011000,350203012000".split(",");
    //特长
    private static String[] zc = "驾车,驾船,驾机,射击,爆破,解刨,屠宰,印刷,表演,绘画,摄影,钳工,缝纫,潜水,攀高,医药,武术".split(",");//特长
    //性别代码
    private static String[] xbdm = "1,2".split(",");//性别代码
    //手机号开头
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    //邮箱
    private static final String[] email_suffix = "@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");
    //房屋结构
    private static String[] zm = "木质,石头,混合,钢架".split(",");
    //随机数
    private static RandomNum randnum = new RandomNum();
    private static TimeUtil time = new TimeUtil();

    /**
     * 返回Email
     *
     * @param lMin 最小长度
     * @param lMax 最大长度
     * @return
     */
    public String getEmail(int lMin, int lMax) {
        int length = randnum.getNum(lMin, lMax);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * base.length());
            sb.append(base.charAt(number));
        }
        sb.append(email_suffix[(int) (Math.random() * email_suffix.length)]);
        return sb.toString();
    }

    /**
     * 返回手机号码
     */

    public String getTel() {
        int index = randnum.getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(randnum.getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(randnum.getNum(1, 9100) + 10000).substring(1);
        return first + second + third;
    }

    /**
     * 生成三字中文名字
     */
    public String getName() {
        Random random = new Random();
        int index = random.nextInt(firstName.length() - 1);
        String name = firstName.substring(index, index + 1);
        ; //获得一个随机的姓氏
        int i = random.nextInt(3);//可以根据这个数设置产生的男女比例
        if (i == 2) {
            int j = random.nextInt(girl.length() - 2);
            name = "2," + name + girl.substring(j, j + 2);
            /* 两字和三字名
            if (j % 2 == 0) {
                //name = "女-" + name + girl.substring(j, j + 2);
                name =  name + girl.substring(j, j + 2);
            } else {
                //name = "女-" + name + girl.substring(j, j + 1);
                name = name + girl.substring(j, j + 1);
            }
            */
        } else {
            int j = random.nextInt(girl.length() - 2);
            //三字名
            name = "1," + name + boy.substring(j, j + 2);
            /* 两字和三字
            if (j % 2 == 0) {
                //name = "男-" + name + boy.substring(j, j + 2);
                name = name + boy.substring(j, j + 2);
            } else {
                //name = "男-" + name + boy.substring(j, j + 1);
                name = name + boy.substring(j, j + 1);
            }
             */
        }
        return name;
    }

    /**
     * 返回单位
     */
    public String getDw() {
        Random random = new Random();
        int len = hz.length();
        int i1 = random.nextInt(len - 1);
        int i2 = random.nextInt(len - 1);
        int i3 = random.nextInt(len - 1);
        String a = hz.substring(i1, i1 + 1);
        String b = hz.substring(i2, i2 + 1);
        String c = hz.substring(i3, i3 + 1);
        return a + b + c + "股份有限公司";
    }

    /**
     * @param N 随机数范围
     * @return 代码
     */
    public String getDm(int N) {
        String mz = "";
        Random random = new Random();
        int dm = random.nextInt(N) + 1;
        if (dm < 10) {
            mz = "0" + dm;
        } else {
            mz = String.valueOf(dm);
        }
        return mz;
    }

    /**
     * 返回生日和身份证号
     */
    public String getSfzxx(String beginnum, String begindate, String enddate) {
        //String bm = "3502";//开头数字
        int N = 9;//随机数范围
        Random random = new Random();
        String bmTmp = beginnum;
        for (int j = 0; j < 6 - beginnum.length(); j++) {//身份证开头6位
            bmTmp = bmTmp + random.nextInt(N);
        }
        Date date = time.randomDate(begindate, enddate);
        String sr = time.formaTime(date, "yyyyMMdd");
        String sr1 = time.formaTime(date, "yyyy-MM-dd");
        bmTmp = bmTmp + sr;
        for (int j = 0; j < 4; j++) {//身份证后4位
            bmTmp = bmTmp + random.nextInt(N);
        }
        String sfz = sr1 + "," + bmTmp;
        return sfz;
    }

    /**
     * 返回地址
     *
     * @return
     */
    public String getRoad() {
        int index = randnum.getNum(0, road.length - 1);
        String qu = "思明区";
        String ss = "福建省厦门市";
        String first = road[index];
        String second = String.valueOf(randnum.getNum(11, 150)) + "号";
        //String third="-"+getNum(1,20)+"-"+getNum(1,10);
        String third = randnum.getNum(1, 9) + "" + randnum.getNum(10, 20) + "室";
        //String third="";
        String jb = getZm();
        return ss + qu + first + second + third;
        //return first + second + third + "', '" + ss + qu + first + second + third;
        //return first + second + third + tmp + qu + first + second + third;
    }

    /**
     * 返回派出所
     */
    public String getPcs() {
        int index = randnum.getNum(0, pcs.length - 1);
        String qu = "厦门市公安局";
        String first = pcs[index];
        return qu + first;
    }

    /**
     * 返回行政区编码
     */
    public String getXzqbm() {
        int index = randnum.getNum(0, xzqbm.length - 1);
        String first = xzqbm[index];
        return first;
    }

    /**
     * 返回房屋结构
     */
    private static String getZm() {
        int index = randnum.getNum(0, zm.length - 1);
        String first = zm[index];
        return first;
    }

    /**
     * 长相特征
     *
     * @return
     */
    private static String getTz() {
        int index = randnum.getNum(0, tz.length - 1);
        String first = tz[index];
        return first;
    }

    /**
     * 特长
     *
     * @return
     */
    private static String getZc() {
        int index = randnum.getNum(0, zc.length - 1);
        String first = zc[index];
        return first;
    }

    /**
     * 行政区代码
     *
     * @return
     */
    public String getXbdm() {
        int index = randnum.getNum(0, xbdm.length - 1);
        String first = xbdm[index];
        return first;
    }
}