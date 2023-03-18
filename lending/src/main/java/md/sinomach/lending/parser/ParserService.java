package md.sinomach.lending.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import md.sinomach.lending.productPage.Product;
import md.sinomach.lending.menuProduct.SubType;
import md.sinomach.lending.menuProduct.Type;
import md.sinomach.lending.productPage.ProductService;
import md.sinomach.lending.menuProduct.SubTypeService;
import md.sinomach.lending.menuProduct.TypeService;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ParserService {
    private final ProductService productService;
    private final TypeService typeService;
    private final SubTypeService subTypeService;


    private final String ORIGINAL_URL = "http://cn-changlin.ru/menu-items.txt";

    //For start parse uncomment this row and run application one time, after it comment this row again
    // @PostConstruct
    public void parseDataFromOriginalSite() throws IOException {
        Map<String, Map<String, List<String>>> result = new HashMap<>();

        Document document = Jsoup.connect(ORIGINAL_URL).get();


        Elements findElementsLeft = document.getElementsByClass("navleft");
        Elements findElementsRight = document.getElementsByClass("navright");
        Validate.isTrue(findElementsLeft.size() == 1);
        Validate.isTrue(findElementsRight.size() == 1);

        Element divNavLeft = findElementsLeft.get(0);
        Element divNavRight = findElementsRight.get(0);

        for (int typeIndex = 0; typeIndex < divNavLeft.children().size(); typeIndex++) {
            Element liNavLeft = divNavLeft.children().get(typeIndex);
            Element leftTagA = liNavLeft.child(0);
            String typeName = leftTagA.html();
            // System.out.println("Type: " + typeName);

            HashMap<String, List<String>> subTypeProducts = new HashMap<>();
            result.put(typeName, subTypeProducts);

            fillSubTypesAndProduct(divNavRight, typeIndex, subTypeProducts);


        }


        parseProducts(result);
    }

    private void parseProducts(Map<String, Map<String, List<String>>> result) {
        result.keySet().forEach(type -> {
            Type typeDao = new Type();
            typeDao.setName(type);
            typeDao = typeService.save(typeDao);

            Map<String, List<String>> subtypeProducts = result.get(type);
            Type finalTypeDao = typeDao;
            subtypeProducts.keySet().forEach(subType -> {
                SubType subTypeDao = new SubType();
                subTypeDao.setName(subType);
                subTypeDao.setType(finalTypeDao);
                subTypeDao = subTypeService.save(subTypeDao);

                List<String> products = subtypeProducts.get(subType);
                SubType finalSubTypeDao = subTypeDao;
                products.forEach(productUrl -> {
                    try {
                        Product product = parseProductPage(productUrl);
                        product.setSubType(finalSubTypeDao);
                        productService.save(product);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        });
    }

    private Product parseProductPage(String productUrl) throws IOException {
        Product product = new Product();

        Document productPage = Jsoup.connect("http://cn-changlin.ru/" + productUrl).get();

        //PRODUCT NAME
        Elements elementsByClass = productPage.getElementsByAttribute("itemprop");
        Element nameElement = elementsByClass.stream()
                .filter(element -> element.tagName() == "h2")
                .findFirst()
                .get();
        nameElement.child(0).remove();
        String productName = nameElement.text();
        product.setName(productName);

        //FULL DESCRIPTION
        Optional<Element> descriptionElement = nameElement.parent().children().stream()
                .filter(element -> element.tagName().equals("p"))
                .findFirst();
        String fullDescription = "";
        if (descriptionElement.isPresent()) {
            fullDescription = descriptionElement
                    .get()
                    .text();
        }
        product.setFullDescription(fullDescription);

        Element parent = nameElement.parent().parent().parent();
        parent.child(0).remove();
        String additionalFullDesc = parent.html();
        product.setAdditionalDescription(additionalFullDesc);


        //SHORT SPECS
        Elements shortSpecsElements = nameElement.parent().getElementsByClass("item2");
        Validate.isTrue(shortSpecsElements.size() == 1);
        Element shortSpecsElement = shortSpecsElements.get(0);
        Map<String, String> specs = new HashMap<>();
        shortSpecsElement.children().forEach(spec -> {
            String value = spec.child(0).text();
            spec.child(0).remove();
            String key = spec.text();
            specs.put(key, value);
        });
        ObjectMapper objectMapper = new ObjectMapper();
        product.setShortSpecification(objectMapper.writeValueAsString(specs));


        //CONTENT
        Element contentElement = productPage.getElementsByClass("nav-page").get(0);
        product.setContent(contentElement.html());
        //TODO download all images and manually change to local address


        return product;
    }

    private void fillSubTypesAndProduct(Element divNavRight, int i, HashMap<String, List<String>> subTypeProducts) {
        ArrayList<String> productsWithDefaultSubType = new ArrayList<>();
        subTypeProducts.put("default", productsWithDefaultSubType);

        Element ulRight = divNavRight.children().get(i);
        ulRight.children().forEach(element -> {
            switch (element.tagName()) {
                case "li":
                    //System.out.println("SUBTYPE: default PRODUCT:" + element.child(0).html());
                    productsWithDefaultSubType.add(element.child(0).attr("href"));
                    break;
                case "ol":
                    ArrayList<String> products = new ArrayList<>();

                    Element h3Tag = element.child(0);
                    subTypeProducts.put(h3Tag.child(0).html(), products);

                    for (int j = 1; j < element.children().size(); j++) {
                        // System.out.println("SUBTYPE: " + element.child(0).child(0).html() +
                        //         "PRODUCT " +  element.child(j).child(0).html());
                        products.add(element.child(j).child(0).attr("href"));
                    }
                    break;
            }
        });
    }
}
