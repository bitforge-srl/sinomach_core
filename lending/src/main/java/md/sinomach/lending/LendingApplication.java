package md.sinomach.lending;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class LendingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LendingApplication.class, args);

	}
}
