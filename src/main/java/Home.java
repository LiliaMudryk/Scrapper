import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class Home {
    private double price;
    private double beds;
    private double bathes;
    private double garages;

    public double getBathes() {
        return bathes;
    }
    public double getPrice(){
        return price;
    }
    public double getBeds(){
        return beds;
    }
    public double getGarages(){
        return garages;
    }
}
