package com.cloud.phonenumbergenerator;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/phone-number-generator")
public class ResourceClass {

    private static HashSet<String> reservedNumbers = new HashSet<>();
    Random random = new Random();

    @GetMapping("/getStatus")
    public String getStatus() {
        return "{\"status\":\"running\"}";
    }

    @GetMapping("/random-number-generate")
    @ApiOperation(value = "get the random number from the phone book",
            notes = "Make a Get request to get the random phone number. Service will return random unallocated phone number in response.",
            response = ResponseClass.class)
    public ResponseClass getRating() {
        return new ResponseClass(generateRandomNumber(), 200, true);
    }

    @GetMapping("/get-specific-number/{specificNumber}")
    @ApiOperation(value = "get the preferred number from the phone book",
            notes = "Type the specific number you want. You will get any random number that is unallocated if the specific number is not available.",
            response = ResponseClass.class)
    public ResponseClass getRating(@PathVariable("specificNumber") String specificNumber) {
        Pattern pattern = Pattern.compile("\\d{3}[- .]?\\d{3}[- .]?\\d{4}$");
        Matcher matcher = pattern.matcher(specificNumber);
        if (matcher.matches()) {
            if (reservedNumbers.size() == 222222222) {
                return new ResponseClass("No numbers available", 404, false);
            } else {
                String deliveredNumber = getSpecificNumber(specificNumber);
                if (deliveredNumber.equals(specificNumber)) {
                    return new ResponseClass(specificNumber, 200, false);
                } else {
                    return new ResponseClass(deliveredNumber, 200, true);
                }
            }
        } else {
            return new ResponseClass("The Number format you want is invalid", 403, false);
        }

    }

    private String getAvailableNumber() {
        while (true) {
            String number = generateRandomNumber();
            if (!reservedNumbers.contains(number)) {
                reservedNumbers.add(number);
                return number;
            }
        }
    }

    private String getSpecificNumber(String specificNumber) {
        if (reservedNumbers.contains(specificNumber)) {
            return getAvailableNumber();
        } else {
            reservedNumbers.add(specificNumber);
            return specificNumber;
        }
    }

    private String generateRandomNumber() {
        int i = random.nextInt(889) + 111;
        int j = random.nextInt(889) + 111;
        int k = random.nextInt(8889) + 1111;

        return new StringBuilder().append(i).append("-").append(j).append("-").append(k).toString();
    }


}

