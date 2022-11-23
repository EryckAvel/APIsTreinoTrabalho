package br.com.erudio.apigateway.controller;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();
    @RequestMapping(value = "/soma/{numberOne}/{numberTwo}",
                    method = RequestMethod.GET)
    public double soma(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedOperationException("Por favor insira um valor numerico!");
        }
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/multiplicacao/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public double multiplicacao(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedOperationException("Por favor insira um valor numerico!");
        }
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/divisao/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public double divisao(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedOperationException("Por favor insira um valor numerico!");
        }
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/subtracao/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public double subtracao(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedOperationException("Por favor insira um valor numerico!");
        }
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/media/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public double media(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedOperationException("Por favor insira um valor numerico!");
        }
        return (convertToDouble(numberOne) + convertToDouble(numberTwo))/2;
    }

    @RequestMapping(value = "/raiz/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public double raiz(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedOperationException("Por favor insira um valor numerico!");
        }
        return Math.sqrt(convertToDouble(numberOne)) + Math.sqrt(convertToDouble(numberTwo));
    }

    private Double convertToDouble(String strNumber) {
        if (strNumber == null) return 0D;
        //10,25 -> 10.25
        String number = strNumber.replaceAll(",",".");
        if(isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null)return false;
        String number = strNumber.replaceAll(",",".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
