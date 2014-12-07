package ua.ko3a4ok.ololo.tests;

import android.app.Activity;
import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;

import ua.ko3a4ok.ololo.MyActivity;
import ua.ko3a4ok.ololo.R;

public class PerformanceTest extends ActivityInstrumentationTestCase2<MyActivity> {


    public PerformanceTest() {
        super(MyActivity.class);
    }

    @SmallTest
    public void testOne() {
        Activity activity = getActivity();
        EditText edit = (EditText) activity.findViewById(R.id.url);
        assertNotNull(edit);
    }

    public static final String[] IMAGES = {
            "http://www.nasa.gov/sites/default/files/thumbnails/image/15697136810_8708bdacd6_o.jpg",
            "http://www.nasa.gov/sites/default/files/thumbnails/image/journey_to_mars.jpeg",
            "http://www.nasa.gov/sites/default/files/thumbnails/image/pia19048.jpg",
            "http://www.nasa.gov/sites/default/files/201411240003hq.jpg",
            "http://www.nasa.gov/sites/default/files/201411210014hq.jpg",
            "http://www.nasa.gov/sites/default/files/thumbnails/image/lmc_7k.jpg",
            "http://www.nasa.gov/sites/default/files/msh1162.jpg",
            "http://www.nasa.gov/sites/default/files/ed14-0338-081.jpg",
            "http://www.nasa.gov/sites/default/files/pia18290_full.jpg",
            "http://www.nasa.gov/sites/default/files/egmont_park_hires.jpg",
            "http://www.nasa.gov/sites/default/files/thumbnails/image/pia18876_welcome_to_a_comet.jpg",
            "http://www.nasa.gov/sites/default/files/ksc-314d-0494_0047.jpg",
            "http://www.nasa.gov/sites/default/files/201411100001hq.jpg",
            "http://www.nasa.gov/sites/default/files/b12ceraiiaebbvb.jpg_large.jpg",
            "http://www.nasa.gov/sites/default/files/20141105-flare-171-304_1.jpeg",
            "http://www.nasa.gov/sites/default/files/orion2014-4259_0.jpg",
            "http://www.nasa.gov/sites/default/files/southernappalachia_vir_2014305_lrg.jpg",
            "http://www.nasa.gov/sites/default/files/pia18432.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10787736_590581697708872_741895019_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10808544_707206266042762_1974869110_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10808952_396430630508707_1741871614_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10809000_1521759941414678_864842904_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10809642_1501804606767750_410157289_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10817536_1519344561662166_384073931_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10817569_812256385497518_272463219_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10817694_716372148460842_1452065001_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10817703_1512644759020839_1159350478_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10817712_704656156319606_1756189900_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10817740_318650638321076_1164162144_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10817886_382499851931373_1946256412_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10817965_378113255700510_485087488_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10817983_318125611727185_2011282888_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10818016_1519563628298101_779342105_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10818120_304671086392656_460168200_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10831810_746775515394219_1205641969_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10831872_856477251078773_1456218009_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10831914_564212967014265_2137325516_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10831995_531258150342783_179870429_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10831995_800412620016266_909704872_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10832142_882007348486092_1800394921_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10832179_834545059942756_1613120080_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10838518_1566562596893314_511248305_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10838632_819182758123036_1132927777_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10838661_1526387950965142_1250197507_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10838752_321623304706640_771099727_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10838839_578336462265881_794700064_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10838881_883761201643678_1126779622_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10838905_757253677690219_529795860_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10843663_310312105844298_2054344558_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10843755_1588790188009186_832492601_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10843932_1518710658391128_470774502_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10844038_703962629671926_1024651961_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10844043_1573639009537904_668184738_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10844127_995289830487214_237268242_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10848169_371457316348783_1582303733_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10848192_340387219486887_1473272368_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/10848216_765333776878669_1281467027_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xaf1/t51.2885-15/1599618_310469415815730_1649491349_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xap1/t51.2885-15/10514048_711697148937851_828066222_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xap1/t51.2885-15/10809561_1536773129912665_2013422588_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xap1/t51.2885-15/10809733_1545781682329217_1484512659_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xap1/t51.2885-15/10809748_700997000008424_966087319_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xap1/t51.2885-15/10809861_1544692189106878_1546837395_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xap1/t51.2885-15/10810008_759604217428653_1563871761_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xap1/t51.2885-15/924171_465087516963924_331388207_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xap1/t51.2885-15/925539_893101224057587_1318964456_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/10802398_1514658978815771_228116224_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/10817636_353839988122174_299610252_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/10817819_906490292703612_1923034065_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/10831736_1514163932179279_2144096290_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/10831784_873012992718810_415952175_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/10831985_1577243812506622_1148312260_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/10832108_309634589230439_427251639_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/10838429_1528841537375524_1903575439_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/10838807_889886447696504_838408854_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/10838843_972833506064748_643472350_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/10843679_809164309122153_1243846972_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/10843694_873649459346161_1963959041_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/10843742_1404506683173216_1020302967_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/10843811_316041668596224_614974743_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/10844180_1500130750263344_386091229_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/10848095_591210464356254_917905046_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/10848266_1531641993756376_462060551_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfa1/t51.2885-15/1889332_1548877615359010_1392506915_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfp1/t51.2885-15/10808917_1574275252784574_71639151_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfp1/t51.2885-15/10809019_300711360119186_886824214_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfp1/t51.2885-15/10809493_1497807620444027_1566991469_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xfp1/t51.2885-15/10810087_1578286575727918_1668475282_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpa1/t51.2885-15/10483614_355956751249587_1458315049_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpa1/t51.2885-15/10514119_618420518262814_456566143_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpa1/t51.2885-15/10666151_570193899791052_1944580752_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpa1/t51.2885-15/10748246_432246133590309_1862580104_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpa1/t51.2885-15/10785086_1522234091375886_1753400998_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpa1/t51.2885-15/10787756_1562128850685977_1124925182_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpa1/t51.2885-15/10808408_320461514809132_73907390_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpa1/t51.2885-15/10809494_326367307549172_499110696_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpa1/t51.2885-15/10809566_342663032572553_312272793_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpa1/t51.2885-15/10809716_816752915033224_867026316_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpa1/t51.2885-15/1941111_1595528600669827_602627926_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpa1/t51.2885-15/925630_735145313233252_148407825_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpa1/t51.2885-15/928738_309502325916579_757993506_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpa1/t51.2885-15/928741_346389038866373_1920128301_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpf1/t51.2885-15/10005515_1526239237618942_1746979343_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpf1/t51.2885-15/10784903_1554233631480434_694139511_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpf1/t51.2885-15/10788011_712023975555015_778994368_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpf1/t51.2885-15/10802599_744632238963842_1359685692_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpf1/t51.2885-15/10808426_1562387967307697_1529152009_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpf1/t51.2885-15/10808501_1516785235253904_271039814_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpf1/t51.2885-15/10808939_668532349929897_440290051_n.jpg",
            "http://scontent-a.cdninstagram.com/hphotos-xpf1/t51.2885-15/10809743_298156950395298_136254509_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10748172_415555401931180_1493423185_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10817563_830064190349084_334532533_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10817584_1503921949869076_600069076_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10817787_600023436792981_206643755_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10817853_325367680996721_220342032_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10817946_370538229737789_1079072130_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10818031_1523159201287130_155135984_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10831698_550962338371473_1399192330_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10831737_985693768123896_263231159_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10831863_1583013695264695_1248962391_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10831867_1510493559236120_200617258_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10831896_1532573620330919_1377868070_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10832107_769171199843745_1836345813_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10832230_567341873398735_775696627_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10832296_1590649991163676_1188119363_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10838568_882969338402862_432951511_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10838680_902372556454158_1562425080_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10838805_644939005615233_1642507603_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10838818_694240597350701_2012543164_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10843669_680440812077374_729992531_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10843747_790866317652315_334091780_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10843752_1495578857372933_438157439_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10843861_1538953576348668_1832865102_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10843872_762652340467429_826539532_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10843888_1521353224790737_250405756_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10843929_1541790306061368_1222799273_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10843963_782828115122564_1890385185_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10844031_412112648940923_1048805175_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10844031_684681468314186_101128061_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10844085_1503362933276651_230239640_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10844089_286623884794822_1801705719_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10844193_646833145427977_2040151860_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10844196_813162748750911_1877526752_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10844263_628144297293972_1732847164_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10844292_311553472385164_373684631_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10848109_478746078931114_2081359373_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10848130_752636784811410_879492323_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10848139_747587448628918_2041675127_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10848235_1561055187442569_1430414292_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10848267_308007932723201_542747607_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10848270_303601419830498_95978011_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10848312_412604672227097_1276053434_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xaf1/t51.2885-15/10848363_821644857896851_1631042199_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xap1/t51.2885-15/10483612_1539797172925919_1400533279_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xap1/t51.2885-15/10802583_383760345123978_606209348_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xap1/t51.2885-15/10802607_834757319909724_1276427058_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xap1/t51.2885-15/10808754_1520325821563714_1200043990_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xap1/t51.2885-15/10808973_634006883377244_760966146_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xap1/t51.2885-15/10809516_1036951556331574_20421477_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xap1/t51.2885-15/10809559_1404700379820463_1009878972_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xap1/t51.2885-15/10809648_1001708313176162_1409321148_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xap1/t51.2885-15/10809936_381945345307697_77809373_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xap1/t51.2885-15/10809940_751725431573919_2122407787_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xap1/t51.2885-15/10810045_1499700593626599_424912894_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xap1/t51.2885-15/928866_836121063115364_519488714_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10693540_1543434139233894_609414889_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10784930_670090119770287_1920050300_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10809928_1569000116667229_1211449430_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10817681_1514481288837870_1034720823_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10817795_385492028281602_970695543_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10817945_587335134732925_2003109962_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10831772_370361319798229_177571418_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10831952_725094624241433_1248655173_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10831970_1736263943264493_451915517_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10832225_746304662105514_2036920581_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10832258_817860318252056_1467364382_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10838351_939782259382752_692702674_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10838498_729560613787955_1336156608_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10838831_370672019768123_1577601578_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10843889_935648266468715_551766868_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10843895_311573562384515_377092393_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10844270_763515347053108_265505504_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10844303_1600693850158791_1169931737_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10848098_596781643758985_1464533541_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfa1/t51.2885-15/10848161_819983048059109_1686667633_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfp1/t51.2885-15/10693567_603765339727849_181391079_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfp1/t51.2885-15/10802533_322682617940246_1357308565_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfp1/t51.2885-15/10808803_293331527529604_603115102_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfp1/t51.2885-15/10808887_1519605928307909_171132447_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xfp1/t51.2885-15/1739731_311345699057995_252869414_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xpa1/t51.2885-15/10784960_331070103763414_1309630658_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xpa1/t51.2885-15/10808846_800834223307007_2382579_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xpa1/t51.2885-15/10809530_1583039358595932_2139827145_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xpa1/t51.2885-15/10809592_313186122208368_1842585426_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xpa1/t51.2885-15/10809596_1573317289558009_1703215524_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xpa1/t51.2885-15/1941111_1523133361289714_332782166_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xpa1/t51.2885-15/1941206_313023572235670_2017560226_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xpa1/t51.2885-15/1941291_735705856519390_550589330_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xpa1/t51.2885-15/928721_400674926763882_1477083471_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xpa1/t51.2885-15/928867_1510840209170698_1273889348_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xpa1/t51.2885-15/928970_761651523887940_1714968218_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xpa1/t51.2885-15/928973_344752012371959_108015620_n.jpg",
            "http://scontent-b.cdninstagram.com/hphotos-xpf1/t51.2885-15/10547099_1733291500228744_1772178048_n.jpg",
    };

    @LargeTest
    public void testLargeAmountOfLoading() {
        Activity activity = getActivity();
        final EditText edit = (EditText) activity.findViewById(R.id.url);
        final Button btn = (Button) activity.findViewById(R.id.btn);
        for (final String url: IMAGES) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    edit.setText(url);
                    btn.performClick();
                }
            });
            SystemClock.sleep(100);
        }

        SystemClock.sleep(200000);

    }

}