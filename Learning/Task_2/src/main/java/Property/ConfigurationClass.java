package Property;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:SampleProperties")
@ComponentScan(basePackages = "Property") // Add this line to scan for components
public class ConfigurationClass {
    @Bean
    public Writer writer(Pen pen) {
        return new Writer(pen);
    }

    @Bean
    public Pen fountainPen(Ink ink) {
        return new FountainPen(ink);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
