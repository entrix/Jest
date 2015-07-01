package com.volkoval.jest.net;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 07.11.14
 * Time: 14:52
 */
public class PhoneNumberSearcherTest {

//    @Test
    public void spOrgSearch() throws IOException {
        Elements links = getGoogleSearchResults("site:spr-org.ru 88003333233");
        String userAgent = "ExampleBot 1.0 (+http://example.com/bot)"; // Change this to your company's name and bot homepage!
        if (links.size() > 0) {
            String url = links.get(0).absUrl("href");
            url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
            Elements elements = Jsoup.connect(url).userAgent(userAgent).get().select("body");
            Element element = elements.get(0);
            String header = element.select("td.main-column>h1").html();
            String contacts = element.select("td.main-column p").html();
            Pattern pattern = Pattern.compile("<.*?>");
            contacts = pattern.matcher(contacts).replaceAll("");
            String place = element.select("table.data-table-empty>tbody>tr:eq(0) span.name-el").html();
            String placeVal = element.select("table.data-table-empty>tbody>tr:eq(0) td:eq(1)").html();
            String phone = element.select("table.data-table-empty>tbody>tr:eq(1) span.name-el").html();
            String phoneVal = element.select("table.data-table-empty>tbody>tr:eq(1) td:eq(1)").html();
            String activity = element.select("table.data-table-empty>tbody>tr:eq(2) span.name-el").html();
            String activityVal = element.select("table.data-table-empty>tbody>tr:eq(2) td:eq(1)").html();
            String site = element.select("table.data-table-empty>tbody>tr:eq(3) span.name-el").html();
            String siteVal = element.select("table.data-table-empty>tbody>tr:eq(3) td:eq(1) a").html();

            System.out.println(header);
            System.out.println(StringEscapeUtils.unescapeHtml4(contacts));
            System.out.println(place + " " + placeVal);
            System.out.println(phone + " " + phoneVal);
            System.out.println(activity + " " + activityVal);
            System.out.println(site + " " + siteVal);
        }
    }

//    @Test
    public void spravinfoSearch() throws IOException {
        Elements links = getGoogleSearchResults("site:spravinfo.ru 88002000900");
        String userAgent = "ExampleBot 1.0 (+http://example.com/bot)"; // Change this to your company's name and bot homepage!
        if (links.size() > 0) {
            String url = links.get(0).absUrl("href");
            url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
            Elements elements = Jsoup.connect(url).userAgent(userAgent).get().select("body");
            Element element = elements.get(0);
            for (int i = 0; i < 100; ++i) {
                if (!element.select("td#main>table.firma:eq(" + i + ") tr:eq(1)>td:eq(0) div:eq(0)").html().isEmpty()) {
                    String scope = element.select("td#main>table.firma:eq(" + i + ") tr:eq(1)>td:eq(0) div:eq(0)").html();
                    String header = element.select("td#main>table.firma:eq(" + i + ") td.firma_name").html();
                    Pattern pattern = Pattern.compile("<.*?>");
                    scope = pattern.matcher(scope).replaceAll("");
                    String phoneVal = element.select("td#main>table.firma:eq(" + i + ") tr:eq(1)>td:eq(0) div:eq(1)").html();
                    phoneVal = pattern.matcher(phoneVal).replaceAll("");
                    String additionalPhoneVal = element.select("td#main>table.firma:eq(" + i + ") tr:eq(1)>td:eq(0) div:eq(2)").html();
                    additionalPhoneVal = pattern.matcher(additionalPhoneVal).replaceAll("");
                    String placeVal = element.select("td#main>table.firma:eq(" + i + ") tr:eq(1)>td:eq(0) div:eq(3)").html();
                    placeVal = pattern.matcher(placeVal).replaceAll("");
                    String addressVal = element.select("td#main>table.firma:eq(" + i + ") tr:eq(1)>td:eq(0) div:eq(4)").html();
                    addressVal = pattern.matcher(addressVal).replaceAll("");
                    String siteVal = element.select("td#main>table.firma:eq(" + i + ") tr:eq(1)>td:eq(0) div:eq(5)").html();
                    siteVal = pattern.matcher(siteVal).replaceAll("");

                    System.out.println(header);
                    System.out.println(scope);
                    System.out.println(phoneVal);
                    System.out.println(additionalPhoneVal);
                    System.out.println(placeVal);
                    System.out.println(addressVal);
                    System.out.println(siteVal);
                    System.out.println("\n\n");
                }
            }
        }
    }

//    @Test
    public void szfophonesSearch() throws IOException {
        Elements links = getGoogleSearchResults("site:szfophones.ru 88002000900");
        String userAgent = "ExampleBot 1.0 (+http://example.com/bot)"; // Change this to your company's name and bot homepage!
        if (links.size() > 0) {
            Pattern pattern = Pattern.compile("<.*?>");
            Pattern newLinePattern = Pattern.compile("<br\\ */>");
            String url = links.get(0).absUrl("href");
            url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
            Elements elements = Jsoup.connect(url).userAgent(userAgent).get().select("body");
            Element element = elements.get(0).select("table#main_table>tbody>tr:eq(6) td>div:eq(1)").get(0);
            String header = element.select("h2").html();
            header = StringEscapeUtils.unescapeHtml4(pattern.matcher(header).replaceAll(""));
            System.out.println(header);
            for (int i = 1; i < 100; ++i) {
                if (!element.select("p:eq(" + i + ")").html().isEmpty()) {
                    String val = newLinePattern.matcher(element.select("p:eq(" + i + ")").html()).replaceAll("\n");
                    val = pattern.matcher(val).replaceAll("");
                    val = StringEscapeUtils.unescapeHtml4(val);
                    System.out.println(val);
                }
            }
        }
    }

