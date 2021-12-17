import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CasheScrapper implements Scrapper{
    @Override@SneakyThrows
    public Home parse(String url){
        Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        Statement statement = connection.createStatement();
        String query = String.format("select count(*) as count from homes where url='%s'", url);
        ResultSet rs = statement.executeQuery(query);
        int count = rs.getInt("count");
        if (count == 0) {
            DefaultScraper defaultScraper = new DefaultScraper();
            Home home = defaultScraper.parse(url);
            String insert = String.format("insert into homes(url, price, beds, baths, garages) values('%s', '%f', '%f', '%f', '%f')", url, home.getPrice(), home.getBeds(), home.getBathes(), home.getGarages());
            statement.executeUpdate(insert);
            return home;
        }else {
            String selector = String.format("select * from homes where url='%s'", url);
            ResultSet res = statement.executeQuery(selector);
            Home home = new Home(Double.parseDouble(res.getString("price").replaceAll(",", ".")), Double.parseDouble(res.getString("beds").replaceAll(",", ".")), Double.parseDouble(res.getString("baths").replaceAll(",", ".")), Double.parseDouble(rs.getString("garages").replaceAll(",", ".")));

            return home;
        }

    }
}

