package com.example.GDP.Service;

import com.example.GDP.Entity.Gdp;
import com.example.GDP.Entity.GdpGrowthRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
@Service
public class GdpServiceImpl implements GdpService {

//    @Autowired
//    private GdpGrowthRateRepository gdpGrowthRateRepository;
//
//    @Autowired
//    private GdpRepository gdpRepository;

    Double year1Gdp;
    Double year2Gdp;

    Gdp gdp ;

    String line ;
    String csvSplitBy = ",";
    BufferedReader bufferedReader;
    String fileName1 = "gdp.csv";
    String fileName2 = "C:\\Users\\Redline PC\\Music\\GDP\\GDP\\src\\main\\java\\com\\example\\GDP\\Service\\All country GDP Dataset.xlsx";


    @Override
    public GdpGrowthRate getGdpGrowthRate(String countryCode, int year1, int year2) throws IOException {
        List<Gdp> gdpList = readFile2(fileName2);

        for(Gdp gdp : gdpList){
            if(gdp.getCountryCode().equals(countryCode)){
                if(gdp.getYear()==year1){
                    year1Gdp = gdp.getValue();
                }
                if(gdp.getYear()==year2){
                    year2Gdp = gdp.getValue();
                }
            }
        }
        GdpGrowthRate gdpGrowthRate = new GdpGrowthRate(countryCode,year1Gdp,year2Gdp);


        return gdpGrowthRate;
    }

    //method to read CSV file
    public ArrayList<Gdp> readFile(String filename) throws IOException {

        ArrayList<Gdp> gdpList = new ArrayList<>();

        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println("File name incorrect");

        }



        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(csvSplitBy, -1);

            gdp = new Gdp();

            if (!data[1].equalsIgnoreCase("Country Code")) {
                gdp.setCountryCode((data[1]));


            }
            if (!data[2].equalsIgnoreCase("Year")) {
                if(data[2].equals("BHS")){
                    System.out.println("stop");
                }
                gdp.setYear(Integer.valueOf((data[2])));

            }
            if (!data[2].equalsIgnoreCase("Value")) {
                gdp.setValue(Double.valueOf((data[3])));

            }
            gdpList.add(gdp);
        }

        return gdpList;
    }

    public ArrayList<Gdp> readFile2(String filename) throws IOException {
        ArrayList<Gdp> gdpList = new ArrayList<>();

        File file = new File(filename);   //creating a new file instance
        FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
//creating Workbook instance that refers to .xlsx file
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
        Iterator<Row> itr = sheet.iterator();    //iterating over excel file
        while (itr.hasNext())
        {
            Row row = itr.next();
            Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
            while (cellIterator.hasNext())
            {
                Cell cell = cellIterator.next();

                if(cell.getColumnIndex()==1){
                    gdp.setCountryCode(cell.getStringCellValue());
                }
                if(cell.getColumnIndex()==2){
                    gdp.setYear(Integer.parseInt(cell.getStringCellValue()));
                }
                if(cell.getColumnIndex()==3){
                    gdp.setValue(cell.getNumericCellValue());
                }

            }
            gdpList.add(gdp);
        }
        return gdpList;
    }


}
