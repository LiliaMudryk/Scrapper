import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DefaultScraper implements Scrapper{
    private static final String PRICE_SELECTOR = ".detail__info-xlrg";
    private static final String BED_SELECTOR = ".nhs_BedsInfo";
    private static final String BATH_SELECTOR = ".nhs_BathsInfo";
    private static final String GARAGE_SELECTOR = ".nhs_GarageInfo";
    @Override @SneakyThrows
    public Home parse(String url) {
        Document doc = Jsoup.connect(url).get();
        double price = getPrice(doc);
        double bedsNum = getBedsNumber(doc);
        double bathsNum = getBathsNumber(doc);
        double garagesNum = getGarageNumber(doc);
//        System.out.println(price);
        System.out.println(bedsNum);
        System.out.println(bathsNum);
        System.out.println(garagesNum);

        return new Home(price,bedsNum,bathsNum,garagesNum);
    }
    public static double getPrice(Document document){
        Element priceTag = document.selectFirst(PRICE_SELECTOR);
        String priceText = priceTag.text().replaceAll("[^0-9]", "");
        return Double.parseDouble(priceText);
    }
    public static double getBedsNumber(Document document){
        Element bedTag = document.selectFirst(BED_SELECTOR);
        String bedText = bedTag.text().replaceAll("[^0-9.,]", "");
        return Double.parseDouble(bedText);
    }
    public static double getBathsNumber(Document document){
        Element bathTag = document.selectFirst(BATH_SELECTOR);
        String bathText = bathTag.text().replaceAll("[^0-9.,]", "");
        return Double.parseDouble(bathText);
    }
    public static double getGarageNumber(Document document){
        Element garageTag = document.selectFirst(GARAGE_SELECTOR);
        String garageText = garageTag.text().replaceAll("[^0-9.,]", "");
        return Double.parseDouble(garageText);
    }
}
