package dat3.database.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    @GetMapping
    public String getInfo() {
        return """
                 {
                  "reference": "https://www.themealdb.com/",
                  "created": "2023-12-18T15:22:55.724Z",
                  "info": "Data are created via the free recipe API from the mealDB."
                }""";
    }
}

