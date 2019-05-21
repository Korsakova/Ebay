package example.common.services.utils;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Service
public class generate {
    static final String A = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final String B = "0123456789";
    static final String C = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    static final String D = "0123456789abcdefghijklmnopqrstuvwxyz";

    public int getrandomint(int diap) {
        Random random = new Random();
        int k = random.nextInt(diap);
//        int k = random.nextInt(diap) - (diap / 2);
        return k;
    }

    public String getrandomdomain(int length) {
        Random r1 = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(D.charAt(r1.nextInt(D.length())));
        return sb.toString();
    }

    public String getrandomstring(int length) {
        Random r1 = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(A.charAt(r1.nextInt(A.length())));
        return sb.toString();
    }

    public String getrandomstringRUS(int length) {
        Random r1 = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(C.charAt(r1.nextInt(C.length())));
        return sb.toString();
    }

    public String getrandomstringofint(int length) {
        Random r1 = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(B.charAt(r1.nextInt(B.length())));
        return sb.toString();
    }

    public String getrandomdate() {
        Random r2 = new Random();
        int dataformat = 9; // 10.01.2000
        StringBuilder date = new StringBuilder(dataformat);
        for (int i = 0; i < dataformat; i++)
            if (i == 2 || i == 4) {
                date.append(".");
            } else {
                date.append(B.charAt(r2.nextInt(B.length())));
            }
        return date.toString();
    }

    public String getrandomtime() {
        int timeformat = 5;
        Random r3 = new Random();
        StringBuilder time = new StringBuilder(timeformat);
        for (int i = 0; i < timeformat; i++)
            if (i == 2) {
                time.append(":");
            } else {
                time.append(B.charAt(r3.nextInt(B.length())));
            }
        return time.toString();
    }

    public String getrandomphone() {
        Random r4 = new Random();
        int phoneformat = 11; // +7 123 456 78 90
        StringBuilder phone = new StringBuilder(phoneformat);
        for (int i = 0; i < phoneformat; i++)
            if (i == 0) {
                phone.append("+7");
            } else {
                phone.append(B.charAt(r4.nextInt(B.length())));
            }
        return phone.toString();
    }

    public String getrandomequantity() {
        Random r5 = new Random();
        int length = 2; // 99
        StringBuilder quantity = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            quantity.append(B.charAt(r5.nextInt(B.length())));
        }
        return quantity.toString();
    }

    public String getrandomediscount() {
        Random r6 = new Random();
        int length = 5; // 99.99
        StringBuilder discount = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            if (i == 2) {
                discount.append(",");
            } else {
                discount.append(B.charAt(r6.nextInt(B.length())));
            }
        return discount.toString();
    }

    public String getFutureDate(int days) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return format.format(cal.getTime());
    }

    public String getFutureTime(int hours, int minutes) {
        SimpleDateFormat format = new SimpleDateFormat("HH.mm");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hours);
        cal.add(Calendar.MINUTE, minutes);
        return format.format(cal.getTime());
    }

    public String getPersonName() {
        List<String> name = new ArrayList<>();
        name.add("Инокентий");
        name.add("Акакий");
        name.add("Борис Бритва");
        name.add("Кирпич");
        name.add("Тони пуля в зубах");
        name.add("Даг Голова");
        name.add("Евлампий");
        name.add("Фрэнки 4 пальца");
        return name.get(getrandomint(name.size()));
    }

    public String getPersonSurName() {
        List<String> surname = new ArrayList<>();
        surname.add("Иванов");
        surname.add("Петров");
        surname.add("Козлов");
        surname.add("Прибауткин");
        surname.add("Сивухин");
        surname.add("Серый");
        surname.add("Белый");
        surname.add("Неожиданный");
        return surname.get(getrandomint(surname.size()));
    }
}