    @Test
    public void ufophonesSearch() throws IOException {
        Elements links = getGoogleSearchResults("site:ufophones.ru 88002000900");
        String userAgent = "ExampleBot 1.0 (+http://example.com/bot)"; // Change this to your company's name and bot homepage!
        if (links.size() > 0) {
            Pattern pattern = Pattern.compile("<.*?>");
            Pattern newLinePattern = Pattern.compile("<br\\ */>");
            String url = links.get(0).absUrl("href");
            url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
            Elements elements = Jsoup.connect(url).userAgent(userAgent).get().select("body");
            Element element = elements.get(0).select("table#main_table>tbody>tr:eq(6) td>div:eq(1)").get(0);
            String header = element.select("h2").html();
            header = StringEscapeUtils.unescapeHtml4(pattern.matcher(header).replaceAll(""));
            System.out.println(header);
            for (int i = 1; i < 100; ++i) {
                if (!element.select("p:eq(" + i + ")").html().isEmpty()) {
                    String val = newLinePattern.matcher(element.select("p:eq(" + i + ")").html()).replaceAll("\n");
                    val = pattern.matcher(val).replaceAll("");
                    val = StringEscapeUtils.unescapeHtml4(val);
                    System.out.println(val);
                }
            }
        }
    }

//    @Test
    public void search() throws IOException {
        List<String> titleList = new ArrayList<>();
        Elements links = getGoogleSearchResults("88003333233");
        Pattern pattern = Pattern.compile("333\\D+32\\D+33");

        for (Element link : links) {
            String title = link.text();

            if (title.contains("3333233") || pattern.matcher(title).find()) {
                continue;
            }

            String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
            url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");

            if (!url.startsWith("http")) {
                continue; // Ads/news/etc.
            }
            titleList.add(title);

//            System.out.println("Title: " + title);
//            System.out.println("URL: " + url);
        }

        List<String> verifiedResults = new ArrayList<>();
        for (String intersection : getCommonPrefix(titleList)) {
            Elements subLinks = getGoogleSearchResults(intersection);
            for (Element link : subLinks) {
                String baseUrl = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
                String url = URLDecoder.decode(baseUrl.substring(baseUrl.indexOf('=') + 1, baseUrl.indexOf('&')), "UTF-8");
                String title;
                if ((title = isNumberExists("8003333233", "333\\D+32\\D+33", url)) != null) {
                    verifiedResults.add(intersection);
                }
            }
        }
        for (String verifiedResult : new HashSet<String>(verifiedResults)) {
            System.out.println(verifiedResult);
        }
    }

    private Elements getGoogleSearchResults(String search) throws IOException {
        String google = "http://www.google.com/search?q=";
        String charset = "UTF-8";
        String userAgent = "ExampleBot 1.0 (+http://example.com/bot)"; // Change this to your company's name and bot homepage!

        return Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select("li.g>h3>a");
    }

    private String isNumberExists(String number, String regex, String url) {
        String charset = "UTF-8";
        String userAgent = "ExampleBot 1.0 (+http://example.com/bot)";
        boolean isChunk = false;

        try {
            Elements links = Jsoup.connect(url).header("Content-Type","application/x-www-form-urlencoded")
                    .ignoreContentType(true).ignoreHttpErrors(true).userAgent(userAgent).get().select("body");
            String title = links.get(0).text();
            String body = links.get(0).html();
            if (Pattern.compile(regex).matcher(body).find()) {
                isChunk = true;
            }
            if (body.contains(number) || body.contains(number.substring(3)) || isChunk) {
                return title;
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return null;
    }

    private List<String> getCommonPrefix(List<String> titleList) {
        List<String> resultList = new ArrayList<>();
        String result = null;

        for (int i = 0; i < titleList.size(); ++i) {
            for (int j = i + 1; j < titleList.size(); ++j) {
                int maxCompared = 0;
                String titleOne = titleList.get(i);
                String titleTwo = titleList.get(j);
                result = lcs(titleOne, titleTwo);
                if (result != null && result.trim().length() > 3) {
                    resultList.add(result);
                }
            }
        }
        Set<String> resultSet = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.trim().equalsIgnoreCase(o2.trim())) {
                    return 0;
                }
                return o1.compareTo(o2);
            }
        });
        resultSet.addAll(resultList);
        return new ArrayList<String>(resultSet);
    }

    public static String lcs(String a, String b) {
        List<String> strings = new ArrayList<>();
        int[][] lengths = new int[a.length()+1][b.length()+1];

        // row 0 and column 0 are initialized to 0 already

        for (int i = 0; i < a.length(); i++)
            for (int j = 0; j < b.length(); j++)
                if (a.charAt(i) == b.charAt(j))
                    lengths[i+1][j+1] = lengths[i][j] + 1;
                else
                    lengths[i+1][j+1] =
                            Math.max(lengths[i+1][j], lengths[i][j+1]);

        // read the substring out from the matrix
        StringBuffer sb = new StringBuffer();
        for (int x = a.length(), y = b.length();
             x != 0 && y != 0; ) {
            if (lengths[x][y] == lengths[x-1][y]) {
                x--;
                strings.add(sb.reverse().toString());
                sb = new StringBuffer();
            }
            else if (lengths[x][y] == lengths[x][y-1]) {
                y--;
                strings.add(sb.reverse().toString());
                sb = new StringBuffer();
            }
            else {
                assert a.charAt(x-1) == b.charAt(y-1);
                sb.append(a.charAt(x-1));
                x--;
                y--;
            }
        }
        strings.add(sb.reverse().toString());
        String longestString = "";
        for (String str : strings) {
            if (str.length() > longestString.length()) {
                longestString = str;
            }
        }
        return longestString;
    }
}
