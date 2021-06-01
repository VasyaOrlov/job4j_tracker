package ru.job4j.inheritance;

public class ReportUsage {
    public static void main(String[] args) {
        JSONReport report = new JSONReport();
        System.out.println(report.generate("Report's name", "Report's body"));
        System.out.println();
        TextReport report1 = new TextReport();
        System.out.println(report1.generate("Report's name", "Report's body"));
        System.out.println();
        HtmlReport report2 = new HtmlReport();
        System.out.println(report2.generate("Report's name", "Report's body"));
    }
}