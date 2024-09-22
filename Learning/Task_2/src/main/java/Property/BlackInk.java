package Property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlackInk implements Ink {
    @Value("${black.brand}")
    private String brand;

    @Value("${black.color}")
    private String color;

    @Override
    public String brand() {
        return this.brand;
    }

    @Override
    public String color() {
        return this.color;
    }
}
